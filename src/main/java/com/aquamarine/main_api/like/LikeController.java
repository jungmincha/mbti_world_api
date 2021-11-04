package com.aquamarine.main_api.like;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

        //좋아요
    @PostMapping("/save")
    public Map<String,Object> saveLike(@RequestBody LikeEntity likeEntity) {

        return likeService.saveLike(likeEntity);
    }

    //좋아요 취소
    @PostMapping("/cancel")
    public Map<String,Object> saveCancel(@RequestBody LikeEntity likeEntity) {

        return likeService.saveCancel(likeEntity);
    }

    @GetMapping("/list/{mbrId}")
    public Map<String,Object> mbrLikeList(@PathVariable String mbrId) {

        return likeService.mbrLikeList(mbrId);
    }
}
