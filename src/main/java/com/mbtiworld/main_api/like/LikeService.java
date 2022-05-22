package com.mbtiworld.main_api.like;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    //좋아요
    public Map<String, Object> saveLike(LikeEntity likeEntity) {

        Map<String, Object> rtnMap = new HashMap<>();

        if(!ObjectUtils.isEmpty(likeEntity)){
            likeEntity.setLikeType("LIKE_TYPE_01");
            likeEntity.setLikeYn("Y");
            likeEntity.setUseYn("Y");
        } else {
            rtnMap.put("code", "e");
        }
        try{

            likeRepository.save(likeEntity);
            rtnMap.put("code", "S");
            rtnMap.put("data" , likeEntity);
            
        }catch(Exception e){
            rtnMap.put("errmgs", e.getMessage());
        }
        return rtnMap;
    }

    //좋아요 취소
    public Map<String, Object> saveCancel(LikeEntity likeEntity) {
        Map<String, Object> rtnMap = new HashMap<>();

        if(!ObjectUtils.isEmpty(likeEntity)){
            likeEntity.setLikeType("LIKE_TYPE_01");
            likeEntity.setUseYn("Y");
            likeEntity.setLikeYn("N");//좋아요 취소하는 플래그
        } else {
            rtnMap.put("code", "e");
        }
        try{

            likeRepository.save(likeEntity);
            rtnMap.put("code", "S");
            rtnMap.put("data" , likeEntity);
            
        }catch(Exception e){
            rtnMap.put("errmgs", e.getMessage());
        }
        return rtnMap;
    }

    //좋아요 리스트
    public Map<String, Object> mbrLikeList(String mbrId) {
        Map<String, Object> rtnMap = new HashMap<>();
        Map<String, Object> dtMap = new HashMap<>();
        List<LikeEntity> rtnList = new ArrayList<>();

        if(mbrId == null){
            rtnMap.put("code", "E");
        }
        try{
            rtnList = likeRepository.findByRegId(mbrId);
            dtMap.put("List", rtnList);
        }catch(Exception e){
            rtnMap.put("errmgs", e.getCause());
            rtnMap.put("errmgs", e.getMessage());
        }

            rtnMap.put("code", "S");
            rtnMap.put("data", dtMap);
        
        return rtnMap;
    }

}
