package entity;

import java.io.Serializable;

public class Result implements Serializable {

    private boolean success;    //前端判断用
    private String message;     //前端返回信息

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //构造函数
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
