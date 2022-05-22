package com.mbtiworld.main_api.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mbtiworld.main_api.login.IUserEntity;
import com.mbtiworld.main_api.login.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class MailService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Map<String, Object> sendTestMail(String email) {

        Map<String, Object> rMap = new HashMap<>();
        SimpleMailMessage message = new SimpleMailMessage();

        try {

            checkMbrEmail(email);
            message.setTo(email);
            message.setSubject("mbti world 이메일 인증 코드입니다.");
            message.setText("인증코드 :" + getRandomNumberString());
            mailSender.send(message);

            String emailCode[] = message.getText().split(":");

            rMap.put("code", "S");
            rMap.put("data", emailCode[1]);

        } catch (Exception e) {
            rMap.put("msgarr", e.getCause());
            rMap.put("msgarr2", e.getMessage());
        }

        return rMap;
    }

    public static String getRandomNumberString() {
        Random rnd = new Random(); // 랜덤함수 생성
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    // 이메일 중복확인
    private void checkMbrEmail(String email) throws Exception {
        IUserEntity iUserEntity = loginRepository.findByMbrEmail(email);
        if (!ObjectUtils.isEmpty(iUserEntity)) {
            throw new Exception();
        }
    }

}
