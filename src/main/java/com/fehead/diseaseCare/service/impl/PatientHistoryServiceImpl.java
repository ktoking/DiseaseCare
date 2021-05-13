package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.controller.vo.resp.patientHistoryResp.PatientHistoryResp;
import com.fehead.diseaseCare.entities.PatientHistory;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.mapper.PatientHistoryMapper;
import com.fehead.diseaseCare.service.IPatientHistoryService;
import com.fehead.diseaseCare.service.IUserService;
import com.fehead.diseaseCare.utility.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class PatientHistoryServiceImpl extends ServiceImpl<PatientHistoryMapper, PatientHistory> implements IPatientHistoryService {

    @Resource
    private PatientHistoryMapper patientHistoryMapper;

    @Resource
    private IUserService userService;

    @Override
    public List<PatientHistoryResp> getHistoryByPatientId(Integer patientId) {
        return getHistoryByUserId(patientId,0);
    }

    @Override
    public List<PatientHistoryResp> getHistoryByDoctorId(Integer doctorId) {
        return getHistoryByUserId(doctorId,1);
    }

    @Override
    public int insertHitoryByDoctor(Integer patientId, String patientSymptoms,Integer doctorId) {
        PatientHistory patientHistory=new PatientHistory();
        patientHistory.setCreateTime(LocalDateTime.now());
        patientHistory.setPatientId(patientId);
        patientHistory.setPatientSymptoms(patientSymptoms);
        patientHistory.setDoctorId(doctorId);
        int insert = patientHistoryMapper.insert(patientHistory);
        return patientHistory.getId();
    }

    public List<PatientHistoryResp> getHistoryByUserId(Integer userId,int status){
        List<PatientHistoryResp> list=new ArrayList<>();

        List<PatientHistory> patientHistories = patientHistoryMapper.
                selectList(new QueryWrapper<PatientHistory>().lambda().
                        eq(status==0?PatientHistory::getPatientId:PatientHistory::getDoctorId, userId).
                        orderByDesc(PatientHistory::getCreateTime));

        for (PatientHistory patientHistory : patientHistories) {
            PatientHistoryResp rt=new PatientHistoryResp();
            BeanUtils.copyProperties(patientHistory,rt);
            rt.setCreateTimeFormat(DateUtil.localdateTimeToFormatString(patientHistory.getCreateTime()));
            User doctor = userService.queryUserByUserInfo(new User().setId(patientHistory.getDoctorId()));
            rt.setDoctorName(doctor.getName());
            list.add(rt);
        }
        return list;
    }
}
