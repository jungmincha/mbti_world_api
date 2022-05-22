package com.mbtiworld.main_api.bbs.cmmt;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsCmmtRepository extends CrudRepository<BbsCmmtEntity, Long> {

    BbsCmmtEntity findByBbsSeqAndCmmtSeq(String bbsSeq, Long cmmtSeq);

    List<BbsCmmtEntity> findByBbsSeqAndDelYnOrderByWriteDtAsc(String bbsSeq, String delYn);
    
}
