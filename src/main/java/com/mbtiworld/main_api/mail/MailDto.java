package com.mbtiworld.main_api.mail;

import lombok.Data;

@Data
public class MailDto {

    private String address;

    private String title;

    private String message;
    
}
