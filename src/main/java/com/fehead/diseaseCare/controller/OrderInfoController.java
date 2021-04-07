package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.req.OrderInfoReq;
import com.fehead.diseaseCare.controller.vo.req.UserAuthReq;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.utility.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

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

    @ApiOperation(value = "预约挂号")
    @PostMapping("/makeAppointment")
    @UserLoginToken
    public CommonReturnType makeAppointment(@RequestBody @Valid OrderInfoReq orderInfoReq) {
        return null;
    }
}

