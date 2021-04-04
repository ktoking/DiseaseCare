package com.fehead.diseaseCare.service.impl;

import com.fehead.diseaseCare.entities.PatientHistory;
import com.fehead.diseaseCare.mapper.PatientHistoryMapper;
import com.fehead.diseaseCare.service.IPatientHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
@Service
public class PatientHistoryServiceImpl extends ServiceImpl<PatientHistoryMapper, PatientHistory> implements IPatientHistoryService {

}
