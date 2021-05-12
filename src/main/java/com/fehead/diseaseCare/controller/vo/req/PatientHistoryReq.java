package com.fehead.diseaseCare.controller.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PatientHistoryReq {

    @NotNull(message = "patientId can' be empty")
    private Integer patientId;

    @NotBlank(message = "patientSymptoms can't be empty")
    private String patientSymptoms;

}
