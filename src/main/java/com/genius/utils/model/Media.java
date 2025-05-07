package com.genius.utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Media {
    private String raw;
    private String name;
    private String type;
}
