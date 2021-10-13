package com.aquamarine.main_api.cls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cls")
public class ClsController {

    @Autowired
    private ClsService clsService;

        //전체보기
        @PostMapping("/list")
        public Map<String,Object> searchClsList(@RequestBody ClsFilterDto clsFilterDto) {
    
            return clsService.searchClsList(clsFilterDto);
        }
    
        //수업 등록, 수정
        @PostMapping("/save")
        public Map<String,Object> saveCls(@RequestBody ClsEntity clsEntity) {
    
            return clsService.saveCls(clsEntity);
        }

        //수업 상세
        @GetMapping("/read/{clsCd}")
        public Map<String, Object> readClsDetails(@PathVariable String clsCd) {

            return clsService.readClsDetails(clsCd);
        }
    
}
