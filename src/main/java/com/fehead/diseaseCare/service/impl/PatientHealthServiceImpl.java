package com.fehead.diseaseCare.service.impl;

import com.fehead.diseaseCare.entities.PatientHealth;
import com.fehead.diseaseCare.mapper.PatientHealthMapper;
import com.fehead.diseaseCare.service.IPatientHealthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-03
 */
@Service
public class PatientHealthServiceImpl extends ServiceImpl<PatientHealthMapper, PatientHealth> implements IPatientHealthService {

}
