package com.aquamarine.main_api.like;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="am_like")
@IdClass(LikeEntityPK.class)
public class LikeEntity {

    @Id
    private String regId;

    @Id
    private String code;

    @Column
    private String likeType;

    @Column
    private String likeYn;

    @Column
    private String useYn;
    
}
