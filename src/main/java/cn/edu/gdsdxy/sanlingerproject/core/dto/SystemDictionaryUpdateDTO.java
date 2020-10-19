package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Administrator on 2020/10/12.
 */
//  后台管理的数据字典更新or删除，接收数据字典更新or删除操作的数据
@Getter @Setter @ToString
@NoArgsConstructor
public class SystemDictionaryUpdateDTO {

    @NotEmpty( message = "数据字典标题id不能为空" )
    private Long id;

    private String title;

    private String onlyTitle;

    private String describe;
}
