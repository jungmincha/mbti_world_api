package com.aquamarine.main_api.login;

import lombok.Data;

@Data
public class UserSessionEntity {

    private String userId;

    private String userNm;

    private String authz;

}
