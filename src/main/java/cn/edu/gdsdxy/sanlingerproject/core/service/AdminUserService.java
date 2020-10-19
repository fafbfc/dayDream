package cn.edu.gdsdxy.sanlingerproject.core.service;

import cn.edu.gdsdxy.sanlingerproject.core.domain.AdminUser;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserRegisterDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.BaseDeleteDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-06
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * service后台管理用户注册
     * @param adminUserRegisterDTO 注册传递的数据
     */
    void add(AdminUserRegisterDTO adminUserRegisterDTO);

    /**
     * service后台管理用户登录
     * @param adminUserLoginDTO 接收后台管理用户的登录信息
     * @return
     */
    void login(AdminUserLoginDTO adminUserLoginDTO);

    /**
     * service后台管理用户退出
     */
    void logout();

    /**
     * 查询后台管理用户数据
     * @param pageNo    页码
     * @param pageSize  页面数据条数
     * @return
     */
    Map<String, Object> selectList(Integer pageNo, Integer pageSize, String keyword);

    /**
     * 后台管理用户删除用户接口
     * @param baseDeleteDTO 接收后台管理用户的删除用户信息
     * @return
     */
    void delete(BaseDeleteDTO baseDeleteDTO);
}
