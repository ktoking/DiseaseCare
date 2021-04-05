package com.fehead.diseaseCare.controller.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UserInsertReq {


    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "性别：1男0女")
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty(value = "账号密码")
    @NotBlank(message = "账号密码不能为空")
    private String password;

    @ApiModelProperty(value = "生日")
    @NotNull(message = "生日不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "0:代表病人 1:代表医生")
    private Integer role;

}
