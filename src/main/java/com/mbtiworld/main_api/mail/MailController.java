package com.mbtiworld.main_api.mail;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send/{email}")
    public Map<String, Object> sendTestMail(@PathVariable String email){
        // MailDto mailDto = new MailDto();
        
        // mailDto.setAddress(email);
        // mailDto.setTitle("정민님이 발송한 이메일입니다.");
        // mailDto.setMessage("안녕하세여");

        //mailService.sendTestMail(mailDto);

        return mailService.sendTestMail(email);
    }

}
