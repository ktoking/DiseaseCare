package com.fehead.diseaseCare.controller.vo.resp.departmentResp;

import com.fehead.diseaseCare.entities.Departments;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentTypeWithNameResp {

    private String type;
    private List<Departments> departmentList;
}
