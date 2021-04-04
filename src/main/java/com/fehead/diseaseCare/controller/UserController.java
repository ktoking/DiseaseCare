package com.fehead.diseaseCare.controller;


import com.fehead.diseaseCare.aop.PassToken;
import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.entities.model.UserAuthModel;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IUserService;
import com.fehead.diseaseCare.utility.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController{

    @Autowired
    HttpServletRequest req;

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "测试方法")
    @RequestMapping("/test")
    @UserLoginToken
    public CommonReturnType test() {
        int userIdByToken = JwtUtil.getUserIdByToken();
        UserAuthModel userAuth=new UserAuthModel();
        userAuth.setId(userIdByToken);
        return CommonReturnType.creat(userAuth);
    }


    @ApiOperation(value = "用户登录")
    @RequestMapping("/login")
    @PassToken
    public CommonReturnType login(@RequestBody User user) throws BusinessException {
        if(StringUtils.isEmpty(user.getPhone())||StringUtils.isEmpty(user.getPassword())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        User findUser = userService.queryUserByUserInfo(user);
        //调刚刚上面定义的userService中的制造token的方法得到token
        if(findUser==null){
            throw new BusinessException(EmBusinessError.USER_AUTH_ERROR);
        }
        String token=userService.makeToken(findUser);
        return CommonReturnType.creat(token);
    }

}

