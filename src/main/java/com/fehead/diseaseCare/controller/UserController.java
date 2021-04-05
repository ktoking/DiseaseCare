package com.fehead.diseaseCare.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.diseaseCare.aop.PassToken;
import com.fehead.diseaseCare.aop.UserLoginToken;
import com.fehead.diseaseCare.controller.vo.req.UserAuthReq;
import com.fehead.diseaseCare.controller.vo.req.UserInsertReq;
import com.fehead.diseaseCare.controller.vo.req.UserRoleInfoReq;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.entities.model.UserIdRoleInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.response.CommonReturnType;
import com.fehead.diseaseCare.service.IUserService;
import com.fehead.diseaseCare.utility.FtpUtil;
import com.fehead.diseaseCare.utility.IDUtils;
import com.fehead.diseaseCare.utility.JwtUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private FtpUtil ftpUtil;

    @Autowired
    HttpServletRequest req;

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "测试方法")
    @GetMapping("/test")
    @UserLoginToken
    public CommonReturnType test() {
        int userIdByToken = JwtUtil.getUserIdByToken().getUserId();
        UserAuthReq userAuth=new UserAuthReq();
        return CommonReturnType.creat(userAuth);
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
    @GetMapping("/login")
    @PassToken
    public CommonReturnType login(@RequestBody UserAuthReq userAuth) throws BusinessException {
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
        return CommonReturnType.creat(token);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/create")
    @PassToken
    public CommonReturnType create(@RequestParam("avatar") MultipartFile avatar,@Valid UserInsertReq newUser) throws BusinessException, SftpException, JSchException, JsonProcessingException {
        User user=new User();
        BeanUtils.copyProperties(newUser,user);
        Object result = uploadPicture(avatar);
        String cover = new ObjectMapper().writeValueAsString(result);
        String avatarUrl= cover.replace("\"", "");
        user.setAvatar(avatarUrl);
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

    // 上传图片拿到地址
    public Object uploadPicture(MultipartFile uploadFile) throws BusinessException, JSchException, SftpException {
        //1.给上传的图片生成新的文件名
        //1.1获取原始文件名getAcitivity
        String oldName=uploadFile.getOriginalFilename();
        //1.2使用IDUtils工具类生成新的文件名，新的文件名=newName+文件后缀
        String newName= IDUtils.getImageName();
        assert oldName!=null;
        newName=newName+oldName.substring(oldName.lastIndexOf("."));
        //1.3生成文件在服务器端存储的子目录
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime localDateTime=LocalDateTime.now();
        String filePath = "/"+localDateTime.format(formatter)+"/";

        //2.把图片上传到图片服务器
        //2.1获取上传的io流
        InputStream inputStream=null;
        try {
            inputStream=uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2调用FtpUtil工具进行上传
        return ftpUtil.putImages(inputStream,filePath,newName);
    }

}

