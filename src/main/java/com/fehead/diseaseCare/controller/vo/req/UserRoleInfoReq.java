package com.fehead.diseaseCare.controller.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRoleInfoReq {

    @ApiModelProperty(value = "所在楼层")
    @NotNull(message = "所在楼层不能为空")
    private Integer floor;

    @ApiModelProperty(value = "东南西北")
    @NotBlank(message = "东南西北不能为空")
    private String area;

    @ApiModelProperty(value = "病人:所属于哪个医生，医生:所属于哪个boss")
    @NotBlank(message = "roomName不能为空")
    private String roomName;

    @ApiModelProperty(value = "病人或医生的位置")
    @NotBlank(message = "位置不能为空")
    private String seat;

    @ApiModelProperty(value = "所属科室id")
    @NotNull(message = "所属科室id不能为空")
    private Integer departmentId;

    @ApiModelProperty(value = "病人:所属于哪个医生，医生:所属于哪个boss")
    @NotNull(message = "所属不能为空")
    private Integer managerId;

}
