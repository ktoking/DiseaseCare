package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.resp.DepartmentSelector;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DepartmentTypeWithNameResp;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DepartmentWithDoctorResp;
import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IDepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public CommonReturnType getAllDepartment(@RequestParam(required = false,defaultValue = "1") Integer page) {
        List<Departments> list = departmentsService.getAllDepartmentByPage(page);
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

    @ApiOperation(value = "模糊查询科室信息")
    @GetMapping("/getDepartmentByNameFuzzy")
    @UserLoginToken
    public CommonReturnType getDepartmentByNameFuzzy(@RequestParam("type") String type) {
        List<Departments> departmentByNameFuzzy = departmentsService.getDepartmentByNameFuzzy(type);
        return CommonReturnType.creat(departmentByNameFuzzy);
    }

    @ApiOperation(value = "查询科室总页数")
    @GetMapping("/getDepartmentPage")
    @UserLoginToken
    public CommonReturnType getDepartmentPage() {
        Long page = departmentsService.getDepartmentPage();
        return CommonReturnType.creat(page);
    }

    @ApiOperation(value = "所有课室信息选择器")
    @GetMapping("/getAllDepartmentSelector")
    @UserLoginToken
    public CommonReturnType getAllDepartmentSelector() {
        List<Departments> list = departmentsService.getAllDepartment();
        List<DepartmentSelector> collect = list.stream().map(e -> {
            DepartmentSelector departmentSelector = new DepartmentSelector();
            departmentSelector.setValue(e.getId());
            departmentSelector.setText(e.getName());
            return departmentSelector;
        }).collect(Collectors.toList());
        return CommonReturnType.creat(collect);
    }

    @ApiOperation(value = "获取所有科室下相关医生")
    @GetMapping("/getDoctorWithDepartment")
    @UserLoginToken
    public CommonReturnType getDoctorWithDepartment() {
        List<DepartmentWithDoctorResp> list=departmentsService.getDoctorWithDepartment();
        return CommonReturnType.creat(list);
    }

    @ApiOperation(value = "获取不同type下的科室信息")
    @GetMapping("/getDepartmentWithType")
    @UserLoginToken
    public CommonReturnType getDepartmentWithType() {
        List<DepartmentTypeWithNameResp> list=departmentsService.getDepartmentWithType();
        return CommonReturnType.creat(list);
    }

}

