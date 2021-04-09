package com.fehead.diseaseCare.controller.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientCollectDataResp {

    @ApiModelProperty(value = "病人id")
    private Integer patientId;

    @ApiModelProperty(value = "病人心率")
    private Integer heartRate;

    @ApiModelProperty(value = "血压高压")
    private Integer bloodPressureHigh;

    @ApiModelProperty(value = "血压低压")
    private Integer bloodPressureLow;

    @ApiModelProperty(value = "创建日期")
    private String createDateFormat;

    @ApiModelProperty(value = "血糖指数")
    private Double bloodGlucose;

    @ApiModelProperty(value = "病人体表温度")
    private String temperature;

    @ApiModelProperty(value = "病人地理位置")
    private String geoPosition;
}
