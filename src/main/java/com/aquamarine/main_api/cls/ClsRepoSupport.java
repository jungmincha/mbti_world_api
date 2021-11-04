package com.aquamarine.main_api.cls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aquamarine.main_api.like.QLikeEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
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
            QLikeEntity ql = QLikeEntity.likeEntity;
            BooleanBuilder builder = new BooleanBuilder();

            // if(clsFilterDto.getMbrId() == ""){
            //                 //접속ID가 NULL 값이면 N 입력
            // } 

            builder.and(ce.delYn.eq("N"));

            JPQLQuery<Tuple> query = null;

            query = queryFactory
            .select(ce.clsCd, //0
                ce.clsDetail, //1
                ce.clsInfo,   //2
                ce.thumbImg,  //3
                ce.writeDt,   //4
                ce.writeId,   //5
                ce.clsTitle, //6
                ExpressionUtils.as(
                JPAExpressions.select(ql.count())  //7
                               .from(ql)
                               .where(ql.code.eq(ce.clsCd).and(ql.likeYn.eq("Y")))
            , "clsLikeCtn"),
                ExpressionUtils.as(
                JPAExpressions.select(ql.likeYn)
                              .from(ql)
                              .where(ql.code.eq(ce.clsCd).and(ql.regId.eq(clsFilterDto.getMbrId())))
             , "likeYn")//8
            
            ).from(ce)
                .where(builder).orderBy(ce.writeDt.desc());

                Pageable pageable = PageRequest.of(clsFilterDto.getPage(), clsFilterDto.getSize());//페이징
                long totalCount = query.fetchCount();//리스트 전체 숫자
                List<Tuple> lt= getQuerydsl().applyPagination(pageable, query).fetch();//2개 이상은 fetch() 1개는 fetchOne()

                for(Tuple tp: lt) {
                    ClsEntity clsEntity = new ClsEntity();
                    clsEntity.setClsCd(tp.get(0, String.class));
                    clsEntity.setClsDetail(tp.get(1, String.class));
                    clsEntity.setClsInfo(tp.get(2, String.class));
                    clsEntity.setThumbImg(tp.get(3, String.class));
                    clsEntity.setWriteDt(tp.get(4, LocalDateTime.class));
                    clsEntity.setWriteId(tp.get(5, String.class));
                    clsEntity.setClsTitle(tp.get(6, String.class));
                    clsEntity.setClsLikeCtn(tp.get(7, Long.class));

                    if(clsFilterDto.getMbrId() == ""){
                            //접속ID가 NULL 값이면 N 입력
                    clsEntity.setLikeYn("N");  

                    }else{
                    clsEntity.setLikeYn(tp.get(8, String.class));
                    } 
          
                    rstList.add(clsEntity);
                }     

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
