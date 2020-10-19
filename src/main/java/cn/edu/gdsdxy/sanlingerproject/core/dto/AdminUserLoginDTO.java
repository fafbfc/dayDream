package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Administrator on 2020/10/12.
 */
//  后台管理的用户登录DTO，接收登录信息
@Getter @Setter @ToString
@NoArgsConstructor
public class AdminUserLoginDTO {

    @NotEmpty( message = "后台管理用户名不能为空" )
    private String name;            //  后台管理用户名

    @NotEmpty( message = "后台管理密码不能为空" )
    private String password;        //  后台管理密码
}
