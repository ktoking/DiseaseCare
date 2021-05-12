package com.fehead.diseaseCare.controller.vo.resp.medicineItemResp;

import com.fehead.diseaseCare.entities.MedicineInfo;
import com.fehead.diseaseCare.entities.MedicineItem;
import lombok.Data;

@Data
public class MedicineItemWithInfo extends MedicineItem {

    private String createTimeFormat;

   private MedicineInfo medicineInfo;
}
