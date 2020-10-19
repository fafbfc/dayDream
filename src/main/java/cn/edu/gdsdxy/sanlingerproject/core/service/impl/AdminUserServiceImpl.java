package cn.edu.gdsdxy.sanlingerproject.core.service.impl;

import cn.edu.gdsdxy.sanlingerproject.core.domain.AdminUser;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.AdminUserRegisterDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.BaseDeleteDTO;
import cn.edu.gdsdxy.sanlingerproject.core.exception.AdminUserLogicException;
import cn.edu.gdsdxy.sanlingerproject.core.mapper.AdminUserMapper;
import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.service.AdminUserService;
import cn.edu.gdsdxy.sanlingerproject.util.DayDreamRequestHolder;
import cn.edu.gdsdxy.sanlingerproject.util.MD5Util;
import cn.edu.gdsdxy.sanlingerproject.util.ServiceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-06
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * service后台管理用户注册
     * @param adminUserRegisterDTO 注册传递的数据
     */
    @Override
    public void add(AdminUserRegisterDTO adminUserRegisterDTO) {

//        校验——密码和确认密码一致
//        不一致抛异常
        String password = adminUserRegisterDTO.getPassword();
        String confirmPassword = adminUserRegisterDTO.getConfirmPassword();

        if( !password.equals(confirmPassword) )
            throw new AdminUserLogicException(AdminUserResult.PASSWORK_NOT_SAME);

        String md5Password = MD5Util.MD5ToDb(password, null);

        String name = adminUserRegisterDTO.getName();

//        构造where条件
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);

//        查询单个后台管理用户
        AdminUser oneUser = this.getOne(queryWrapper);

//        用户存在，抛异常
        if( oneUser != null )
            throw new AdminUserLogicException(AdminUserResult.ADMIN_USER_EXIST, oneUser);

//        创建后台管理用户对象
        AdminUser adminUser = new AdminUser();
        adminUser.setName(name);
        adminUser.setPassword(md5Password);

//        初始化数据
        adminUser.setVersion(0);
        adminUser.setDeleted(0);

//        对象数据插入数据库
        this.save(adminUser);
    }

    /**
     * service后台管理用户登录
     * @param adminUserLoginDTO 接收后台管理用户的登录信息
     * @return
     */
    @Override
    public Map<String, String> login(AdminUserLoginDTO adminUserLoginDTO) {

        String name = adminUserLoginDTO.getName();
        String password = adminUserLoginDTO.getPassword();

//        获取密码的md5
        String md5Password = MD5Util.MD5ToDb(password, null);

//        构造where条件
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper();
        Map<String, String> whereMap = new HashMap();
        whereMap.put("name", name);
        whereMap.put("password", md5Password);
        queryWrapper.allEq(whereMap);

//        查询单个后台管理用户
        AdminUser oneUser = this.getOne(queryWrapper);

//        用户不存在，抛异常
        if(oneUser == null)
            throw new AdminUserLogicException(AdminUserResult.NOT_EXIST_OR_PASSWORK_MISS);

        oneUser.setPassword(null);

//        生成 token 放在 ServletContext
        String token = UUID.randomUUID().toString();

        DayDreamRequestHolder.setAdminUserLoginInServletContext(token, oneUser);
//        封装数据给前端
        Map<String, String> map = new HashMap();
        map.put("token", token);
        return map;
    }

    /**
     * service后台管理用户退出
     */
    @Override
    public void logout(String token) {

//        删除session的登录凭证
        DayDreamRequestHolder.removeAdminUserLoginInServletContext(token);
    }

    /**
     * 查询后台管理用户数据
     * @param pageNo    页码
     * @param pageSize  页面数据条数
     * @return
     */
    @Override
    public Map<String, Object> selectList(Integer pageNo, Integer pageSize, String keyword) {
//        构造where条件
        Page<AdminUser> page = new Page(pageNo, pageSize);

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper();
        if ( !Util.isEmptyString(keyword) )
            queryWrapper.like("name", keyword);

//        分页查询数据查询
        Page<AdminUser> adminUserPage = adminUserMapper.selectPage(page, queryWrapper);
//        分页查询数据封装给前端
        Map dataMap = ServiceUtil.selectPageToMap(adminUserPage);
        return dataMap;
    }

    /**
     * 后台管理用户删除用户接口
     * @param baseDeleteDTO 接收后台管理用户的删除用户信息
     * @return
     */
    @Override
    public void delete(BaseDeleteDTO baseDeleteDTO) {

//        mysql: 删除后台管理的用户数据
        this.removeById(baseDeleteDTO.getId());
    }

}
