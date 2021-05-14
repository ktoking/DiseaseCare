package com.fehead.diseaseCare.controller.vo.resp.patientHistoryResp;

import com.fehead.diseaseCare.entities.PatientHistory;
import lombok.Data;

@Data
public class PatientHistoryResp extends PatientHistory {

    private String doctorName;

    private String createTimeFormat;

    private String patientName;
}
