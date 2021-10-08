package com.aquamarine.main_api.bbs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bbs")
public class BbsController {

    @Autowired
    BbsService bbsService;

        //전체보기
        @PostMapping("/list")
        public Map<String,Object> searchBbsList(@RequestBody BbsFilterDto bbsFilterDto) {
    
            return bbsService.searchBbsList(bbsFilterDto);
        }
    
        //등록, 수정
        @PostMapping("/save")
        public Map<String,Object> saveBbs(@RequestBody BbsEntity bbsEntity) {
    
            return bbsService.saveBbs(bbsEntity);
        }
    
}
