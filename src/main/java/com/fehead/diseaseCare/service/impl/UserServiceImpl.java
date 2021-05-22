package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.entities.model.UserBaseInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.DepartmentsMapper;
import com.fehead.diseaseCare.mapper.UserMapper;
import com.fehead.diseaseCare.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public static final int PAGE_SIZE=5;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DepartmentsMapper departmentsMapper;


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

    @Override
    public List<UserBaseInfo> queryAllDoctor(Integer page) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role",1);
        Page<User> userPage = new Page<>(page,PAGE_SIZE);  // 查询第page页，每页返回PAGE_SIZE条
        List<User> users = userMapper.selectPage(userPage,queryWrapper).getRecords();

        List<UserBaseInfo> userDoctors=new ArrayList<>();
        for (User user : users) {
            UserBaseInfo userDoctor=new UserBaseInfo();
            QueryWrapper<Departments> departmentsQueryWrapper=new QueryWrapper<>();
            departmentsQueryWrapper.eq("id",user.getDepartmentId());
            Departments departments = departmentsMapper.selectOne(departmentsQueryWrapper);
            BeanUtils.copyProperties(user,userDoctor);
            userDoctor.setDepartments(departments);
            userDoctors.add(userDoctor);
        }
        return userDoctors;
    }

    @Override
    public List<UserBaseInfo> getAllPatientByDoctorId(int doctorId) {
        List<UserBaseInfo> resultList=new ArrayList<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role",0).eq("manager_id",doctorId).ne("status",1);
        List<User> users =null;
        try {
            users=userMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR);
        }
        for (User user : users) {
            UserBaseInfo userBaseInfo=new UserBaseInfo();
            BeanUtils.copyProperties(user,userBaseInfo);
            resultList.add(userBaseInfo);
        }
        return resultList;
    }

    @Override
    public User queruUserByUserId(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Long getDoctorPage() {
        Page<User> userPage = new Page<>(1,PAGE_SIZE);  // 查询第page页，每页返回PAGE_SIZE条
        return userMapper.selectPage(userPage,new QueryWrapper<>()).getPages();
    }

    @Override
    public int updateByUserId(User user) {
        return userMapper.updateById(user);
    }
}
