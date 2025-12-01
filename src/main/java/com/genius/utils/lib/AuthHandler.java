package com.genius.utils.lib;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.genius.utils.model.ApiToken;
import com.genius.utils.model.VerifyApiToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthHandler {
    private static final String SECRET_KEY = "OhMyGenius!";
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final long TIME_LIMIT =  15L * 60 * 1000; // 15 minutes, 24L * 60 * 60 * 1000 = 1 day

    // get uuid by Genius iQ @20251107
    public static String getUniqueId() {
        return NanoIdUtils.randomNanoId(new SecureRandom(), NanoIdUtils.DEFAULT_ALPHABET, 11);
    }

    // encrypt & decrypt data by Genius iQ @20251108
    public static String encrypt(String plainText, String secretKey) {
        try {
            String secretkey = (secretKey != null && !secretKey.isBlank()) ? secretKey : SECRET_KEY;
            byte[] secret = MessageDigest.getInstance("SHA-256").digest(secretkey.getBytes());
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeySpec secretSpec = new SecretKeySpec(secret, "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretSpec, ivSpec);

            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            String ivEncoded = Base64.getEncoder().encodeToString(iv);
            String encEncoded = Base64.getEncoder().encodeToString(encrypted);

            return ivEncoded + "." + encEncoded;

        } catch (Exception err) {
            return null;
        }
    }

    public static String decrypt(String encryptedText, String secretKey) {
        try {
            String secretkey = (secretKey != null && !secretKey.isBlank()) ? secretKey : SECRET_KEY;
            byte[] secret = MessageDigest.getInstance("SHA-256").digest(secretkey.getBytes());
            String[] parts = encryptedText.split("\\.");

            if (parts.length != 2) return null;

            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] encrypted = Base64.getDecoder().decode(parts[1]);

            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec secretSpec = new SecretKeySpec(secret, "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretSpec, ivSpec);

            byte[] decrypted = cipher.doFinal(encrypted);

            return new String(decrypted, "UTF-8");

        } catch (Exception err) {
            return null;
        }
    }

    // APIs token authorization by Genius iQ @20251108 modified @20251115
    public static ApiToken generateApiToken(String secretKey, String domain) {
        try {
            String secretkey = (secretKey != null && !secretKey.isBlank()) ? secretKey : SECRET_KEY;
            String domainUse = (domain != null && !domain.isBlank()) ? domain : "*";

            long expireTime =  new Date().getTime() + TIME_LIMIT;
            String uuid = getUniqueId();
            String plainText = domainUse + uuid + expireTime + secretkey;

            byte[] secret = MessageDigest.getInstance("SHA-256").digest(plainText.getBytes(StandardCharsets.UTF_8));

            String encodedHash = bytesToHex(secret);
            String domainHex = bytesToHex(domainUse.getBytes(StandardCharsets.UTF_8));
            String uuidHex = bytesToHex(uuid.getBytes(StandardCharsets.UTF_8));
            String expireHex = Long.toHexString(expireTime);

            String token = uuidHex + "." + encodedHash + "." + expireHex + "." + domainHex;
            String expireAt = DateTimeHandler.formatTokenExpireTime(expireTime);

            return new ApiToken(token, expireAt);
        } catch (Exception err) {
            return null;
        }
    }

    public static VerifyApiToken verifyApiToken(String token, String secretKey, String domain) {
        try {
            String secretkey = (secretKey != null && !secretKey.isBlank()) ? secretKey : SECRET_KEY;
            String domainCheck = (domain != null && !domain.isBlank()) ? domain : "*";

            String[] parts = token.split("\\.");
            if (parts.length != 4) {
                return new VerifyApiToken(401, "Invalid Token Format!");
            }

            String uuidHex = parts[0];
            String tokenHash = parts[1];
            String expireHex = parts[2];
            String domainHex = parts[3];            

            String domainUse = new String(hexToBytes(domainHex), StandardCharsets.UTF_8);
            String uuid = new String(hexToBytes(uuidHex), StandardCharsets.UTF_8);
            long expireTime = Long.parseLong(expireHex, 16);

            if (new Date().getTime() > expireTime) {
                return new VerifyApiToken(401, "Token Expired!");
            }

            if (!domainUse.equals(domainCheck)) {
                return new VerifyApiToken(403, "Unauthorized Domain: " + domainCheck + "!");
            }

            String plainText = domainUse + uuid + expireTime + secretkey;
            byte[] secret = MessageDigest.getInstance("SHA-256").digest(plainText.getBytes(StandardCharsets.UTF_8));
            String expectedHash = bytesToHex(secret);

            if (!expectedHash.equals(tokenHash)) {
                return new VerifyApiToken(401, "Invalid Token!");
            }

            return new VerifyApiToken(200, "Token Verification Success.");            
        } catch (Exception e) {
            return new VerifyApiToken(401, "Token Verification Failed!");
        }
    }

    // private methods by Genius iQ @20251109
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

}
