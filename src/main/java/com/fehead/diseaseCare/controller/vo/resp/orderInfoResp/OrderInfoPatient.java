package com.fehead.diseaseCare.controller.vo.resp.orderInfoResp;

import com.fehead.diseaseCare.entities.OrderInfo;
import lombok.Data;

/**
 * @ClassName: OrderInfoPatient
 * @Description:
 * @Author: 西瓜
 * @Date: 2021/5/14 17:58
 */
@Data
public class OrderInfoPatient extends OrderInfo {

    private String patientName;

    private String patientAvatar;

    private Integer patientSex;

    private Integer patientAge;
}
