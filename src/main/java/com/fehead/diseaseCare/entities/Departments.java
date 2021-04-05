package com.fehead.diseaseCare.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Departments对象", description="")
public class Departments implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "小类科室名称")
    @NotBlank(message = "科室名称不能为空")
    private String name;

    @ApiModelProperty(value = "几层")
    @NotBlank(message = "科室层数不能为空")
    private String floor;

    @ApiModelProperty(value = "具体位置")
    @NotBlank(message = "科室具体位置不能为空")
    private String position;

    @ApiModelProperty(value = "大类")
    @NotBlank(message = "科室大类不能为空")
    private String type;


}
