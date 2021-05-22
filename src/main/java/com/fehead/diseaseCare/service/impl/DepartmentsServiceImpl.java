package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DepartmentTypeWithNameResp;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DepartmentWithDoctorResp;
import com.fehead.diseaseCare.controller.vo.resp.departmentResp.DoctorResp;
import com.fehead.diseaseCare.entities.Departments;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.mapper.DepartmentsMapper;
import com.fehead.diseaseCare.mapper.UserMapper;
import com.fehead.diseaseCare.service.IDepartmentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static final int PAGE_SIZE=10;

    @Resource
    private DepartmentsMapper departmentsMapper;

    @Resource
    private UserMapper userMapper;

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
    public List<Departments> getAllDepartmentByPage(Integer page) {
        Page<Departments> departmentsPage = new Page<>(page,PAGE_SIZE);  // 查询第page页，每页返回PAGE_SIZE条
        List<Departments> list = departmentsMapper.selectPage(departmentsPage,new QueryWrapper<>()).getRecords();
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

    @Override
    public List<Departments> getDepartmentByNameFuzzy(String type) {
        QueryWrapper<Departments>  queryWrapper=new QueryWrapper<>();
        queryWrapper.likeRight("type",type);
        List<Departments> departmentsList =null;
        try {
            departmentsList= departmentsMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR);
        }
        return departmentsList;
    }

    @Override
    public List<DepartmentWithDoctorResp> getDoctorWithDepartment() {
        List<DepartmentWithDoctorResp> rt=new ArrayList<>();

        List<Departments> departments = departmentsMapper.selectList(new QueryWrapper<>());
        for (Departments department : departments) {
            List<DoctorResp> doctorResps=new ArrayList<>();

            QueryWrapper<User>  queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("department_id",department.getId());
            List<User> users = userMapper.selectList(queryWrapper);

            for (User user : users) {
                DoctorResp doctorResp=new DoctorResp();
                BeanUtils.copyProperties(user,doctorResp);
                doctorResps.add(doctorResp);
            }

            DepartmentWithDoctorResp departmentResp=new DepartmentWithDoctorResp();
            departmentResp.setDepartmentId(department.getId());
            departmentResp.setType(department.getType());
            departmentResp.setDoctorList(doctorResps);

            rt.add(departmentResp);
        }

        return rt;

    }

    @Override
    public List<DepartmentTypeWithNameResp> getDepartmentWithType() {
       List<DepartmentTypeWithNameResp> rtVal=new ArrayList<>();

       QueryWrapper<Departments> queryWrapper=new QueryWrapper<>();
       queryWrapper.select("distinct(type)");
       List<String> collect = departmentsMapper.selectList(queryWrapper).stream().distinct().map(e -> {
            return e.getType();
       }).collect(Collectors.toList());

        for (String type : collect) {
            DepartmentTypeWithNameResp rt=new DepartmentTypeWithNameResp();

            QueryWrapper<Departments> qw=new QueryWrapper<>();
            qw.eq("type",type);
            List<Departments> departments = departmentsMapper.selectList(qw);

            rt.setType(type);
            rt.setDepartmentList(departments);
            rtVal.add(rt);
        }

        return rtVal;

    }

    @Override
    public Departments queryDepartmentById(Integer deptId) {
        return departmentsMapper.selectById(deptId);
    }

    @Override
    public Long getDepartmentPage() {
        Page<Departments> departmentsPage = new Page<>(1,PAGE_SIZE);  // 查询第page页，每页返回PAGE_SIZE条
        IPage<Departments> listPage = departmentsMapper.selectPage(departmentsPage, new QueryWrapper<Departments>());
        return listPage.getPages();
    }

    @Override
    public List<Departments> getAllDepartment() {
        return departmentsMapper.selectList(new QueryWrapper<>());
    }
}
