package cn.edu.gdsdxy.sanlingerproject.api.controller;


import cn.edu.gdsdxy.sanlingerproject.core.domain.DoPaperAnswer;
import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.DoPaperAnswerService;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.ClientNeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@RestController
@RequestMapping("doPaperAnswer")
public class DoPaperAnswerController {

    @Autowired
    private DoPaperAnswerService doPaperAnswerService;

    /**
     * 根据用户id和试卷id，查询做题情况
     * @param paperId 试卷id
     * @return
     */
    @ClientNeedLogin
    @GetMapping("select/{paper_id}")
    public Result selectListByUserIdAndPaperId(@PathVariable("paper_id") Long paperId)
    {
//        根据用户id和试卷id，查询做题情况
        List<DoPaperAnswer> doPaperAnswerList = doPaperAnswerService.selectListByUserIdAndPaperId(paperId);
        return ClientUserResult.successResult(doPaperAnswerList);
    }

}

