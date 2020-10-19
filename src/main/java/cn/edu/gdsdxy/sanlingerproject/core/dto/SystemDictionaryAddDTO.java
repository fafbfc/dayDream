package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Administrator on 2020/10/12.
 */
//  后台管理的数据字典添加，接收数据字典添加操作的数据
@Getter @Setter @ToString
@NoArgsConstructor
public class SystemDictionaryAddDTO {

    @NotEmpty( message = "数据字典标题不能为空" )
    private String title;

    @NotEmpty( message = "数据字典唯一标识不能为空" )
    private String onlyTitle;

    @NotEmpty( message = "数据字典的描述不能为空" )
    private String describe;
}
