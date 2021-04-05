package com.fehead.diseaseCare.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
    private String medicineName;

    @ApiModelProperty(value = "药品单价")
    private BigDecimal medicinePrice;

    @ApiModelProperty(value = "药品描述信息")
    private String medicineInfo;

    @ApiModelProperty(value = "药品数量")
    private Integer medicineRepertory;


}
