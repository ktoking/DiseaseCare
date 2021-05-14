package com.fehead.diseaseCare.controller.vo.resp.orderInfoResp;

import com.fehead.diseaseCare.entities.OrderInfo;
import lombok.Data;

/**
 * @ClassName: OrderInfoDetail
 * @Description:
 * @Author: 西瓜
 * @Date: 2021/5/14 12:12
 */
@Data
public class OrderInfoDetail extends OrderInfo {

    private String doctorName;

    private String departmentName;
}
