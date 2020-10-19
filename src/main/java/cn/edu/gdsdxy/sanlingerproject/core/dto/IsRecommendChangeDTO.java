package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2020/10/18.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class IsRecommendChangeDTO {

    @NotNull( message = "试卷id不能为空" )
    private Long id;    //  试卷id

    @NotNull( message = "推荐值不能为空" )
    private Integer isRecommend;    //  推荐值
}
