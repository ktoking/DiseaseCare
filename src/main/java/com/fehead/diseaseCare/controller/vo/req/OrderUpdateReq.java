package com.fehead.diseaseCare.controller.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class OrderUpdateReq {

    @ApiModelProperty(value = "病人ID")
    @NotNull(message = "病人不能为空")
    private Integer patientId;

    @ApiModelProperty(value = "等待序号")
    @NotNull(message = "等待序号不能为空")
    private Integer number;

    @ApiModelProperty(value = "开始时间")
    @NotNull(message = "时间不能为空")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Valid
    private LocalDateTime beginTime;
}
