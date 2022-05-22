package com.mbtiworld.main_api.bbs;

import java.util.List;

import lombok.Data;

@Data
public class BbsFilterDto {

    private String bbsSeq;

    private String searchParam;

    private int size;

    private int page;

    private String delYn;

    private String mbtiType;

    private List<String> mbtiList;

}
