package cn.edu.gdsdxy.sanlingerproject.api.controller;


import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.DoStemRecordService;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.ClientNeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/doStemRecord")
public class DoStemRecordController {

    @Autowired
    private DoStemRecordService doStemRecordService;

    /**
     * 根据id，查询用户做题记录
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return
     */
    @ClientNeedLogin
    @GetMapping
    public Result selectList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize)
    {
//        根据id，查询用户做题记录
        Map dataMap = doStemRecordService.selectList(pageNo, pageSize);
        return ClientUserResult.successResult(dataMap);
    }

}

