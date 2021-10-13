package com.aquamarine.main_api.cls;

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
public class ClsRepoSupport  extends QuerydslRepositorySupport {

    public ClsRepoSupport(JPAQueryFactory JPAQueryFactory) {
        super(ClsEntity.class);
    }

    @Autowired
    private JPAQueryFactory queryFactory;

    public Map<String, Object> searchClsList(ClsFilterDto clsFilterDto) {

        Map<String , Object> rstMap = new HashMap<>();
        Map<String , Object> dataMap = new HashMap<>();
        List<ClsEntity> rstList = new ArrayList<>();

        try{
            QClsEntity ce = QClsEntity.clsEntity;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(ce.delYn.eq("N"));

            JPQLQuery<ClsEntity> query = null;

            query = queryFactory
                .selectFrom(ce).where(builder).orderBy(ce.writeDt.desc());

                Pageable pageable = PageRequest.of(clsFilterDto.getPage(), clsFilterDto.getSize());//페이징
                long totalCount = query.fetchCount();//리스트 전체 숫자
                rstList = getQuerydsl().applyPagination(pageable, query).fetch();//2개 이상은 fetch() 1개는 fetchOne()

                dataMap.put("list", rstList);
                dataMap.put("totalCnt", totalCount);

                if(!ObjectUtils.isEmpty(dataMap)){
                    rstMap.put("code", "S");
                    rstMap.put("data", dataMap);
                }

        }catch(Exception e){
            rstMap.put("code", "E");
            rstMap.put("msg", e.getMessage());
            rstMap.put("msg", e.getCause());         
        }
        return rstMap;
    }
}
