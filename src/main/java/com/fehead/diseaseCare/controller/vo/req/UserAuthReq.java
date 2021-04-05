package com.fehead.diseaseCare.controller.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserAuthReq {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
