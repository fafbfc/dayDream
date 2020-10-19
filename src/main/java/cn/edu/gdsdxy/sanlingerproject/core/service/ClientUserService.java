package cn.edu.gdsdxy.sanlingerproject.core.service;

import cn.edu.gdsdxy.sanlingerproject.core.domain.ClientUser;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.ClientUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.ClientUserRegisterDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
public interface ClientUserService extends IService<ClientUser> {

    /**
     * service客户端用户注册
     * @param clientUserRegisterDTO 注册传递的数据
     */
    void register(ClientUserRegisterDTO clientUserRegisterDTO);

    /**
     * service客户端用户登录
     * @param clientUserLoginDTO 接收客户端用户的登录信息
     * @return
     */
    void login(ClientUserLoginDTO clientUserLoginDTO);

    /**
     * service客户端用户退出
     */
    void logout();
}
