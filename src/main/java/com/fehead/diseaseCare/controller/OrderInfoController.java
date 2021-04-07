package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.req.OrderInfoReq;
import com.fehead.diseaseCare.controller.vo.req.UserAuthReq;
import com.fehead.diseaseCare.entities.OrderInfo;
import com.fehead.diseaseCare.entities.model.UserIdRoleInfo;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IOrderInfoService;
import com.fehead.diseaseCare.utility.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/orderInfo")
@Api(tags = "挂号相关")
public class OrderInfoController extends BaseController{

    @Autowired
    private IOrderInfoService orderInfoService;

    @ApiOperation(value = "预约挂号")
    @PostMapping("/makeAppointment")
    @UserLoginToken
    public CommonReturnType makeAppointment(@RequestBody @Valid OrderInfoReq orderInfoReq) {
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderInfoReq,orderInfo);

        LocalDateTime today_start = LocalDateTime.of(orderInfoReq.getBeginTime().toLocalDate(), LocalTime.MIN);// 当天开始
        LocalDateTime today_end = LocalDateTime.of(orderInfoReq.getBeginTime().toLocalDate(), LocalTime.MAX);//当天结束


        orderInfo.setPatientId(userIdByToken.getUserId());
        orderInfo.setBeginTime(today_start);
        orderInfo.setEndTime(today_end);
        orderInfo.setStatus(0);
        orderInfo.setCreateTime(LocalDateTime.now());
        OrderInfo insertData = orderInfoService.makeAppoinment(orderInfo);
        return CommonReturnType.creat(insertData);
    }
}

