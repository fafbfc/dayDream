package cn.edu.gdsdxy.sanlingerproject.admin.controller;


import cn.edu.gdsdxy.sanlingerproject.core.domain.AdminUser;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserRegisterDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.BaseDeleteDTO;
import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.AdminUserService;
import cn.edu.gdsdxy.sanlingerproject.util.DayDreamRequestHolder;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.AdminNeedLogin;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-06
 */
//    后台管理用户接口
@RestController
@RequestMapping("adminUser")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 查询后台管理用户
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @param keyword 关键字，查询包含的用户名
     * @return
     */

    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name = "pageNo",dataType = "Integer",required = false),
            @ApiImplicitParam(value = "页面大小",name = "pageSize",dataType = "Integer",required = false),
            @ApiImplicitParam(value = "关键字",name = "keyword",dataType = "String",required = false),
    })
    @ApiOperation(value = "查询后台管理用户", notes = "接收客户端的传递的查询条件数据")
    @AdminNeedLogin //  登录拦截的注解
    @GetMapping("selectList")
    public Result selectList(@RequestParam(defaultValue = "1") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,  @RequestParam( required = false ) String keyword )
    {
//        查询后台管理用户
        Map dataMap = adminUserService.selectList(pageNo, pageSize, keyword);
        return ClientUserResult.successResult(dataMap);
    }

    /**
     * 后台管理用户删除用户接口
     * @param baseDeleteDTO 接收后台管理用户的删除用户信息
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "后台管理的用户id",name = "id",dataType = "String",required = true),
    })
    @AdminNeedLogin //  登录拦截的注解
    @ApiOperation(value = "删除用户", notes = "接收后台管理的删除用户数据")
    @PostMapping("delete")
    public Result delete(@Valid @RequestBody BaseDeleteDTO baseDeleteDTO)
    {
//        后台管理用户删除用户接口
        adminUserService.delete(baseDeleteDTO);
        return AdminUserResult.successResult(null);
    }

    /**
     * 后台管理用户添加接口
     * @param adminUserRegisterDTO 接收后台管理用户的添加信息
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "后台管理用户名",name = "name",dataType = "String",required = true),
            @ApiImplicitParam(value = "后台管理密码",name = "password",dataType = "String",required = true),
            @ApiImplicitParam(value = "后台管理确认密码",name = "confirmPassword",dataType = "String",required = true),
    })
    @AdminNeedLogin //  登录拦截的注解
    @ApiOperation(value = "添加用户", notes = "接收后台管理的添加用户数据")
    @PostMapping("add")
    public Result add(@Valid @RequestBody AdminUserRegisterDTO adminUserRegisterDTO)
    {
        adminUserService.add(adminUserRegisterDTO);
        return AdminUserResult.successResult(null);
    }

    /**
     * 后台管理用户登录接口
     * @param adminUserLoginDTO 接收后台管理用户的登录信息
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "后台管理用户名",name = "name",dataType = "String",required = true),
            @ApiImplicitParam(value = "后台管理密码",name = "password",dataType = "String",required = true),
    })
    @ApiOperation(value = "用户登录", notes = "接收后台管理的用户登录数据")
    @PostMapping("login")
    public Result login(@Valid @RequestBody AdminUserLoginDTO adminUserLoginDTO)
    {
//        后台管理用户登录接口
        Map<String, String> map = adminUserService.login(adminUserLoginDTO);
        return AdminUserResult.successResult(map);
    }

    /**
     * 后台管理获取用户基础信息接口
     * @return
     */
    @ApiOperation(value = "获取用户基础", notes = "接收后台管理的用户token数据")
    @GetMapping("info")
    public Result info(String token)
    {
        AdminUser adminUser = DayDreamRequestHolder.getAdminUserLoginInServletContext(token);
//        给个写死的 avatar，introduction，roles
        Map<String, Object> map = new HashMap();
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("introduction", "我是超级管理员");
        map.put("name", adminUser.getName());
        map.put("roles", new String[]{"admin"});

        return AdminUserResult.successResult(map);
    }

    /**
     * 后台管理用户退出接口
     * @return
     */
    @AdminNeedLogin //  登录拦截的注解
    @ApiOperation(value = "用户退出", notes = "接收后台管理的用户退出数据")
    @PostMapping("logout")
    public Result logout(String token)
    {
//        后台管理用户退出接口
        adminUserService.logout(token);
        return AdminUserResult.successResult(null);
    }
}

