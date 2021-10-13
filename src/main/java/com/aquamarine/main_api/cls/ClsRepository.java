package com.aquamarine.main_api.cls;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClsRepository extends CrudRepository<ClsEntity, String> {

    ClsEntity findByClsCd(String clsCd);

}
