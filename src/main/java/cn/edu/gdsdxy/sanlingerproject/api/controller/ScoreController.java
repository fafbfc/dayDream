package cn.edu.gdsdxy.sanlingerproject.api.controller;


import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.ScoreService;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.ClientNeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 查询用户的成绩，科根据筛选查询
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @param map 封装数据，包含clientUserId（客户端用户id）
     *            , gradeName（年级）, groupName（年级分类）, subject（学科）
     * @return
     */
    @ClientNeedLogin
    @PostMapping("selectList")
    public Result selectList(@RequestParam(defaultValue = "1") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            , @RequestParam Map map )
    {
//        scoreService.selectList();
        return ClientUserResult.successResult(null);
    }

}

