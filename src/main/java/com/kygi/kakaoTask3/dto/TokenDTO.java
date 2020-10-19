package com.kygi.kakaoTask3.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;


@Getter
@Setter
public class TokenDTO implements Serializable {

    public static final String TOKEN       = "token";

    private String token;
    public TokenDTO(Map<String, Object> values) {
        this.token = values.get(TOKEN) != null ? (String) values.get(TOKEN): null;
    }
}
