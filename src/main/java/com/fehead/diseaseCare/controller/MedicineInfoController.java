package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IMedicineInfoService;
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
@RequestMapping("/medicineInfo")
@Api(tags = "内部药品相关")
public class MedicineInfoController extends BaseController{

    @Autowired
    private IMedicineInfoService medicineInfoService;

    @ApiOperation(value = "新增药品")
    @PostMapping("/insertMedicine")
    @UserLoginToken
    public CommonReturnType insertDepartment(@Valid @RequestBody MedicineInfo medicineInfo) {
        MedicineInfo  medicineInfoVO= medicineInfoService.insertDepartment(medicineInfo);
        return CommonReturnType.creat(medicineInfoVO);
    }

}

