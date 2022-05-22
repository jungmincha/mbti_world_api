package com.mbtiworld.main_api.login;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name="mw_mbr_info")
public class UserEntity {

    // mbr_id, mbr_nickname, mbr_pwd, mbr_nm, mbr_email, mbr_tel, address, authz,
    // prsn_yn, email_chck_yn, del_yn, reg_dt, biz_no  
  
    @Id
    @Column(nullable = false)
    private String mbrId;

    @Column
    private String mbrNickname;

    @Column
    private String mbrPwd;

    @Column
    private String mbrEmail;

    // @Column
    // private String mbrTel;

    // @Column
    // private String address;

    @Column
    private String authz;

    // @Column
    // private String prsnYn;

    @Column
    private String emailChckYn;

    @Column
    private String delYn;

    @Column
    private String mbtiType;

    // @Column
    // @CreatedDate
    // private LocalDateTime regDt;

    // @Column
    // private String bizNo;
}
