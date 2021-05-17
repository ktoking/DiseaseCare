package com.fehead.diseaseCare.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fehead.diseaseCare.aop.PassToken;
import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.req.UserAuthReq;
import com.fehead.diseaseCare.controller.vo.req.UserInsertReq;
import com.fehead.diseaseCare.controller.vo.req.UserRoleInfoReq;
import com.fehead.diseaseCare.controller.vo.resp.UserStorageDataResp;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.entities.model.UserBaseInfo;
import com.fehead.diseaseCare.entities.model.UserIdRoleInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IUserService;
import com.fehead.diseaseCare.utility.DateUtil;
import com.fehead.diseaseCare.utility.JwtUtil;
import com.fehead.diseaseCare.utility.PictureUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Resource
    private IUserService userService;

    @Resource
    private PictureUtil pictureUtil;


    @ApiOperation(value = "测试方法")
    @GetMapping("/test")
    @UserLoginToken
    public CommonReturnType test() {
        int userIdByToken = JwtUtil.getUserIdByToken().getUserId();
        UserAuthReq userAuth=new UserAuthReq();
        return CommonReturnType.creat(userAuth);
    }

    @ApiOperation(value = "获取所有医生信息")
    @GetMapping("/getAllDoctors")
    @UserLoginToken
    public CommonReturnType getAllDoctors() {
        List<UserBaseInfo> userDoctors = userService.queryAllDoctor();
        return CommonReturnType.creat(userDoctors);
    }

    @ApiOperation(value = "用户充值")
    @PutMapping("/chargeMoney")
    @UserLoginToken
    @Transactional
    public CommonReturnType chargeMoney(
            @RequestParam("money")
                    Double money) {
        if(money==null||money<=0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"金额不能少于0或空");
        }
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        BigDecimal moneyD=new BigDecimal(money);
        boolean chargeSuccess= userService.chargeMoney(moneyD, userIdByToken.getUserId());
        return CommonReturnType.creat(chargeSuccess);
    }


    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @PassToken
    public CommonReturnType login(@RequestBody @Valid UserAuthReq userAuth) throws BusinessException {
        if(StringUtils.isEmpty(userAuth.getPhone())||StringUtils.isEmpty(userAuth.getPassword())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        User user=new User();
        BeanUtils.copyProperties(userAuth,user);
        User findUser = userService.queryUserByUserInfo(user);
        //调刚刚上面定义的userService中的制造token的方法得到token
        if(findUser==null){
            throw new BusinessException(EmBusinessError.USER_AUTH_ERROR);
        }

        String token = JwtUtil.makeToken(findUser);
        UserStorageDataResp userStorageDataResp=new UserStorageDataResp();
        BeanUtils.copyProperties(findUser,userStorageDataResp);
        userStorageDataResp.setToken(token);
        return CommonReturnType.creat(userStorageDataResp);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/create")
    @PassToken
    public CommonReturnType create(@RequestParam("avatar") MultipartFile avatar,@Valid UserInsertReq newUser) throws BusinessException, SftpException, JSchException, JsonProcessingException {
        User user=new User();
        BeanUtils.copyProperties(newUser,user);

        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = sft.parse(newUser.getBirthday().toLocalDate().toString());
        }catch(Exception e){
            throw  new BusinessException(EmBusinessError.COMMON_ERROR,"时间转化异常");
        }
        int age = DateUtil.getAgeByBirth(date);

        String avatarUrl = pictureUtil.getPicUrl(avatar);
        user.setAvatar(avatarUrl);
        user.setAge(age);
        User insertUser = userService.createUser(user);
        return CommonReturnType.creat(insertUser);
    }

    @ApiOperation(value = "用户更新具体所属信息")
    @PostMapping("/update/belongInfo")
    @UserLoginToken
    public CommonReturnType completeUserInfo(@Valid @RequestBody UserRoleInfoReq userRoleInfoReq){
        UserIdRoleInfo userIdByToken = JwtUtil.getUserIdByToken();
        User user=new User();
        BeanUtils.copyProperties(userRoleInfoReq,user);
        user.setId(userIdByToken.getUserId());
        int update = userService.completeUserInfo(user);
        return CommonReturnType.creat(update);
    }

    @ApiOperation(value = "获取当前医生下的所有病人信息(非出院)")
    @GetMapping("/getAllPatientByDoctorId")
    @UserLoginToken
    public CommonReturnType getAllPatientByDoctorId() {
        int userIdByToken = JwtUtil.getUserIdByToken().getUserId();
        List<UserBaseInfo>patientList=userService.getAllPatientByDoctorId(userIdByToken);
        return CommonReturnType.creat(patientList);
    }

}

