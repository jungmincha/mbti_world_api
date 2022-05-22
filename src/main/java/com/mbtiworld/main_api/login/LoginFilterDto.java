package com.mbtiworld.main_api.login;

import lombok.Data;

@Data
public class LoginFilterDto {

    private String mbrId;

    private String mbrPwd;

    private String mbrEmail;
    
}
