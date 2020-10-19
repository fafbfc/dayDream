package cn.edu.gdsdxy.sanlingerproject.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TestPaper对象", description = "")
public class TestPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "试卷名称")
    private String name;

    @ApiModelProperty(value = "试卷的年级")
    private String gradeName;

    @ApiModelProperty(value = "试卷年级分类")
    private String groupName;

    @ApiModelProperty(value = "学科")
    private String subject;

    @ApiModelProperty(value = "题目数量")
    private Integer count;

    @ApiModelProperty(value = "题目集合，用\",\"分开")
    private String stemIdList;

    @ApiModelProperty(value = "是否推荐，0-不推荐，1-推荐")
    private Integer isRecommend;

    @JsonIgnore
    @ApiModelProperty(value = "试卷数据的创建数据时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "试卷数据的更新数据时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "试卷数据的乐观锁版本标志")
    @Version
    private Integer version;

    @JsonIgnore
    @ApiModelProperty(value = "试卷数据的删除标志，0-没删除，1-删除")
    @TableLogic
    private Integer deleted;

//    重写isRecommend，避免空值
    public Integer getIsRecommend() {
        if (this.isRecommend == null)
            return 0;
        return this.isRecommend;
    }

//    修改stemIdList，去掉[]
    public void setStemIdList(String stemIdList){
        boolean flag= stemIdList.startsWith("[");
        if( flag ) {
            this.stemIdList = stemIdList.substring(1, stemIdList.length()-1);
        }
        this.stemIdList = stemIdList;
    }
    public String getStemIdList(){
        if( stemIdList == null ) return null;
        boolean flag= stemIdList.startsWith("[");
        if( flag ) {
            return stemIdList.substring(1, stemIdList.length()-1);
        }
        return this.stemIdList;
    }

}
