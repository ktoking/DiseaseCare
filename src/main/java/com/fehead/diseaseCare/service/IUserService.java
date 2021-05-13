package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.entities.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.diseaseCare.entities.model.UserBaseInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
public interface IUserService extends IService<User> {
    // 注册用户
    User createUser(User newUser);
    // 通过账户密码来登录
    User queryUserByUserInfo(User user);

    // 完善用户信息
    int completeUserInfo(User user);
    // 用户充钱
    boolean chargeMoney(BigDecimal money,Integer userId);

    List<UserBaseInfo> queryAllDoctor();

    List<UserBaseInfo> getAllPatientByDoctorId(int doctorId);

    User queruUserByUserId(Integer userId);

    int updateByUserId(User user);
}
