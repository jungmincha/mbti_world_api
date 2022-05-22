package com.mbtiworld.main_api.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = {"http://localhost:3030"})
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/auth") //로그인
    public Map<String, Object> getLogin(@RequestBody LoginFilterDto loginFilterDto,HttpServletRequest request) {

        return loginService.getLogin(loginFilterDto, request);
        
    }

    @GetMapping("/session") //유저정보 가져오기
    public Map<String , Object> getSession(HttpServletRequest request) {

        return loginService.getSession(request);
    }

    @GetMapping("/sessionend") //로그아웃
    public Map<String , Object> getSessionOut(HttpServletRequest request) {

        return loginService.getSessionOut(request);
    }

    @PostMapping("/join") //회원가입
    public Map<String , Object> joinUser(@RequestBody UserEntity userEntity) {

        return loginService.joinUser(userEntity);
    }

    @PostMapping("/check/{mbrId}") //아이디 중복 체크
    public Map<String , Object> checkMbrId(@PathVariable String mbrId) {

        return loginService.checkMbrId(mbrId);
    }
  
    @PostMapping("/findById") //아이디 찾기
    public Map<String , Object> getFindById(@RequestBody LoginFilterDto loginFilterDto) {

        return loginService.getFindById(loginFilterDto);
    }

    
}
