package com.fehead.diseaseCare.controller.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PatientCollectDataResp {

    @ApiModelProperty(value = "病人id")
    private Integer patientId;

    @ApiModelProperty(value = "病人心率")
    private String heartRate;

    @ApiModelProperty(value = "创建日期")
    private String createDateFormat;

    @ApiModelProperty(value = "病人体表温度")
    private String temperature;

    @ApiModelProperty(value = "血氧")
    private String bloodOxygen;

    @ApiModelProperty(value = "病人地理经度")
    private String longitude;

    @ApiModelProperty(value = "病人地理纬度")
    private String latitude;
}
