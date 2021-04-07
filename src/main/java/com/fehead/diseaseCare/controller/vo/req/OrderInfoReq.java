package com.fehead.diseaseCare.controller.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrderInfoReq implements Serializable {

    @ApiModelProperty(value = "医生ID")
    @NotNull(message = "医生不能为空")
    private Integer doctorId;

    @ApiModelProperty(value = "科室ID")
    @NotNull(message = "科室不能为空")
    private Integer deptId;

    @ApiModelProperty(value = "开始时间")
    @NotNull(message = "时间不能为空")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Valid
    private LocalDateTime beginTime;

}
