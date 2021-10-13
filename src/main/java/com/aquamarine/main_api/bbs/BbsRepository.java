package com.aquamarine.main_api.bbs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsRepository extends CrudRepository<BbsEntity , String>{

    BbsEntity findByBbsCd(String bbsCd);

     Page<BbsEntity>findByDelYnOrderByWriteDtDesc(String delYn, Pageable pageable);
    
}
