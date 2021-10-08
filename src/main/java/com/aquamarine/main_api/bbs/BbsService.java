package com.aquamarine.main_api.bbs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class BbsService {

    @Autowired
    BbsRepository bbsRepository;

    @Autowired
    BbsRepoSupport bbsRepoSupport;

    public Map<String, Object> searchBbsList(BbsFilterDto bbsFilterDto) {

        return bbsRepoSupport.searchBbsList(bbsFilterDto);
    }

    @Transactional
    public Map<String, Object> saveBbs(BbsEntity bbsEntity) {

        Map<String , Object> rstMap = new HashMap<>();

        try{
            bbsEntity.setDel_yn("N");

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
    
}
