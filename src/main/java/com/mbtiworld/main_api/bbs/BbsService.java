package com.mbtiworld.main_api.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mbtiworld.main_api.bbs.cmmt.BbsCmmtEntity;
import com.mbtiworld.main_api.bbs.cmmt.BbsCmmtRepository;
import com.mbtiworld.main_api.common.ComCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class BbsService {

    @Autowired
    BbsRepository bbsRepository;

    @Autowired
    BbsRepoSupport bbsRepoSupport;

    @Autowired
    BbsCmmtRepository bbsCmmtRepository;

    @Autowired
    ComCodeRepository comCodeRepository;


    public Map<String, Object> searchBbsList(BbsFilterDto bbsFilterDto) {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> rtMap = new HashMap<>();

        try {
            if (!ObjectUtils.isEmpty(bbsFilterDto)) {
                dataMap = bbsRepoSupport.searchBbsList(bbsFilterDto);

                if (dataMap != null) {
                    rtMap.put("code", "S");
                    rtMap.put("data", dataMap);
                } else {
                    rtMap.put("code", "F");
                    rtMap.put("dataMap is null", dataMap);
                }

            }

        } catch (Exception e) {
            rtMap.put("errmsg", e.getMessage());
            rtMap.put("errmsg2", e.getCause());
        }

        return rtMap;
    }

    @Transactional
    public Map<String, Object> saveBbs(BbsEntity bbsEntity) {

        Map<String, Object> rstMap = new HashMap<>();

        try {
            bbsEntity.setDelYn("N");

            bbsRepository.save(bbsEntity);

            rstMap.put("code", "S");
            rstMap.put("data", bbsEntity);

        } catch (Exception e) {
            rstMap.put("code", "E");
            rstMap.put("errmsg1", e.getMessage());
            rstMap.put("errmsg2", e.getCause());
        }

        return rstMap;
    }

    // 게시판, 댓글 조회
    public Map<String, Object> readBbsDetails(String bbsSeq) {
        Map<String, Object> rstMap = new HashMap<>();
        Map<String, Object> dtMap = new HashMap<>();

        try {
            String delYn = "N";
            BbsEntity bbsEntity = bbsRepository.findByBbsSeq(bbsSeq);
            List<BbsCmmtEntity> cmmtList = bbsCmmtRepository.findByBbsSeqAndDelYnOrderByWriteDtAsc(bbsSeq, delYn);

            dtMap.put("bbsInfo", bbsEntity);
            dtMap.put("list", cmmtList);

            if (!ObjectUtils.isEmpty(dtMap)) {
                rstMap.put("code", "S");
                rstMap.put("data", dtMap);
            } else {
                rstMap.put("code", "F");
                rstMap.put("data", "bbsEntity is null");
            }

        } catch (Exception e) {
            rstMap.put("code", "E");
            rstMap.put("errmsg1", e.getMessage());
            rstMap.put("errmsg2", e.getCause());
        }

        return rstMap;
    }

    public Map<String, Object> deleteBbs(String bbsSeq) {
        Map<String, Object> rstMap = new HashMap<>();

        try {
            String delYn = "N";
            BbsEntity bbsEntity = bbsRepository.findByBbsSeq(bbsSeq);
            List<BbsCmmtEntity> cmmtList = bbsCmmtRepository.findByBbsSeqAndDelYnOrderByWriteDtAsc(bbsSeq, delYn);

            // 글의 댓글 모두 삭제
            for (BbsCmmtEntity cmmtEntity : cmmtList) {
                cmmtEntity.setDelYn("Y");
                bbsCmmtRepository.save(cmmtEntity);
            }
            // 글 삭제
            bbsEntity.setDelYn("Y");
            bbsRepository.save(bbsEntity);

            rstMap.put("code", "S");
            rstMap.put("data", bbsEntity);

        } catch (Exception e) {
            rstMap.put("code", "E");
            rstMap.put("msg", e.getMessage());
            rstMap.put("msg", e.getCause());
        }

        return rstMap;
    }

    // 댓글 저장 후 댓글리스트 조회
    public Map<String, Object> saveBbsCmmt(BbsCmmtEntity bbsCmmtEntity) {
        Map<String, Object> rstMap = new HashMap<>();
        Map<String, Object> dtMap = new HashMap<>();
        try {
            String delYn = "N";
            BbsCmmtEntity saveEntity = bbsCmmtRepository.save(bbsCmmtEntity);
            List<BbsCmmtEntity> cmmtList = bbsCmmtRepository
                    .findByBbsSeqAndDelYnOrderByWriteDtAsc(saveEntity.getBbsSeq(), delYn);

                    

            dtMap.put("data", saveEntity);
            dtMap.put("list", cmmtList);

            if (!ObjectUtils.isEmpty(dtMap)) {
                rstMap.put("code", "S");
                rstMap.put("data", dtMap);
            } else {
                rstMap.put("code", "F");
                rstMap.put("data", "dtMap is null");
            }

        } catch (Exception e) {
            rstMap.put("code", "E");
            rstMap.put("msg", e.getMessage());
            rstMap.put("msg", e.getCause());
        }

        return rstMap;
    }

    // 답변 삭제후 리스트 조회
    public Map<String, Object> deleteBbsCmmt(String bbsSeq, Long cmmtSeq) {
        Map<String, Object> rstMap = new HashMap<>();
        Map<String, Object> dtMap = new HashMap<>();
        try {
            String delYn = "N";
            BbsCmmtEntity saveEntity = bbsCmmtRepository.findByBbsSeqAndCmmtSeq(bbsSeq, cmmtSeq);
            saveEntity.setDelYn("Y");
            BbsCmmtEntity bbsCmmtEntity = bbsCmmtRepository.save(saveEntity);
            List<BbsCmmtEntity> cmmtList = bbsCmmtRepository
                    .findByBbsSeqAndDelYnOrderByWriteDtAsc(bbsCmmtEntity.getBbsSeq(), delYn);

            dtMap.put("data", bbsCmmtEntity);
            dtMap.put("list", cmmtList);

            if (!ObjectUtils.isEmpty(dtMap)) {
                rstMap.put("code", "S");
                rstMap.put("data", dtMap);
            } else {
                rstMap.put("code", "F");
                rstMap.put("data", "dtMap is null");
            }

        } catch (Exception e) {
            rstMap.put("code", "E");
            rstMap.put("msg", e.getMessage());
            rstMap.put("msg", e.getCause());
        }

        return rstMap;
    }
}
