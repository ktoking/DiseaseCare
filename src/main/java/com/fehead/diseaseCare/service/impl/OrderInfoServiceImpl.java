package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.fehead.diseaseCare.entities.OrderInfo;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.OrderInfoMapper;
import com.fehead.diseaseCare.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderInfo makeAppoinment(OrderInfo orderInfo) {

        QueryWrapper<OrderInfo> infoQueryWrapper=new QueryWrapper<>();
        infoQueryWrapper.eq("patient_id",orderInfo.getPatientId()).eq("doctor_id",orderInfo.getDoctorId()).eq("begin_time",orderInfo.getBeginTime());
        Integer integer = orderInfoMapper.selectCount(infoQueryWrapper);
        if(integer>0){
            throw new BusinessException(EmBusinessError.COMMON_ERROR,"已经挂过号了");
        }

        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("begin_time", orderInfo.getBeginTime());
        Integer num = orderInfoMapper.selectCount(queryWrapper);
        orderInfo.setNumber(++num);
        try {
            int insert = orderInfoMapper.insert(orderInfo);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_INSERT_ERROR,e.getMessage());
        }

//        LocalDateTime today_start = LocalDateTime.of(orderInfo.getBeginTime().toLocalDate(), LocalTime.MIN);
//        LocalDateTime today_end = LocalDateTime.of(orderInfo.getBeginTime().toLocalDate(), LocalTime.MAX);//当天零点
        return orderInfo;
    }

    @Override
    public List<OrderInfo> getOrderPatient(Integer userId, LocalDateTime begainTime) {
        QueryWrapper<OrderInfo> infoQueryWrapper=new QueryWrapper<>();
        infoQueryWrapper.eq("doctor_id",userId).eq("begin_time",begainTime).eq("status",2).orderByAsc("number");
        List<OrderInfo> orderInfos=null;
        try {
            orderInfos = orderInfoMapper.selectList(infoQueryWrapper);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,e.getMessage());
        }
        return orderInfos;
    }

    @Override
    public OrderInfo updateOrderStatus(OrderInfo orderInfo) {
        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("patient_id",orderInfo.getPatientId()).eq("doctor_id",orderInfo.getDoctorId()).eq("begin_time",orderInfo.getBeginTime()).eq("number",orderInfo.getNumber()).eq("status",2);
        OrderInfo findOrder = orderInfoMapper.selectOne(queryWrapper);
        if(findOrder==null){
            throw new BusinessException(EmBusinessError.COMMON_ERROR,"预约单不存在");
        }
        findOrder.setStatus(1);
        try {
            int update = orderInfoMapper.updateById(findOrder);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_UPDATE_ERROR);
        }
        return findOrder;
    }
}
