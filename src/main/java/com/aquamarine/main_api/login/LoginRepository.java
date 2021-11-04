package com.aquamarine.main_api.login;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByMbrId(String mbrId);
    
}
