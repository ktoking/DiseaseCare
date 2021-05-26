package com.fehead.diseaseCare.controller.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DoctorInfoReq {

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "性别：1男0女")
    @NotNull(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(value = "生日")
    @NotBlank(message = "生日不能为空")
    private String birthday;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "所在楼层")
    @NotNull(message = "所在楼层不能为空")
    private Integer floor;

    @ApiModelProperty(value = "所在办公室")
    @NotBlank(message = "所在办公室不能为空")
    private String room;

    @ApiModelProperty(value = "所在座位")
    @NotBlank(message = "所在座位不能为空")
    private String seat;

    @ApiModelProperty(value = "所在科室")
    @NotNull(message = "所在科室不能为空")
    private Integer departmentId;

    @ApiModelProperty(value = "简介信息")
    private String info;

}
