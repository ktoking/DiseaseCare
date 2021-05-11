package com.fehead.diseaseCare.controller.vo.resp.departmentResp;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentWithDoctorResp {

    private Integer departmentId;
    private String type;
    private List<DoctorResp> doctorList;
}
