package com.aquamarine.main_api.bbs;

import lombok.Data;

@Data
public class BbsFilterDto {

    private String bbsCd;

    private String searchParam;

    private int size;

    private int page;

}
