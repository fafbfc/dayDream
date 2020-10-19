package cn.edu.gdsdxy.sanlingerproject.core.result;

/**
 * Created by Administrator on 2020/10/12.
 */
public class ClientUserResult extends Result {

    public ClientUserResult(int code, String message) {
        super(code, message);
    }

    public static ClientUserResult NOT_LOGIN = new ClientUserResult(20003, "请登录后再访问");
    public static ClientUserResult PASSWORK_NOT_SAME = new ClientUserResult(20004, "密码不一致");
    public static ClientUserResult CLIENT_USER_EXIST = new ClientUserResult(20005, "用户已存在");
    public static ClientUserResult NOT_EXIST_OR_PASSWORK_MISS = new ClientUserResult(20006, "用户不存在或者密码错误");
}
