package com.mbtiworld.main_api.bbs;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="mw_bbs_info")
public class BbsEntity {


    @Id                 //BbsCodeGenerator
    @GenericGenerator(name="gen_bbs_seq", strategy="com.mbtiworld.main_api.bbs.BbsCodeGenerator")
    @GeneratedValue(generator="gen_bbs_seq")
    private String bbsSeq;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String bbsType;

    @Column
    private String mbtiType;

    @Column
    private String thumbImg;

    @Column
    private int wishCnt;

    @Column
    private String tag;

    @Column
    private int readCnt;

    @Column
    private String writeId;

    @Column
    private String mbrNickname;

    // @Formula(value = "("
    //         +"select b.mbr_nickname from am_mbr_info b where b.mbr_id = write_id"
    //         +")")
    // private String mbrNickname;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime writeDt;

    @CreatedDate
    @Column
    private LocalDateTime modiDt;

    @Column
    private String delYn;

    @Column(name = "test_dt" , nullable = false , updatable = true)
    private Date testDt;





    
}
