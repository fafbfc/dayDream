package cn.edu.gdsdxy.sanlingerproject.admin.interceptor;

import cn.edu.gdsdxy.sanlingerproject.core.domain.AdminUser;
import cn.edu.gdsdxy.sanlingerproject.util.DayDreamRequestHolder;
import cn.edu.gdsdxy.sanlingerproject.util.ToolUtil;
import cn.edu.gdsdxy.sanlingerproject.util.annotation.AdminNeedLogin;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2020/10/12.
 */
@Component
public class AdminNeedLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
//        判断是否是controller的方法
        if( !(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        AdminNeedLogin adminNeedLogin = handlerMethod.getMethodAnnotation(AdminNeedLogin.class);

//        没有该注解，表示不需要登录后才访问
        if(adminNeedLogin == null) return true;

//        获取登录凭证
        AdminUser adminUser = DayDreamRequestHolder.getAdminUserLoginInSession();

        if( adminUser != null ) return true;

        ToolUtil.respWriteJson(response);
        return false;
    }
}
