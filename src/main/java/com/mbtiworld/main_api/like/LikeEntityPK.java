package com.mbtiworld.main_api.like;

import java.io.Serializable;

import lombok.Data;

@Data
public class LikeEntityPK implements Serializable {
    //Serializable: 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 
    //byte 형태로 데이터를 변환하는 기술.

    private static final long serialVersionUID = 1L;

    private String regId;

    private String code;

}
