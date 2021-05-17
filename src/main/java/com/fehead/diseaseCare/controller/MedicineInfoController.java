package com.fehead.diseaseCare.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IMedicineInfoService;
import com.fehead.diseaseCare.utility.PictureUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
@RequestMapping("/medicineInfo")
@Api(tags = "内部药品相关")
public class MedicineInfoController extends BaseController{

    @Resource
    private IMedicineInfoService medicineInfoService;

    @Resource
    private PictureUtil pictureUtil;

    @ApiOperation(value = "新增药品")
    @PostMapping("/insertMedicine")
    @UserLoginToken
    public CommonReturnType insertDepartment(@Valid MedicineInfo medicineInfo,@RequestParam("medicinePicture") MultipartFile medicinePic) throws SftpException, JSchException, JsonProcessingException {

        String pic = pictureUtil.getPicUrl(medicinePic);
        medicineInfo.setMedicinePic(pic);
        MedicineInfo  medicineInfoVO= medicineInfoService.insertDepartment(medicineInfo);
        return CommonReturnType.creat(medicineInfoVO);
    }

    @ApiOperation(value = "根据药名模糊查找药品")
    @GetMapping("/getMedicineByNameFuzzy")
    @UserLoginToken
    public CommonReturnType getMedicineByName(@RequestParam("medicineName") @NotNull(message = "药品名不能为空") String medicineName) {
        List<MedicineInfo> medicineInfoList=medicineInfoService.getMedicineByName(medicineName);
        return CommonReturnType.creat(medicineInfoList);
    }

    @ApiOperation(value = "所有药品信息")
    @GetMapping("/getAllMedicine")
    @UserLoginToken
    public CommonReturnType getAllMedicine(@RequestParam(required = false,defaultValue = "1") Integer page) {
        List<MedicineInfo> medicineInfoList=medicineInfoService.getAllMedicine(page);
        return CommonReturnType.creat(medicineInfoList);
    }

    @ApiOperation(value = "删除药品信息")
    @PostMapping("/deleteMedicineById")
    @UserLoginToken
    public CommonReturnType deleteMedicineById(@RequestBody Map<String, Integer> requestMap) {
        int delete = medicineInfoService.deleteMedicineById(requestMap.get("medicineId"));
        return CommonReturnType.creat(delete);
    }

}

