package cn.edu.gdsdxy.sanlingerproject.core.service.impl;

import cn.edu.gdsdxy.sanlingerproject.core.domain.ClientUser;
import cn.edu.gdsdxy.sanlingerproject.core.domain.ClientUser;
import cn.edu.gdsdxy.sanlingerproject.core.dto.ClientUserLoginDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.ClientUserRegisterDTO;
import cn.edu.gdsdxy.sanlingerproject.core.exception.ClientUserLogicException;
import cn.edu.gdsdxy.sanlingerproject.core.exception.ClientUserLogicException;
import cn.edu.gdsdxy.sanlingerproject.core.mapper.ClientUserMapper;
import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.service.ClientUserService;
import cn.edu.gdsdxy.sanlingerproject.util.DayDreamRequestHolder;
import cn.edu.gdsdxy.sanlingerproject.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@Service
public class ClientUserServiceImpl extends ServiceImpl<ClientUserMapper, ClientUser> implements ClientUserService {

    /**
     * service客户端用户注册
     * @param clientUserRegisterDTO 注册传递的数据
     */
    @Override
    public void register(ClientUserRegisterDTO clientUserRegisterDTO) {

//        校验——密码和确认密码一致
//        不一致抛异常
        String password = clientUserRegisterDTO.getPassword();
        String confirmPassword = clientUserRegisterDTO.getConfirmPassword();

        if( !password.equals(confirmPassword) )
            throw new ClientUserLogicException(ClientUserResult.PASSWORK_NOT_SAME);

        String md5Password = MD5Util.MD5ToDb(password, null);

        String name = clientUserRegisterDTO.getName();

//        构造where条件
        QueryWrapper<ClientUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);

//        查询单个后台管理用户
        ClientUser oneUser = this.getOne(queryWrapper);

//        用户存在，抛异常
        if( oneUser != null )
            throw new ClientUserLogicException(ClientUserResult.CLIENT_USER_EXIST, oneUser);

//        创建后台管理用户对象
        ClientUser clientUser = new ClientUser();
        clientUser.setName(name);
        clientUser.setPassword(md5Password);

//        对象数据插入数据库
        this.save(clientUser);
    }

    /**
     * service客户端用户登录
     * @param clientUserLoginDTO 接收客户端用户的登录信息
     * @return
     */
    @Override
    public void login(ClientUserLoginDTO clientUserLoginDTO) {

        String name = clientUserLoginDTO.getName();
        String password = clientUserLoginDTO.getPassword();

//        获取密码的md5
        String md5Password = MD5Util.MD5ToDb(password, null);

//        构造where条件
        QueryWrapper<ClientUser> queryWrapper = new QueryWrapper();
        Map<String, String> whereMap = new HashMap();
        whereMap.put("name", name);
        whereMap.put("password", md5Password);
        queryWrapper.allEq(whereMap);

//        查询单个后台管理用户
        ClientUser oneUser = this.getOne(queryWrapper);

//        用户不存在，抛异常
        if(oneUser == null)
            throw new ClientUserLogicException(ClientUserResult.NOT_EXIST_OR_PASSWORK_MISS);

//        以uuid为token，不是分布式，不用token
//        String token = UUID.randomUUID().toString();

//        把登录凭证放到session上
        oneUser.setPassword(null);
        DayDreamRequestHolder.setClientUserLoginInSession(oneUser);
    }

    /**
     * service客户端用户退出
     */
    @Override
    public void logout() {

//        删除session的登录凭证
        DayDreamRequestHolder.removeClientUserLoginInSession();
    }
}
