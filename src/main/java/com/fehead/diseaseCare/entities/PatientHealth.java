package com.fehead.diseaseCare.entities;

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
 * @since 2021-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PatientHealth对象", description="")
public class PatientHealth implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "病人id")
    private Integer patientId;

    @ApiModelProperty(value = "病人心率")
    private Integer heartRate;

    @ApiModelProperty(value = "血压高压")
    private Integer bloodPressureHigh;

    @ApiModelProperty(value = "血压低压")
    private Integer bloodPressureLow;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "血糖指数")
    private Double bloodGlucose;

    @ApiModelProperty(value = "病人体表温度")
    private String temperature;

    @ApiModelProperty(value = "病人地理位置")
    private String geoPosition;


}
