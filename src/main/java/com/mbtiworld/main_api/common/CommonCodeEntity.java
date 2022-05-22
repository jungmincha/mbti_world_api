package com.mbtiworld.main_api.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mbtiworld.main_api.bbs.cmmt.BbsCmmtEntity;

import lombok.Data;

@Data
@Entity
@Table(name="mw_common_code")
public class CommonCodeEntity {

    @Id
    @Column(name = "common_id")
    private String code;

    @Column
    private String codeNm;

    @Column
    private String sort;

    @Column
    private String superCode;

    @Column
    private String useYn;

    // @OneToOne(mappedBy = "commonCodeEntity")
    // private BbsCmmtEntity bbsCmmtEntity;
    
}
