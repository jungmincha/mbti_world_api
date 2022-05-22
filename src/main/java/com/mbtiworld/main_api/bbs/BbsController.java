package com.mbtiworld.main_api.bbs;

import java.util.Map;

import com.mbtiworld.main_api.bbs.cmmt.BbsCmmtEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "01.MBTI 게시판", value = "api/bbs : BbsController")
@RestController
@RequestMapping("/api/bbs")
public class BbsController {
    //haha
    @Autowired
    BbsService bbsService;

    // 전체보기
    @ApiOperation(value = "MBTI 전체 게시판 리스트 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "cmpnCd", dataType = "string", value = "예) 회사코드: CA002"),
        @ApiImplicitParam(name = "projCd", dataType = "string", value = "예) 프로젝트코드: PRJ0000002"),
        @ApiImplicitParam(name = "prdctCd", dataType = "string", value = "예) 프로젝트코드: PRD004")
    })
    @PostMapping("/list")
    public Map<String, Object> searchBbsList(@RequestBody BbsFilterDto bbsFilterDto) {

        return bbsService.searchBbsList(bbsFilterDto);
    }

    // 등록, 수정
    @PostMapping("/save")
    public Map<String, Object> saveBbs(@RequestBody BbsEntity bbsEntity) {

        return bbsService.saveBbs(bbsEntity);
    }

    // 게시글 상세
    @GetMapping("/read/{bbsSeq}")
    public Map<String, Object> readBbsDetails(@PathVariable String bbsSeq) {

        return bbsService.readBbsDetails(bbsSeq);
    }

    // 게시글 삭제
    @PostMapping("/delete/{bbsSeq}")
    public Map<String, Object> deleteBbs(@PathVariable String bbsSeq) {

        return bbsService.deleteBbs(bbsSeq);
    }

    // 답변 등록
    @PostMapping("/cmmt/save")
        public Map<String, Object> saveBbsCmmt(@RequestBody BbsCmmtEntity bbsCmmtEntity) {

            return bbsService.saveBbsCmmt(bbsCmmtEntity);
        }

    // 답변 삭제
    @PostMapping("/cmmt/delete/{bbsSeq}/{cmmtSeq}")
    public Map<String, Object> deleteBbsCmmt(@PathVariable String bbsSeq, @PathVariable Long cmmtSeq) {

        return bbsService.deleteBbsCmmt(bbsSeq, cmmtSeq);
    }

}
