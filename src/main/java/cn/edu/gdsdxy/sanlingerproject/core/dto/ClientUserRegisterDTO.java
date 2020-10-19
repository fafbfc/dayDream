package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Administrator on 2020/10/6.
 */
//  客户端用户的注册DTO，用户接收注册信息
@Getter @Setter @ToString
@NoArgsConstructor
public class ClientUserRegisterDTO {

    @NotEmpty( message = "客户端用户名不能为空" )
    private String name;            //  后台管理用户名

    @NotEmpty( message = "客户端密码不能为空" )
    private String password;        //  后台管理密码

    @NotEmpty( message = "客户端确认密码不能为空" )
    private String confirmPassword; //  后台管理确认密码
}
