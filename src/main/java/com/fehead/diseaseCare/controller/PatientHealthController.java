package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.resp.PatientCollectDataResp;
import com.fehead.diseaseCare.entities.PatientHealth;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IPatientHealthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Api(tags = "病人硬件采集数据相关")
@RestController
@RequestMapping("/patientHealth")
public class PatientHealthController extends BaseController{

    @Autowired
    private IPatientHealthService patientHealthService;

    @ApiOperation(value = "获取病人硬件采集数据")
    @GetMapping("/getPatientCollectData/{patientId}")
    @UserLoginToken
    public CommonReturnType getPatientCollectData(@PathVariable("patientId") @NotNull Integer patientId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<PatientHealth> patientData = patientHealthService.getPatientCollectData(patientId);
        List<PatientCollectDataResp> resuleList=new ArrayList<>();
        for (PatientHealth patientDatum : patientData) {
            PatientCollectDataResp patientCollectDataResp=new PatientCollectDataResp();
            BeanUtils.copyProperties(patientDatum,patientCollectDataResp);
            patientCollectDataResp.setCreateDateFormat(df.format(patientDatum.getCreateDate()));
            resuleList.add(patientCollectDataResp);
        }
        return CommonReturnType.creat(resuleList);
    }


}

