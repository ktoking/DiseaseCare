package com.fehead.diseaseCare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fehead.diseaseCare.controller.vo.req.MedicineItemReq;
import com.fehead.diseaseCare.controller.vo.resp.medicineItemResp.MedicineItemWithInfo;
import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.entities.MedicineItem;
import com.fehead.diseaseCare.entities.PatientHistory;
import com.fehead.diseaseCare.mapper.MedicineInfoMapper;
import com.fehead.diseaseCare.mapper.MedicineItemMapper;
import com.fehead.diseaseCare.mapper.PatientHistoryMapper;
import com.fehead.diseaseCare.service.IMedicineItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fehead.diseaseCare.utility.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class MedicineItemServiceImpl extends ServiceImpl<MedicineItemMapper, MedicineItem> implements IMedicineItemService {

    @Resource
    private MedicineItemMapper medicineItemMapper;

    @Resource
    private MedicineInfoMapper medicineInfoMapper;

    @Resource
    private PatientHistoryMapper patientHistoryMapper;

    @Override
    public  List<MedicineItemWithInfo> getMedicineItemByItemId(Integer historyId) {

        List<MedicineItemWithInfo> rtList=new ArrayList<>();
        List<MedicineItem> medicineItems = medicineItemMapper.selectList(new QueryWrapper<MedicineItem>().lambda().eq(MedicineItem::getHistoryId, historyId));

        for (MedicineItem medicineItem : medicineItems) {
            MedicineItemWithInfo rt=new MedicineItemWithInfo();
            MedicineInfo medicineInfo = medicineInfoMapper.selectOne(new QueryWrapper<MedicineInfo>().lambda().eq(MedicineInfo::getId, medicineItem.getMedicineId()));
            BeanUtils.copyProperties(medicineItem,rt);

            rt.setMedicineInfo(medicineInfo);
            rt.setCreateTimeFormat(DateUtil.localdateTimeToFormatString(medicineItem.getCreateTime()));
            rtList.add(rt);
        }
        return rtList;
    }

    @Override
    @Transactional
    public int makeMedicineItemForPatient(List<MedicineItemReq> medicineItemReq, Integer historyId) {

        BigDecimal price=new BigDecimal("0");

        for (MedicineItemReq itemReq : medicineItemReq) {
            MedicineItem medicineItem = new MedicineItem();
            medicineItem.setCount(itemReq.getCount());
            medicineItem.setMedicineId(itemReq.getMedicineId());
            medicineItem.setHistoryId(historyId);

            MedicineInfo medicineInfo = medicineInfoMapper.selectOne(new QueryWrapper<MedicineInfo>().lambda().eq(MedicineInfo::getId, itemReq.getMedicineId()));
            if(medicineInfo!=null){
                price=price.add(medicineInfo.getMedicinePrice());
                int insert = medicineItemMapper.insert(medicineItem);
            }
            medicineInfo.setMedicineRepertory(medicineInfo.getMedicineRepertory()-itemReq.getCount());
            int update = medicineInfoMapper.update(medicineInfo, new UpdateWrapper<MedicineInfo>().lambda().eq(MedicineInfo::getId, itemReq.getMedicineId()));
        }
        int update = patientHistoryMapper.update(new PatientHistory().setPrice(price), new UpdateWrapper<PatientHistory>().lambda().eq(PatientHistory::getId, historyId));

        return update;

    }

}
