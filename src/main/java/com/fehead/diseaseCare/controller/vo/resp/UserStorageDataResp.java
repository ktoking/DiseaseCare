package com.fehead.diseaseCare.controller.vo.resp;

import lombok.Data;

@Data
public class UserStorageDataResp {

    private Integer id;

    private String name;

    private Integer sex;

    private String avatar;

    private String email;

    private String phone;

    private String token;

    private Integer role;
}
