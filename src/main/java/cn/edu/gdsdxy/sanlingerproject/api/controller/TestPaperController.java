package cn.edu.gdsdxy.sanlingerproject.api.controller;


import cn.edu.gdsdxy.sanlingerproject.core.dto.BaseDeleteDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.IsRecommendChangeDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.TestPaperAddDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.TestPaperUpdataDTO;
import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.TestPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@Api(description = "试卷模块")
@RestController
@RequestMapping("testPaper")
public class TestPaperController {

    @Autowired
    private TestPaperService testPaperService;

    /**
     * 查询试卷
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @param map 封装数据，包含试卷名
     *            , gradeName（年级）, keywork（试卷名）, subject（学科）
     * @return
     */

    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name = "pageNo",dataType = "Integer",required = false),
            @ApiImplicitParam(value = "页面大小",name = "pageSize",dataType = "Integer",required = false),
            @ApiImplicitParam(value = "年级",name = "gradeName",dataType = "String",required = false),
            @ApiImplicitParam(value = "试卷名",name = "keywork",dataType = "String",required = false),
            @ApiImplicitParam(value = "学科",name = "subject",dataType = "String",required = false),
    })
    @ApiOperation(value = "查询试卷", notes = "接收客户端的传递的查询条件数据")
    @GetMapping("selectList")
    public Result selectList(@RequestParam(defaultValue = "1") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,  @RequestParam Map<String, String> map )
    {
//        查询试卷
        Map dataMap = testPaperService.selectList(pageNo, pageSize, map);
        return ClientUserResult.successResult(dataMap);
    }

    /**
     * 后台管理用户删除试卷接口
     * @param baseDeleteDTO 接收后台管理用户的删除试卷信息
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "试卷id",name = "id",dataType = "String",required = true),
    })
    @ApiOperation(value = "删除试卷", notes = "接收后台管理的删除试卷数据")
    @PostMapping("delete")
    public Result delete(@Valid @RequestBody BaseDeleteDTO baseDeleteDTO)
    {
//        后台管理用户删除试卷接口
        testPaperService.delete(baseDeleteDTO);
        return AdminUserResult.successResult(null);
    }

    /**
     * 修改试卷
     * @param testPaperIUpdataDTO 接收客户端的传递的修改试卷数据
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "试卷id",name = "id",dataType = "Long",required = true),
            @ApiImplicitParam(value = "试卷名",name = "name",dataType = "String",required = true),
            @ApiImplicitParam(value = "试卷年级",name = "gradeName",dataType = "String",required = true),
            @ApiImplicitParam(value = "试卷阶段",name = "groupName",dataType = "String",required = true),
            @ApiImplicitParam(value = "学科",name = "subject",dataType = "String",required = true),
            @ApiImplicitParam(value = "题目数量",name = "count",dataType = "Integer",required = true),
            @ApiImplicitParam(value = "题目集合",name = "stemIdList",dataType = "List",required = true),
    })
    @PostMapping("updata")
    public Result updata(@Valid @RequestBody TestPaperUpdataDTO testPaperIUpdataDTO)
    {
//        更新试卷service
        testPaperService.updata(testPaperIUpdataDTO);
        return ClientUserResult.successResult(null);
    }

    /**
     * 试卷变为是否推荐
     * @param isRecommendChangeDTO 接收客户端的传递的推荐变更数据
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "试卷id",name = "id",dataType = "String",required = true),
            @ApiImplicitParam(value = "推荐值",name = "isRecommend",dataType = "String",required = true),
    })
    @ApiOperation(value = "试卷变为是否推荐", notes = "接收客户端的传递的推荐变更数据")
    @PostMapping("isRecommendChange")
    public Result isRecommendChange(@Valid @RequestBody IsRecommendChangeDTO isRecommendChangeDTO)
    {
//        试卷变为是否推荐
        testPaperService.isRecommendChange(isRecommendChangeDTO);
        return ClientUserResult.successResult(null);
    }

    /**
     * 添加试卷
     * @param testPaperAddDTO 接收客户端的添加试卷数据
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "试卷名",name = "name",dataType = "String",required = true),
            @ApiImplicitParam(value = "试卷年级",name = "gradeName",dataType = "String",required = true),
            @ApiImplicitParam(value = "试卷阶段",name = "groupName",dataType = "String",required = true),
            @ApiImplicitParam(value = "学科",name = "subject",dataType = "String",required = true),
            @ApiImplicitParam(value = "题目数量",name = "count",dataType = "Integer",required = true),
            @ApiImplicitParam(value = "题目集合",name = "stemIdList",dataType = "List",required = true),
    })
    @ApiOperation(value = "添加试卷", notes = "接收客户端的添加试卷数据")
    @PostMapping("add")
    public Result add(@Valid @RequestBody TestPaperAddDTO testPaperAddDTO)
    {
//        添加试卷service
        testPaperService.add(testPaperAddDTO);
        return ClientUserResult.successResult(null);
    }
}

