package com.fehead.diseaseCare.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

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
@ApiModel(value="MedicineInfo对象", description="")
public class MedicineInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "药品名称")
    @NotBlank(message = "药品名称不能为空")
    private String medicineName;

    @ApiModelProperty(value = "药品单价")
    @NotNull(message = "药品单价不能为空")
    @Valid
    private BigDecimal medicinePrice;

    @ApiModelProperty(value = "药品描述信息")
    @NotBlank(message = "药品详情信息不能为空")
    private String medicineInfo;

    @ApiModelProperty(value = "药品数量")
    @NotNull(message = "药品库存不能为空")
    private Integer medicineRepertory;


}
