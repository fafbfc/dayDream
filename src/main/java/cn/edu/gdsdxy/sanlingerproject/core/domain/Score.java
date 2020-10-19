package cn.edu.gdsdxy.sanlingerproject.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Score对象", description="")
public class Score implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "分数数据的主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关联用户数据的主键id")
    private Long usersId;

    @ApiModelProperty(value = "关联试卷数据的主键id")
    private Long paperId;

    @ApiModelProperty(value = "分数")
    private String fraction;

    @ApiModelProperty(value = "分数数据的创建数据时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "分数数据的更新数据时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "分数数据的乐观锁版本标志")
    @Version
    private Integer version;

    @ApiModelProperty(value = "分数数据的删除标志，0-没删除，1-删除")
    @TableLogic
    private Integer deleted;


}
