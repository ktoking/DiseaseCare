package com.fehead.diseaseCare.entities.model;

import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.utility.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class UserBaseInfo {

    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别：1男0女")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "手机号")
    private Integer age;

    @ApiModelProperty(value = "所在楼层")
    private Integer floor;

    @ApiModelProperty(value = "东南西北")
    private String area;

    @ApiModelProperty(value = "病人代表病房号，医生代表办公室号")
    private String roomName;

    @ApiModelProperty(value = "病人或医生的位置")
    private String seat;

    @ApiModelProperty(value = "科室信息")
    private Departments departments;
}
