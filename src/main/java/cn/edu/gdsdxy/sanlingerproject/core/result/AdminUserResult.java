package cn.edu.gdsdxy.sanlingerproject.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by 刘海涛 on 2020/10/6.
 * 管理员用户信息统一数据管理
 */
@Getter @Setter @ToString
@JsonInclude
public class AdminUserResult extends Result {

    public AdminUserResult(int code, String message) {
        super(code, message);
    }

    public static AdminUserResult HTTP_SEND_MISS = new AdminUserResult(10002, "服务端发送http请求失败");
    public static AdminUserResult NOT_LOGIN = new AdminUserResult(10003, "请登录后再访问");
    public static AdminUserResult PASSWORK_NOT_SAME = new AdminUserResult(10004, "密码不一致");
    public static AdminUserResult ADMIN_USER_EXIST = new AdminUserResult(10005, "用户已存在");
    public static AdminUserResult NOT_EXIST_OR_PASSWORK_MISS = new AdminUserResult(10006, "用户不存在或者密码错误");
}
