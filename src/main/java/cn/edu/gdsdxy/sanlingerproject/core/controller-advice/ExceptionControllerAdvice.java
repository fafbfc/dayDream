package cn.edu.gdsdxy.sanlingerproject.controllerAdvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice
{
    //  处理什么类型的异常
    @ExceptionHandler(Exception.class)
    public String handlException(Exception e, Model model)
    {
        model.addAttribute("say", "执行异常");
        model.addAttribute("msg", e.getMessage());
        return "/errorView"; //逻辑视图名称
    }
}