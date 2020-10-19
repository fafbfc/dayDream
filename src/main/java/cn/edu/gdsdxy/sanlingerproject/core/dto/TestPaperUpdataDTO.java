package cn.edu.gdsdxy.sanlingerproject.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class TestPaperUpdataDTO extends TestPaperAddDTO {

    @NotNull( message = "试卷id不能为空" )
    private Long id;    // 试卷id

}
