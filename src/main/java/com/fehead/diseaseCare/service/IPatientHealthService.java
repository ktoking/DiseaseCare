package com.fehead.diseaseCare.service;

import com.fehead.diseaseCare.entities.PatientHealth;
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
public interface IPatientHealthService extends IService<PatientHealth> {

    List<PatientHealth> getPatientCollectData(Integer patientId);

    Integer insertPatientHealth(String str);
}
