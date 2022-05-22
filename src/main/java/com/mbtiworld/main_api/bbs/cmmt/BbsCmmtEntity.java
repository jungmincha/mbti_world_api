package com.mbtiworld.main_api.bbs.cmmt;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mbtiworld.main_api.common.CommonCodeEntity;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "mw_bbs_cmmt")
public class BbsCmmtEntity {

    @Id // BbsCodeGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmmtSeq;

    @Column // BbsCodeGenerator
    private String bbsSeq;

    @Column
    private String content;

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

    @Column
    private String mbtiType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime writeDt;

    @CreatedDate
    @Column
    private LocalDateTime modiDt;

    @Column
    private String delYn = "N";

}

