package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Administrator on 2020/10/6.
 */
//  后台管理用户的添加DTO，用户接收添加信息
@Getter @Setter @ToString
@NoArgsConstructor
public class AdminUserRegisterDTO {

    @NotEmpty( message = "后台管理用户名不能为空" )
    private String name;            //  后台管理用户名

    @NotEmpty( message = "后台管理密码不能为空" )
    private String password;        //  后台管理密码

    @NotEmpty( message = "后台管理确认密码不能为空" )
    private String confirmPassword; //  后台管理确认密码
}
