package com.mbtiworld.main_api.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // 로그인
    public Map<String, Object> getLogin(LoginFilterDto loginFilterDto, HttpServletRequest request) {

    Map<String , Object> dataMap = new HashMap<>();
    BCryptPasswordEncoder bcPwd = new BCryptPasswordEncoder();

    HttpSession session = request.getSession();

    try{
        UserEntity userEntity =
        loginRepository.findByMbrId(loginFilterDto.getMbrId());

            if(!bcPwd.matches(loginFilterDto.getMbrPwd(), userEntity.getMbrPwd())){
                //입력받은 비밀번호와 디비에 암호화되어서 저장된 비밀번호를 비교해서 일치하지 않으면 code. pwe return
                log.info("mbrPwd is not corrected");
                dataMap.put("code", "pwe");
            }else {
                session.setAttribute("mbrId", userEntity.getMbrId());
                session.setAttribute("authz", userEntity.getAuthz());
                session.setAttribute("mbrNickname", userEntity.getMbrNickname());
                session.setAttribute("mbtiType", userEntity.getMbtiType());
                session.setMaxInactiveInterval(60 * 60); //60 * 3

                dataMap.put("code", "S");
                dataMap.put("data", userEntity);
            }

        }catch(Exception e){
            log.info("mbrId is not corrected");
            dataMap.put("code", "ide");//아이디 정보 확인
            dataMap.put("mgserr", e.getMessage());
        }

    return dataMap;
    }

    //접속 유저 정보 세션에 담아서 뿌려주기
    public Map<String , Object> getSession(HttpServletRequest request) {

    Map<String , Object> rtnMap = new HashMap<>();
    Map<String , Object> dataMap = new HashMap<>();

    HttpSession session = request.getSession();
    try{
    String mbrId = (String)session.getAttribute("mbrId");
    String authz = (String)session.getAttribute("authz");
    String mbrNickname = (String)session.getAttribute("mbrNickname");

    log.info("==============================");
    log.info("세션에 저장 되 있는 변수 : "+ mbrId + "," + authz + "," + mbrNickname);
    log.info("==============================");

    dataMap.put("mbrId", mbrId);
    dataMap.put("authz", authz);
    dataMap.put("mbrNickname", mbrNickname);

    if(dataMap.get("mbrId") != null){
    rtnMap.put("code", "S");
    rtnMap.put("data", dataMap);
    } else {
    rtnMap.put("code", "E");
    }

    } catch(Exception e){
    rtnMap.put("errmsg", e.getCause());
    rtnMap.put("errmsg2", e.getMessage());
    }

    return rtnMap;
    }

    //로그아웃
    public Map<String, Object> getSessionOut(HttpServletRequest request) {

    Map<String , Object> rtnMap = new HashMap<>();

    HttpSession session = request.getSession();
    try{

    session.invalidate(); //세션값 죽임

    rtnMap.put("code", "S");

    }catch(Exception e){
    rtnMap.put("errmsg", e.getCause());
    rtnMap.put("errmsg2", e.getMessage());

    }

    return rtnMap;
    }

    // 회원가입
    @Transactional
    public Map<String, Object> joinUser(UserEntity userEntity) {

        Map<String, Object> rtnMap = new HashMap<>();
        BCryptPasswordEncoder bcPwd = new BCryptPasswordEncoder();// 암호화 객체 생성

        try {
            userEntity.setAuthz("ROLE_USER_01");// 일반회원 유저셋팅 저장
            String mbrPwd = bcPwd.encode(userEntity.getMbrPwd());// 입력받은 비밀번호 암호화
            userEntity.setMbrPwd(mbrPwd);// 암호화된 비밀번호 저장

            loginRepository.save(userEntity);// 디비에 저장

            rtnMap.put("code", "S");

        } catch (Exception e) {
            rtnMap.put("errmsg", e.getCause());
            rtnMap.put("errmsg2", e.getMessage());

        }

        return rtnMap;
    }

    // 아이디 중복 검사
    public Map<String, Object> checkMbrId(String mbrId) {

        Map<String, Object> rtnMap = new HashMap<>();

        try {
            UserEntity userEntity = loginRepository.findByMbrId(mbrId);
            rtnMap.put("code", "ide");
            rtnMap.put("data", userEntity.getMbrId());
            rtnMap.put("checkMbrId", false);// 사용x 중복된 아이디

        } catch (Exception e) {
            rtnMap.put("code", "S");
            rtnMap.put("msgerr", e.getMessage());
            rtnMap.put("checkMbrId", true); // 사용 할수 있는 아이디
        }

        return rtnMap;
    }

    //아이디 찾기
    public Map<String, Object> getFindById(LoginFilterDto loginFilterDto) {

        Map<String, Object> rtnMap = new HashMap<>();

        try {
            if (!ObjectUtils.isEmpty(loginFilterDto)) {
               // String mbrId = loginRepository.findByMbrEmail(loginFilterDto.getMbrEmail());
               IUserEntity userEntity = loginRepository.findByMbrEmail(loginFilterDto.getMbrEmail());
                //String mbrId = userEntity;
                rtnMap.put("code", "S");
                rtnMap.put("data", userEntity);
            } else {
                rtnMap.put("code", "F");
                rtnMap.put("msgerr", "loginFilterDto is null");
            }

        } catch (Exception e) {
            rtnMap.put("code", "E");
            rtnMap.put("msgerr", e.getMessage());
        }

        return rtnMap;
    }

}
