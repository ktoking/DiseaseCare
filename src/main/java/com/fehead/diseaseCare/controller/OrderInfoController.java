package com.fehead.diseaseCare.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.req.OrderInfoReq;
import com.fehead.diseaseCare.controller.vo.req.OrderUpdateReq;
import com.fehead.diseaseCare.controller.vo.req.UserAuthReq;
import com.fehead.diseaseCare.controller.vo.resp.orderInfoResp.OrderInfoDetail;
import com.fehead.diseaseCare.controller.vo.resp.orderInfoResp.OrderInfoPatient;
import com.fehead.diseaseCare.entities.OrderInfo;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.entities.model.UserIdRoleInfo;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IOrderInfoService;
import com.fehead.diseaseCare.service.IUserService;
import com.fehead.diseaseCare.utility.DateUtil;
import com.fehead.diseaseCare.utility.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private IUserService userService;

    @ApiOperation(value = "预约挂号")
    @PostMapping("/makeAppointment")
    @UserLoginToken
    public CommonReturnType makeAppointment(@RequestBody @Valid OrderInfoReq orderInfoReq) {
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderInfoReq,orderInfo);

        LocalDateTime today_start = DateUtil.getTodayStart(orderInfoReq.getBeginTime());
        LocalDateTime today_end = DateUtil.getTodayEnd(orderInfoReq.getBeginTime());


        orderInfo.setPatientId(userIdByToken.getUserId());
        orderInfo.setBeginTime(today_start);
        orderInfo.setEndTime(today_end);
        orderInfo.setStatus(2);
        orderInfo.setCreateTime(LocalDateTime.now());
        OrderInfo insertData = orderInfoService.makeAppoinment(orderInfo);
        return CommonReturnType.creat(insertData);
    }

    @ApiOperation(value = "获取当天所有挂过当前医生号的病人")
    @GetMapping("/getOrderPatient")
    @UserLoginToken
    public CommonReturnType getOrderPatient(@RequestParam("beginTime") @NotNull(message = "时间不能为空") String beginTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(beginTime, df);
        LocalDateTime today_start = DateUtil.getTodayStart(parse);
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        List<OrderInfo> orderInfoList = orderInfoService.getOrderPatient(userIdByToken.getUserId(),today_start);
        List<OrderInfoPatient> rtVal=new ArrayList<>();
        for (OrderInfo orderInfo : orderInfoList) {
            OrderInfoPatient orderInfoPatient=new OrderInfoPatient();
            BeanUtils.copyProperties(orderInfo,orderInfoPatient);
            User patientUser = userService.queryUserByUserInfo(new User().setId(orderInfo.getPatientId()));
            orderInfoPatient.setPatientAge(patientUser.getAge());
            orderInfoPatient.setPatientAvatar(patientUser.getAvatar());
            orderInfoPatient.setPatientName(patientUser.getName());
            orderInfoPatient.setPatientSex(patientUser.getSex());
            rtVal.add(orderInfoPatient);
        }
        return CommonReturnType.creat(rtVal);
    }

    @ApiOperation(value = "修改挂号状态")
    @PutMapping("/updateOrderStatus")
    public CommonReturnType updateOrderStatus(@RequestBody @Valid OrderUpdateReq orderUpdateReq) {
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setPatientId(orderUpdateReq.getPatientId());
        orderInfo.setStatus(1);
        orderInfo.setNumber(orderUpdateReq.getNumber());
        orderInfo.setBeginTime(DateUtil.getTodayStart(orderUpdateReq.getBeginTime()));
        orderInfo.setDoctorId(userIdByToken.getUserId());
        OrderInfo updateOrder = orderInfoService.updateOrderStatus(orderInfo);
        return CommonReturnType.creat(updateOrder);
    }

    @ApiOperation(value = "查看我的挂号记录")
    @GetMapping("/getMyOrderInfo")
    public CommonReturnType getMyOrderInfo(){
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        List<OrderInfoDetail> orderInfo=orderInfoService.getMyOrderInfo(userIdByToken.getUserId());
        return CommonReturnType.creat(orderInfo);
    }



}

