package com.fehead.diseaseCare.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ktoking
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MedicineItem对象", description="")
public class MedicineItem implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "唯一标识清单")
    private Integer itemId;

    @ApiModelProperty(value = "药品id")
    private String medicineId;

    @ApiModelProperty(value = "药品数量")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
