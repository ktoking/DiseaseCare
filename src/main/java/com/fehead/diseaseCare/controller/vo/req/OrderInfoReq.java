package com.fehead.diseaseCare.controller.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class OrderInfoReq {

    @ApiModelProperty(value = "医生ID")
    @NotBlank(message = "医生不能为空")
    private Integer doctorId;

    @ApiModelProperty(value = "科室ID")
    @NotBlank(message = "科室不能为空")
    private Integer deptId;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
