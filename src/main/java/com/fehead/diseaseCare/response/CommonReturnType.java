package com.fehead.diseaseCare.response;


public class CommonReturnType {

    // 返回请求处理结果
    private String status;
    // 返回数据
    private Object data;

    public static CommonReturnType creat(Object result) {
        return CommonReturnType.creat(result, "success");
    }

    public static CommonReturnType creat(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setStatus(status);

        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
