package com.aquamarine.main_api.cls;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="am_cls_info")
public class ClsEntity {

    @Id
    @GenericGenerator(name="gen_cls_cd", strategy="com.aquamarine.main_api.cls.ClsCodeGenerator")
    @GeneratedValue(generator="gen_cls_cd")
    private String clsCd;

    @Column
    private String clsTitle;

    @Column
    private String clsInfo;//클래스 한줄 설명

    @Column
    private String clsDetail;//클래스 상세 설명

    @Column
    private String clsPrice;

    @Column
    private String clsLoca;

    @Column
    private String clsTel;

    @Column
    private String clsType;

    @Column
    private String thumbImg;

    @Column
    private String attachFile;

    @Column
    private int likeCnt;

    @Column
    private String tag;

    @Column
    private int readCnt;

    @Column
    private String writeId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime writeDt;

    @CreatedDate
    @Column
    private LocalDateTime modiDt;

    @Column
    private String delYn;

    @Column
    private String tempYn;





    
}
