package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.entities.MedicineInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<MedicineInfo> getMedicineByName(String medicineName);

    List<MedicineInfo> getAllMedicine();

    int deleteMedicineById(Integer medicineId);
}
