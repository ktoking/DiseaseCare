package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.mapper.UserMapper;
import com.fehead.diseaseCare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User createUser(User newUser) {
        int insert = userMapper.insert(newUser);
        return newUser;
    }

    @Override
    public User queryUserByUserInfo(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>(user);
        User findUser = userMapper.selectOne(queryWrapper);
        return findUser;
    }
}
