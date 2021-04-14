package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.MedicineInfoMapper;
import com.fehead.diseaseCare.service.IMedicineInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<MedicineInfo> getMedicineByName(String medicineName) {
        QueryWrapper<MedicineInfo>  queryWrapper=new QueryWrapper<>();
        queryWrapper.likeRight("medicine_name",medicineName);
        List<MedicineInfo> medicineInfoList =null;
        try {
            medicineInfoList= medicineInfoMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR);
        }
        return medicineInfoList;
    }

    @Override
    public List<MedicineInfo> getAllMedicine() {
        List<MedicineInfo> medicineInfoList = medicineInfoMapper.selectList(new QueryWrapper<>());
        for (MedicineInfo medicineInfo : medicineInfoList) {
            medicineInfo.setMedicineInfo(medicineInfo.getMedicineInfo().substring(0,10)+"...");
        }
        return medicineInfoList;
    }
}
