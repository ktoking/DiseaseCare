package com.fehead.diseaseCare.service.impl;

import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.MedicineInfoMapper;
import com.fehead.diseaseCare.service.IMedicineInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
@Service
public class MedicineInfoServiceImpl extends ServiceImpl<MedicineInfoMapper, MedicineInfo> implements IMedicineInfoService {

    @Autowired
    private MedicineInfoMapper medicineInfoMapper;

    @Override
    public MedicineInfo insertDepartment(MedicineInfo medicineInfo) {
        try {
            int insert = medicineInfoMapper.insert(medicineInfo);
        }catch (Exception e){
            new BusinessException(EmBusinessError.DATA_INSERT_ERROR);
        }
        return medicineInfo;
    }
}
