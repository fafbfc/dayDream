package cn.edu.gdsdxy.sanlingerproject.admin.controller;


import cn.edu.gdsdxy.sanlingerproject.core.domain.SystemDictionary;
import cn.edu.gdsdxy.sanlingerproject.core.dto.SystemDictionaryAddDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.SystemDictionaryUpdateDTO;
import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.SystemDictionaryService;
import cn.edu.gdsdxy.sanlingerproject.util.ToolUtil;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.AdminNeedLogin;
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
@RestController
@RequestMapping("systemDictionary")
public class SystemDictionaryController {

    @Autowired
    private SystemDictionaryService systemDictionaryService;

    /**
     * 数据字典添加操作
     * @param systemDictionaryAddDTO 接收数据字典添加操作的数据
     * @return
     */
//    @AdminNeedLogin
    @PostMapping("add")
    public Result add(@Valid @RequestBody SystemDictionaryAddDTO systemDictionaryAddDTO )
    {
//        数据字典数据添加
        SystemDictionary systemDictionary = ToolUtil.ObjToObj(systemDictionaryAddDTO, SystemDictionary.class);
        systemDictionaryService.save(systemDictionary);
        return AdminUserResult.successResult(null);
    }

    /**
     * 数据字典查询操作
     * @param pageNo    页码
     * @param pageSize  页面数据条数
     * @param keyword   关键字
     * @return
     */
//    @AdminNeedLogin
    @GetMapping("selectList")
    public Result selectList( @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize
            , @RequestParam(defaultValue = "") String keyword)
    {
//        数据字典查询操作
        Map<String, Object> dataMap = systemDictionaryService.selectList(pageNo, pageSize, keyword);
        return AdminUserResult.successResult(dataMap);
    }

    /**
     * 数据字典更新操作
     * @param systemDictionaryUpdateDTO 接收数据字典更新操作的数据
     * @return
     */
//    @AdminNeedLogin
    @PostMapping("update")
    public Result update(@Valid @RequestBody SystemDictionaryUpdateDTO systemDictionaryUpdateDTO )
    {
//        数据字典数据更新操作
        SystemDictionary systemDictionary = ToolUtil.ObjToObj(systemDictionaryUpdateDTO, SystemDictionary.class);
        systemDictionaryService.updateById(systemDictionary);
        return AdminUserResult.successResult(null);
    }

    /**
     * 数据字典添加操作
     * @param systemDictionaryUpdateDTO 接收数据字典删除操作的数据
     * @return
     */
//    @AdminNeedLogin
    @PostMapping("delete")
    public Result delete(@Valid @RequestBody SystemDictionaryUpdateDTO systemDictionaryUpdateDTO )
    {
//        数据字典数据添加
        SystemDictionary systemDictionary = ToolUtil.ObjToObj(systemDictionaryUpdateDTO, SystemDictionary.class);
        systemDictionaryService.removeById(systemDictionary);
        return AdminUserResult.successResult(null);
    }

}

