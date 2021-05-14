package com.fehead.diseaseCare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DepartmentTypeWithNameResp;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DepartmentWithDoctorResp;
import com.fehead.diseaseCare.entities.Departments;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
public interface IDepartmentsService extends IService<Departments> {

    // 新增科室
    Departments insertDepartment(Departments department);

    List<Departments> getAllDepartment();

    int deleteDepartmentById(Integer departmentId);

    int updateDepartment(Departments departments);

    List<Departments> getDepartmentByNameFuzzy(String type);

    List<DepartmentWithDoctorResp> getDoctorWithDepartment();

    List<DepartmentTypeWithNameResp> getDepartmentWithType();

    Departments queryDepartmentById(Integer deptId);

}
