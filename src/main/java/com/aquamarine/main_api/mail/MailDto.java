package com.aquamarine.main_api.mail;

import lombok.Data;

@Data
public class MailDto {

    private String address;

    private String title;

    private String message;
    
}
