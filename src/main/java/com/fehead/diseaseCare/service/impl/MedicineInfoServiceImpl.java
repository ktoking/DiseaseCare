package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.MedicineInfoMapper;
import com.fehead.diseaseCare.service.IMedicineInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public static final int PAGE_SIZE=10;

    @Resource
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
    public List<MedicineInfo> getAllMedicine(Integer page) {
        Page<MedicineInfo> medicineInfoPage = new Page<>(page,PAGE_SIZE);  // 查询第page页，每页返回PAGE_SIZE条
        IPage<MedicineInfo> listPage = medicineInfoMapper.selectPage(medicineInfoPage, new QueryWrapper<MedicineInfo>());
        return listPage.getRecords();
    }

    @Override
    public int deleteMedicineById(Integer medicineId) {
        int delete=-1;
        try {
            delete=medicineInfoMapper.deleteById(medicineId);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_DELETE_ERROR,e.getMessage());
        }
        return delete;
    }
}
