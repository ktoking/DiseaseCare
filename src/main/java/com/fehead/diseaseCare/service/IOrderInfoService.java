package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.controller.vo.resp.orderInfoResp.OrderInfoDetail;
import com.fehead.diseaseCare.entities.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    OrderInfo makeAppoinment(OrderInfo orderInfo);

    List<OrderInfo> getOrderPatient(Integer userId, LocalDateTime begainTime);

    OrderInfo updateOrderStatus(OrderInfo orderInfo);

    List<OrderInfoDetail> getMyOrderInfo(Integer userId);
}
