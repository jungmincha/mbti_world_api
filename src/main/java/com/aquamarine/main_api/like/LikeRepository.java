package com.aquamarine.main_api.like;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity, String> {

    List<LikeEntity> findByRegId(String mbrId);

}
