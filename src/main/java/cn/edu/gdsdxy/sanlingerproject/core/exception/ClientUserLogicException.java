package cn.edu.gdsdxy.sanlingerproject.core.exception;

import cn.edu.gdsdxy.sanlingerproject.core.result.ClientUserResult;

/**
 * Created by Administrator on 2020/10/12.
 */
public class ClientUserLogicException extends RuntimeException
{
    private String message;         //  错误信息
    private ClientUserResult clientUserResult;  //  错误结果
    private Object[] data;          //  错误数据捕获

    public ClientUserLogicException(ClientUserResult clientUserResult, Object... data)
    {
        super();
        this.clientUserResult = clientUserResult;
        this.data = data;
    }
}
