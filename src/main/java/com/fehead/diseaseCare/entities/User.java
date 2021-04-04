package com.fehead.diseaseCare.entities;

import java.math.BigDecimal;
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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别：1男0女")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "账号密码")
    private String password;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态 0正常(默认)  1注销 2住院 ")
    private Integer status;

    @ApiModelProperty(value = "账号创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "所属身份id")
    private Integer belongId;

    @ApiModelProperty(value = "0:代表病人 1:代表医生")
    private Integer role;

    @ApiModelProperty(value = "余额")
    private BigDecimal price;

    @ApiModelProperty(value = "所在楼层")
    private Integer floor;

    @ApiModelProperty(value = "东南西北")
    private Integer area;

    @ApiModelProperty(value = "病人代表病房号，医生代表办公室号")
    private String roomName;

    @ApiModelProperty(value = "病人或医生的位置")
    private String seat;

    @ApiModelProperty(value = "所属科室id")
    private Integer departmentId;

    @ApiModelProperty(value = "病人:所属于哪个医生，医生:所属于哪个boss")
    private Integer managerId;


}
