package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.entities.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
public interface IUserService extends IService<User> {

    String makeToken(User user) ;
    // 通过账户密码来登录
    User queryUserByUserInfo(User user);
}
