package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.entities.Departments;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
