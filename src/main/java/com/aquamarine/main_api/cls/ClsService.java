package com.aquamarine.main_api.cls;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class ClsService {

    @Autowired
    private ClsRepoSupport clsRepoSupport;

    @Autowired
    private ClsRepository clsRepository;

    //전체보기
    public Map<String, Object> searchClsList(ClsFilterDto clsFilterDto) {

        return clsRepoSupport.searchClsList(clsFilterDto);
    }

    @Transactional
    public Map<String, Object> saveCls(ClsEntity clsEntity) {

        Map<String , Object> rstMap = new HashMap<>();

        try{
            clsEntity.setDelYn("N");

            clsRepository.save(clsEntity);

            rstMap.put("code", "S");
            rstMap.put("clsEntity" , clsEntity);

        }catch(Exception e){
            rstMap.put("code", "E");
            rstMap.put("msg", e.getMessage());
            rstMap.put("msg", e.getCause());
        }


        return rstMap;
    }

    public Map<String, Object> readClsDetails(String clsCd) {
        
        Map<String , Object> rstMap = new HashMap<>();

        rstMap.put("data", clsRepository.findByClsCd(clsCd));

        return rstMap;
    }

}
