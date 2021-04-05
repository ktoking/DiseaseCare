package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.entities.MedicineInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
public interface IMedicineInfoService extends IService<MedicineInfo> {

    MedicineInfo insertDepartment(MedicineInfo medicineInfo);
}
