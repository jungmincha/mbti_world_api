package com.aquamarine.main_api.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class BbsService {

    @Autowired
    BbsRepository bbsRepository;

    // @Autowired
    // BbsRepoSupport bbsRepoSupport;

    public Map<String, Object> searchBbsList(BbsFilterDto bbsFilterDto) {
        Map<String, Object> dataMap = new HashMap<>();
       
        try{
            PageRequest pageRequest = PageRequest.of(bbsFilterDto.getPage(), bbsFilterDto.getSize());

            dataMap.put("list", bbsRepository.findByDelYnOrderByWriteDtDesc("N" , pageRequest));
        }catch(Exception e) {
            dataMap.put("errmsg", e.getMessage());
            dataMap.put("errmsg2", e.getCause());
        }

        return dataMap;
    }

    @Transactional
    public Map<String, Object> saveBbs(BbsEntity bbsEntity) {

        Map<String , Object> rstMap = new HashMap<>();

        try{
            bbsEntity.setDelYn("N");

            bbsRepository.save(bbsEntity);

            rstMap.put("code", "S");
            rstMap.put("bbsEntity" , bbsEntity);

        }catch(Exception e){
            rstMap.put("code", "E");
            rstMap.put("msg", e.getMessage());
            rstMap.put("msg", e.getCause());
        }


        return rstMap;
    }

    public Map<String, Object> readBbsDetails(String bbsCd) {

        Map<String , Object> rstMap = new HashMap<>();

        rstMap.put("data", bbsRepository.findByBbsCd(bbsCd));

        return rstMap;
    }
    
}
