package com.mbtiworld.main_api.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComCodeRepository extends CrudRepository<CommonCodeEntity, String> {
    
}
