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
@ApiModel(value="DoStemRecord对象", description="")
public class DoStemRecord implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "做题记录的主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键，题目主键id")
    private Long stemId;

    @ApiModelProperty(value = "外键，客户端用户主键id")
    private Long clientUserId;

    @ApiModelProperty(value = "用户的答案")
    private String answer;

    @ApiModelProperty(value = "正确答案")
    private String rightAnswer;

    @ApiModelProperty(value = "0-对，1-错")
    private Integer rightOrWrong;

    @ApiModelProperty(value = "用户做题记录数据的创建数据时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "用户做题记录数据的更新数据时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "用户做题记录数据的乐观锁版本标志")
    @Version
    private Integer version;

    @ApiModelProperty(value = "用户做题记录数据的删除标志，0-没删除，1-删除")
    @TableLogic
    private String deleted;


}
