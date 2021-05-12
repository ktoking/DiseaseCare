package com.fehead.diseaseCare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.diseaseCare.controller.vo.resp.patientHistoryResp.PatientHistoryResp;
import com.fehead.diseaseCare.entities.PatientHistory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
public interface IPatientHistoryService extends IService<PatientHistory> {

    List<PatientHistoryResp> getHistoryByPatientId(Integer patientId);

    List<PatientHistoryResp> getHistoryByDoctorId(Integer doctorId);
}
