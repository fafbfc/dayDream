package cn.edu.gdsdxy.sanlingerproject.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Administrator on 2020/10/18.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestPaperAddDTO {

    @NotEmpty(message = "试卷名称不能为空")
    private String name;        //  试卷名称

    @NotEmpty(message = "试卷年级不能为空")
    private String gradeName;   //  试卷的年级

    @NotEmpty(message = "试卷阶段分类不能为空")
    private String groupName;   //  试卷年级分类

    @NotEmpty(message = "学科不能为空")
    private String subject;     //  学科

    @NotNull(message = "题目数量不能为空")
    private Integer count;      //  题目数量

    @NotEmpty(message = "题目集合不能为空")
    private List stemIdList;  //  题目集合

    public String getStemIdList() {
        String stemIdListStr = this.stemIdList.toString();
        return stemIdListStr;
    }
}
