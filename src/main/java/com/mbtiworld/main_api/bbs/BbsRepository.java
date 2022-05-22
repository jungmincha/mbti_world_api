package com.mbtiworld.main_api.bbs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsRepository extends CrudRepository<BbsEntity , String>{

    BbsEntity findByBbsSeq(String bbsSeq);

    Page<BbsEntity> findByDelYnAndMbtiTypeOrderByWriteDtDesc(String string, String mbtiType, PageRequest pageRequest);
    
}
         // istjBbsList: [], //1
          // istpBbsList: [], //2
          // isfjBbsList: [], //3
          // isfpBbsList: [], //4
          // intjBbsList: [], //5
          // intpBbsList: [], //6
          // infjBbsList: [], //7
          // infpBbsList: [], //8
          // estjBbsList: [], //9
          // estpBbsList: [], //10 
          // esfjBbsList: [], //11
          // esfpBbsList: [], //12
          // entjBbsList: [], //13
          // entpBbsList: [], //14
          // enfjBbsList: [], //15
          // enfpBbsList: [], //16