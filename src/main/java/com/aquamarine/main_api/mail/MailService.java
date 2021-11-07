package com.aquamarine.main_api.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public Map<String, Object> sendTestMail(String email) {

        Map<String, Object> rMap = new HashMap<>();
        SimpleMailMessage message = new SimpleMailMessage();

        try{
            message.setTo(email);
            message.setSubject("아쿠아마린 이메일 인증 코드입니다.");
            message.setText("인증코드 :" + getRandomNumberString());
            mailSender.send(message);

            String emailCode[] = message.getText().split(":");

            rMap.put("code", "S");
            rMap.put("data", emailCode[1]);

        }catch(Exception e){
            rMap.put("msgarr", e.getCause());
            rMap.put("msgarr2", e.getMessage());
        }

        return rMap;
    }

    // public void sendTestMail(MailDto mailDto) {
    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setTo(mailDto.getAddress());
    //     message.setSubject(mailDto.getTitle());
    //     message.setText(mailDto.getMessage());

    //     mailSender.send(message);
    // }


    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
    
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
    
}
