package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.diseaseCare.entities.PatientHealth;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.PatientHealthMapper;
import com.fehead.diseaseCare.service.IPatientHealthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PatientHealthServiceImpl extends ServiceImpl<PatientHealthMapper, PatientHealth> implements IPatientHealthService {

    @Autowired
    private PatientHealthMapper patientHealthMapper;

    @Override
    public List<PatientHealth> getPatientCollectData(Integer patientId) {
        QueryWrapper<PatientHealth> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("patient_id",patientId).orderByAsc("create_date");
        List<PatientHealth> patientHealths=null;
        try {
            patientHealths = patientHealthMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,e.getMessage());
        }
        return patientHealths;
    }
}
