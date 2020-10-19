package cn.edu.gdsdxy.sanlingerproject.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2020/10/6.
 */
@Getter @Setter @ToString
@JsonInclude
public class Result<T> {

    private int code;
    private T data;
    private String message;

    public static Result BIND_PARAMETER_MISS = new Result(10000, "参数传入有误");
    public static Result SYS_ERROR = new Result(10001, "系统未知异常");

    public Result(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

//    成功的结果返回
    public static <T> Result successResult(T data)
    {
        Result result = new Result(200, null);
        if(data == null) return result;
        result.setData(data);
        return result;
    }

//    逻辑异常的结果返回
//    重新定义可以修改message
    public static <T> Result missResult(Result result, String message)
    {
        if(message == null || "".equals(message)) return result;
        Result bindParameterMissResult = new Result(result.getCode(), message);
        return bindParameterMissResult;
    }
}
