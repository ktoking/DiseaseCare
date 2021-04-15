package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.PassToken;
import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IDepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Controller
@RequestMapping("/departments")
@Api(tags = "内部科室相关")
public class DepartmentsController extends BaseController{

    @Autowired
    private IDepartmentsService departmentsService;

    @ApiOperation(value = "新增科室")
    @PostMapping("/insertDepartment")
    @UserLoginToken
    public CommonReturnType insertDepartment(@RequestBody @Valid Departments departments) {
        Departments insertDepartment = departmentsService.insertDepartment(departments);
        return CommonReturnType.creat(insertDepartment);
    }

    @ApiOperation(value = "所有科室信息")
    @GetMapping("/getAllDepartment")
    @UserLoginToken
    public CommonReturnType getAllDepartment() {
        List<Departments> list = departmentsService.getAllDepartment();
        return CommonReturnType.creat(list);
    }

    @ApiOperation(value = "删除科室信息")
    @PostMapping("/deleteDepartmentById")
    @UserLoginToken
    public CommonReturnType deleteDepartmentById(@RequestBody Map<String, Integer> requestMap) {
        int delete = departmentsService.deleteDepartmentById(requestMap.get("departmentId"));
        return CommonReturnType.creat(delete);
    }

    @ApiOperation(value = "修改科室信息")
    @PutMapping("/updateDepartment")
    @UserLoginToken
    public CommonReturnType updateDepartment(@RequestBody @Valid Departments departments) {
        int update = departmentsService.updateDepartment(departments);
        return CommonReturnType.creat(update);
    }

}

