package cn.edu.gdsdxy.sanlingerproject.util;

import cn.edu.gdsdxy.sanlingerproject.core.domain.AdminUser;
import cn.edu.gdsdxy.sanlingerproject.core.domain.ClientUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class DayDreamRequestHolder
{
    private static final String CLIENT_USER_LOGIN_TOKEN = "CLIENT_USER_LOGIN_TOKEN";

    public static HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static HttpSession getSession()
    {
        HttpSession session = getRequest().getSession();
        return session;
    }

    public static ServletContext getServletContext()
    {
        ServletContext servletContext = getRequest().getServletContext();
        return servletContext;
    }

    public static void setAdminUserLoginInServletContext(String token, AdminUser adminUser)
    {
        getServletContext().setAttribute(token, adminUser);
    }

    public static AdminUser getAdminUserLoginInServletContext(String token)
    {
        AdminUser adminUser = (AdminUser)getServletContext().getAttribute(token);
        return adminUser;
    }

    public static void removeAdminUserLoginInServletContext(String token)
    {
        getServletContext().removeAttribute(token);
    }

    public static void setClientUserLoginInSession(ClientUser clientUser)
    {
        getSession().setAttribute(CLIENT_USER_LOGIN_TOKEN, clientUser);
    }

    public static ClientUser getClientUserLoginInSession()
    {
        ClientUser clientUser = (ClientUser)getSession().getAttribute(CLIENT_USER_LOGIN_TOKEN);
        return clientUser;
    }

    public static void removeClientUserLoginInSession()
    {
        getSession().removeAttribute(CLIENT_USER_LOGIN_TOKEN);
    }
}