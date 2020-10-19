package cn.edu.gdsdxy.sanlingerproject.api.controller;


import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserRegisterDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.ClientUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.ClientUserRegisterDTO;
import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.core.service.AdminUserService;
import cn.edu.gdsdxy.sanlingerproject.core.service.ClientUserService;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.AdminNeedLogin;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.ClientNeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@RestController
@RequestMapping("clientUser")
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;

    /**
     * 客户端用户注册接口
     * @param clientUserRegisterDTO 接收客户端用户的注册信息
     * @return
     */
    @PostMapping("register")
    public Result register(@Valid @RequestBody ClientUserRegisterDTO clientUserRegisterDTO)
    {
        clientUserService.register(clientUserRegisterDTO);
        return AdminUserResult.successResult(null);
    }

    /**
     * 客户端用户登录接口
     * @param clientUserLoginDTO 接收后台管理用户的登录信息
     * @return
     */
    @PostMapping("login")
    public Result login(@Valid @RequestBody ClientUserLoginDTO clientUserLoginDTO)
    {
        clientUserService.login(clientUserLoginDTO);
        return AdminUserResult.successResult(null);
    }

    /**
     * 客户端用户退出接口
     * @return
     */
    @ClientNeedLogin
    @PostMapping("logout")
    public Result logout()
    {
        clientUserService.logout();
        return AdminUserResult.successResult(null);
    }

}

