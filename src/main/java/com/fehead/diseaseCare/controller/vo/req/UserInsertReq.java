package com.fehead.diseaseCare.controller.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserInsertReq {
    @ApiModelProperty(value = "姓名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "性别：1男0女")
    @NotNull
    private Integer sex;

    @ApiModelProperty(value = "账号密码")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "生日")
    @NotBlank
    private LocalDateTime birthday;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态 0正常(默认)  1注销 2住院 ")
    private Integer status;

    @ApiModelProperty(value = "账号创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "所属身份id")
    private Integer belongId;

    @ApiModelProperty(value = "0:代表病人 1:代表医生")
    private Integer role;

    @ApiModelProperty(value = "余额")
    private BigDecimal price;

}
