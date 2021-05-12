package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.resp.patientHistoryResp.PatientHistoryResp;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IPatientHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Api(tags = "看病记录相关")
@RestController
@RequestMapping("/patientHistory")
public class PatientHistoryController {

    @Autowired
    private IPatientHistoryService patientHistoryService;

    @ApiOperation(value = "病人查看自己的看病历史")
    @GetMapping("/getHistoryByPatientId")
    @UserLoginToken
    public CommonReturnType getHistoryByUserId(@RequestParam Integer patientId) {
        List<PatientHistoryResp> historyByUserId = patientHistoryService.getHistoryByPatientId(patientId);
        return CommonReturnType.creat(historyByUserId);
    }

    @ApiOperation(value = "医生查看名下的病人看病历史")
    @GetMapping("/getHistoryByDoctorId")
    @UserLoginToken
    public CommonReturnType getHistoryByPatientId(@RequestParam Integer doctorId) {
        List<PatientHistoryResp> historyByUserId = patientHistoryService.getHistoryByDoctorId(doctorId);
        return CommonReturnType.creat(historyByUserId);
    }

}

