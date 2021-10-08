package com.aquamarine.main_api.bbs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsRepository extends CrudRepository<BbsEntity , String>{
    
}
