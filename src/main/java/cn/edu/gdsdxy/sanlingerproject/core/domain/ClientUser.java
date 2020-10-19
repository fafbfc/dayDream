package cn.edu.gdsdxy.sanlingerproject.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(value="ClientUser对象", description="")
public class ClientUser implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户端的用户主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "客户端的用户名")
    private String name;

    @ApiModelProperty(value = "客户端的用户密码")
    private String password;

    @ApiModelProperty(value = "客户端的用户头像")
    private String headPic;

    @ApiModelProperty(value = "客户端的用户数据的创建数据时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "客户端的用户数据的更新数据时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "客户端的用户数据的乐观锁版本标志")
    @Version
    private Integer version;

    @JsonIgnore
    @ApiModelProperty(value = "客户端的用户数据的删除标志，0-没删除，1-删除")
    @TableLogic
    private Integer deleted;


}
