package cn.edu.gdsdxy.sanlingerproject.core.advice;

import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import cn.edu.gdsdxy.sanlingerproject.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import cn.edu.gdsdxy.sanlingerproject.core.exception.AdminUserLogicException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//  统一异常管理
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice
{

//  处理数据绑定时异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerBindException(MethodArgumentNotValidException e)
    {
        log.error("数据绑定异常", e);
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        Result bindParameterMissResult = Result.missResult(Result.BIND_PARAMETER_MISS, defaultMessage);
        return bindParameterMissResult;
    }

//  处理管理员用户的逻辑异常
    @ExceptionHandler(AdminUserLogicException.class)
    public Result handlerBindException(AdminUserLogicException e)
    {
        log.error("后台管理用户数据逻辑异常", e);
        Result result = AdminUserResult.missResult(e.getAdminUserResult(), null);
        log.error("异常原因" + ToolUtil.ObjToJsonStr(result) + "，数据为：" + ToolUtil.ObjToJsonStr(e.getData()));
        return result;
    }

//     系统未知异常
    @ExceptionHandler(Exception.class)
    public Result handlerBindException(Exception e)
    {
        log.error("用户服务系统未知异常", e);
        return Result.missResult(Result.SYS_ERROR, e.toString());
    }
}
