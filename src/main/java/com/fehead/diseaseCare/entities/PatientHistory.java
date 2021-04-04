package com.fehead.diseaseCare.entities;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PatientHistory对象", description="")
public class PatientHistory implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "病人id")
    private Integer patientId;

    @ApiModelProperty(value = "医生id")
    private Integer doctorId;

    @ApiModelProperty(value = "描述症状")
    private String patientSymptoms;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "药品清单id 默认-1不开药")
    private Integer medicineItemId;

    @ApiModelProperty(value = "此次总价")
    private BigDecimal price;


}
