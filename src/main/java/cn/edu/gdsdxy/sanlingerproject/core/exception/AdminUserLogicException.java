package cn.edu.gdsdxy.sanlingerproject.core.exception;

import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminUserLogicException extends RuntimeException
{
    private String message;         //  错误信息
    private AdminUserResult adminUserResult;  //  错误结果
    private Object[] data;          //  错误数据捕获

    public AdminUserLogicException(AdminUserResult adminUserResult, Object... data)
    {
        super();
        this.adminUserResult = adminUserResult;
        this.data = data;
    }
}