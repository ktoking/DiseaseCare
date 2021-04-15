package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.DepartmentsMapper;
import com.fehead.diseaseCare.service.IDepartmentsService;
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

    @Override
    public List<Departments> getAllDepartment() {
        List<Departments> list = departmentsMapper.selectList(new QueryWrapper<>());
        return list;
    }

    @Override
    public int deleteDepartmentById(Integer departmentId) {
        int delete=-1;
        try {
            delete = departmentsMapper.deleteById(departmentId);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_DELETE_ERROR);
        }
        return delete;
    }

    @Override
    public int updateDepartment(Departments departments) {
        int update=-1;
        try {
            update = departmentsMapper.updateById(departments);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_UPDATE_ERROR,e.getMessage());
        }
        return update;
    }
}
