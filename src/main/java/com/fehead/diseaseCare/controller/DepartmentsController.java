package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IDepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
}

