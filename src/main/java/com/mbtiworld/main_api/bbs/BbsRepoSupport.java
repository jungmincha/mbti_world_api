package com.mbtiworld.main_api.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class BbsRepoSupport extends QuerydslRepositorySupport {

    public BbsRepoSupport(JPAQueryFactory queryFactory) {
        super(BbsEntity.class);
    }

    @Autowired
    private JPAQueryFactory queryFactory;

    // 리스트
    public Map<String, Object> searchBbsList(BbsFilterDto bbsFilterDto) {

        // Map<String, Object> rstMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        List<BbsEntity> rstList = new ArrayList<>();

        QBbsEntity be = QBbsEntity.bbsEntity;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(be.delYn.eq("N"));

        if (!ObjectUtils.isEmpty(bbsFilterDto.getMbtiType())) {// mbtiType이 있을 경우
            String mbtiType = bbsFilterDto.getMbtiType();
            builder.and(be.mbtiType.eq(mbtiType));
            JPQLQuery<BbsEntity> query = null;

            query = queryFactory
                    .selectFrom(be).where(builder).orderBy(be.writeDt.desc());

            Pageable pageable = PageRequest.of(bbsFilterDto.getPage(), bbsFilterDto.getSize());// 페이징
            long totalCount = query.fetchCount();// 리스트 전체 숫자
            rstList = getQuerydsl().applyPagination(pageable, query).fetch();// 2개 이상은 fetch() 1개는 fetchOne()

            dataMap.put("list", rstList);
            dataMap.put("totalCnt", totalCount);
        }

        if (!ObjectUtils.isEmpty(bbsFilterDto.getMbtiList())) {// mbtiList기 있을 경우
            List<String> mbtiList = bbsFilterDto.getMbtiList();
            for (String s : mbtiList) {
                builder.and(be.mbtiType.eq(s));

                JPQLQuery<BbsEntity> query = null;

                query = queryFactory
                        .selectFrom(be).where(builder).orderBy(be.writeDt.desc());

                Pageable pageable = PageRequest.of(bbsFilterDto.getPage(), bbsFilterDto.getSize());// 페이징
                long totalCount = query.fetchCount();// 리스트 전체 숫자
                rstList = getQuerydsl().applyPagination(pageable, query).fetch();// 2개 이상은 fetch() 1개는 fetchOne()

                dataMap.put(s + "list", rstList);
                dataMap.put(s + "totalCnt", totalCount);
                builder.not();
            }
        }

        return dataMap;
    }
}
