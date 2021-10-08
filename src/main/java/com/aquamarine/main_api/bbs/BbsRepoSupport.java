package com.aquamarine.main_api.bbs;

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
    
    //리스트
    public Map<String, Object> searchBbsList(BbsFilterDto bbsFilterDto) {

        Map<String , Object> rstMap = new HashMap<>();
        Map<String , Object> dataMap = new HashMap<>();
        List<BbsEntity> rstList = new ArrayList<>();

        try{
            QBbsEntity be = QBbsEntity.bbsEntity;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(be.del_yn.eq("N"));

            JPQLQuery<BbsEntity> query = null;

            query = queryFactory
                .selectFrom(be).where(builder).orderBy(be.write_dt.desc());

                Pageable pageable = PageRequest.of(bbsFilterDto.getPage(), bbsFilterDto.getSize());//페이징
                long totalCount = query.fetchCount();//리스트 전체 숫자
                rstList = getQuerydsl().applyPagination(pageable, query).fetch();//2개 이상은 fetch() 1개는 fetchOne()

                dataMap.put("List", rstList);
                dataMap.put("totalcnt", totalCount);

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
