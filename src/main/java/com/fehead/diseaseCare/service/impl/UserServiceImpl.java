package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.UserMapper;
import com.fehead.diseaseCare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Override
    public int completeUserInfo(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",user.getId());
        int update=0;
        try {
             update = userMapper.update(user, updateWrapper);
        }catch (BusinessException e){
            throw new BusinessException(EmBusinessError.DATA_UPDATE_ERROR);
        }
        return update;
    }

    @Override
    public synchronized boolean chargeMoney(BigDecimal money,Integer userId) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        User findUser = userMapper.selectOne(queryWrapper);
        if(findUser==null){
           throw new BusinessException(EmBusinessError.COMMON_ERROR,"用户不存在");
        }

        LambdaUpdateWrapper<Object> lambda = new UpdateWrapper<>().lambda();
        LambdaUpdateChainWrapper<User> lambdaUpdateChainWrapper = new LambdaUpdateChainWrapper<>(userMapper);
        BigDecimal balanceMoney=findUser.getPrice().add(money);
        boolean update=false;
        try {
             update = lambdaUpdateChainWrapper.eq(User::getId, userId).set(User::getPrice, balanceMoney).update();
        }catch (BusinessException e){
            throw new BusinessException(EmBusinessError.DATA_UPDATE_ERROR);
        }
        return update;
    }
}
