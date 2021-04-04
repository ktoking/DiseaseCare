package com.fehead.diseaseCare.error;


public enum EmBusinessError implements CommonError {

    // 通用错误类型 1000x
    UNKNOWN_ERROR(10000, "未知错误"),
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    IMAGE_INSERT_ERROR(10002,"图片上传失败"),
    COMMON_ERROR(10003,"通用返回自定义异常信息"),

    // 类型为数据库操作异常 2000x
    DATA_ERROR(20000, "数据库操作失败"),
    DATA_INSERT_ERROR(20001, "数据库插入异常"),
    DATA_DELETE_ERROR(20002, "数据库删除异常"),
    DATA_UPDATE_ERROR(20003, "数据库更新异常"),
    DATA_SELECT_ERROR(20004, "数据库查询异常"),

    // 验权失败 4000x
    USER_AUTH_ERROR(40001,"验权失败"),

    ;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;



    @Override
    public int getErrorCode() {
        return errCode;
    }

    @Override
    public String getErrorMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
