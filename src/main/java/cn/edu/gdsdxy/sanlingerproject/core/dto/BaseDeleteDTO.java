package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2020/10/19.
 */
//  后台管理的删除DTO，接收删除信息
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseDeleteDTO {

    @NotNull( message = "后台管理的用户id不能为空" )
    private Long id;    // 试卷id
}
