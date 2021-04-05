package com.fehead.diseaseCare.service.impl;

import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.DepartmentsMapper;
import com.fehead.diseaseCare.service.IDepartmentsService;
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
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Override
    public Departments insertDepartment(Departments department) {
        try {
            int insert = departmentsMapper.insert(department);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_INSERT_ERROR);
        }
        return department;
    }
}
