package com.fehead.diseaseCare.entities.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserIdRoleInfo对象", description="")
public class UserIdRoleInfo {

    @ApiModelProperty(value = "userId")
    private Integer userId;

    @ApiModelProperty(value = "role")
    private Integer role;
}
