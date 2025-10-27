package com.genius.utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String syskey;
    private long autokey;
    private String createddate;
    private String modifieddate;
    private String createduser;
    private String modifieduser;
    private int recordstatus;
    private String t1; // use userid both for mobile and web
    private String t2; // use username both for mobile and web
    private int n1; // use variable name both for mobile and web
}
