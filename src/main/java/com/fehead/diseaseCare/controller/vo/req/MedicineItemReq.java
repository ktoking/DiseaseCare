package com.fehead.diseaseCare.controller.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MedicineItemReq {

    @NotNull(message = "medicineId can't be empty")
    private Integer medicineId;

    @NotNull(message = "count can't be empty")
    private Integer count;
}
