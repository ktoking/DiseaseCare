package com.fehead.diseaseCare.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.mapper.UserMapper;
import com.fehead.diseaseCare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public String makeToken(User user) {
        //withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
        String token="";
        Date date = new Date(System.currentTimeMillis()+1000*60*2*24);
        token= JWT.create()
                .withAudience(user.getId()+"")
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256("xxxxx4234{}d]]fxxxxdsadasdasdsad"));
        return token;
    }

    @Override
    public User queryUserByUserInfo(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>(user);
        User findUser = userMapper.selectOne(queryWrapper);
        return findUser;
    }
}
