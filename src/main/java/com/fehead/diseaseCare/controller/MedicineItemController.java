package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.req.MedicineItemReq;
import com.fehead.diseaseCare.controller.vo.resp.medicineItemResp.MedicineItemWithInfo;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IMedicineItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Api(tags = "药单相关")
@RestController
@RequestMapping("/medicineItem")
public class MedicineItemController {

    @Resource
    private IMedicineItemService medicineItemService;

    @ApiOperation(value = "获取指定历史病单(historyId)的开具药品具体信息")
    @GetMapping("/getMedicineItemByItemId")
    @UserLoginToken
    public CommonReturnType getMedicineItemByItemId(@RequestParam Integer historyId) {
        List<MedicineItemWithInfo> historyByUserId = medicineItemService.getMedicineItemByItemId(historyId);
        return CommonReturnType.creat(historyByUserId);
    }

    @ApiOperation(value = "医生开药单给病人")
    @PostMapping("/makeMedicineItemForPatient")
    @UserLoginToken
    public CommonReturnType makeMedicineItemForPatient(@RequestBody @Validated List<MedicineItemReq> medicineItemReq,@RequestParam Integer historyId) {
        int status=medicineItemService.makeMedicineItemForPatient(medicineItemReq,historyId);
        return CommonReturnType.creat(status);
    }

}

