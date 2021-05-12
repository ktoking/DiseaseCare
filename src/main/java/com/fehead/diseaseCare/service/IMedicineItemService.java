package com.fehead.diseaseCare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.diseaseCare.controller.vo.req.MedicineItemReq;
import com.fehead.diseaseCare.controller.vo.resp.medicineItemResp.MedicineItemWithInfo;
import com.fehead.diseaseCare.entities.MedicineItem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktoking
 * @since 2021-04-04
 */
public interface IMedicineItemService extends IService<MedicineItem> {

    List<MedicineItemWithInfo> getMedicineItemByItemId(Integer historyId);

    int makeMedicineItemForPatient(List<MedicineItemReq> medicineItemReq, Integer historyId);
}
