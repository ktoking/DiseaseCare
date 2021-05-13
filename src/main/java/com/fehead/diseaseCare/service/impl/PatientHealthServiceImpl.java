package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.entities.PatientHealth;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.PatientHealthMapper;
import com.fehead.diseaseCare.service.IPatientHealthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private PatientHealthMapper patientHealthMapper;

    @Override
    public List<PatientHealth> getPatientCollectData(Integer patientId) {
        QueryWrapper<PatientHealth> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("patient_id",patientId).orderByDesc("create_date");
        List<PatientHealth> patientHealths=null;
        try {
            patientHealths = patientHealthMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,e.getMessage());
        }
        return patientHealths;
    }

    @Override
    public Integer insertPatientHealth(String str) {
        str=str.trim();
        if(str.startsWith("AA")){
            str=str.substring(3,str.length()-2);
            String[] s = str.split("_");
            if(s.length!=6){ // 当没拿到所有数据后返回-1
                return -1;
            }else {
                PatientHealth patientHealth=new PatientHealth();
                patientHealth.setLongitude(s[0]);
                patientHealth.setLatitude(s[1]);
                patientHealth.setTemperature(s[2]);
                patientHealth.setBloodOxygen(s[3]);
                patientHealth.setHeartRate(s[4]);
                patientHealth.setPatientId(Integer.parseInt(s[5]));
                patientHealth.setCreateDate(LocalDateTime.now());
                return patientHealthMapper.insert(patientHealth);
            }
        }else {
            return -1;
        }
    }
}
