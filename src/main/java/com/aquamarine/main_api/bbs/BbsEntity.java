package com.aquamarine.main_api.bbs;

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
@EntityListeners(value = { AuditingEntityListener.class })//뉴스 매체사 컬럼 필요
@Table(name="am_bbs_info")
public class BbsEntity {

    @Id
    @GenericGenerator(name="gen_bbs_cd", strategy="com.aquamarine.main_api.bbs.BbsCodeGenerator")
    @GeneratedValue(generator="gen_bbs_cd")
    private String bbsCd;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String bbs_type;

    @Column
    private String thumb_Img;

    @Column
    private String attach_file;

    @Column
    private int wish_cnt;

    @Column
    private String tag;

    @Column
    private int read_cnt;

    @Column
    private String write_id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime write_dt;

    @CreatedDate
    @Column
    private LocalDateTime modi_dt;

    @Column
    private String del_yn;

    @Column
    private String temp_yn;





    
}
