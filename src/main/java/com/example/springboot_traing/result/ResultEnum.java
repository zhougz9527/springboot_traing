package com.example.springboot_traing.result;

/**
 *
 * 响应码枚举
 *
 * @Author: Think
 * @Date: 2018/5/15
 * @Time: 22:01
 */
public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    THE_USER_NAME_ALREADY_EXISTS(201, "用户名已经存在"),
    PASSWORD_STRUCTURE(202, "密码必须为6到12位的大小写字母或者数字构成"),
    PARAMS_INVALID(203, "参数无效"),
    SEND_MAIL_FAIL(204, "发送邮件过于频繁, 请稍后再试"),
    INVALID_MAIL(205, "无效邮箱"),
    CODE_INVALID(206, "验证码无效"),
    PASSWORD_INVALID(207, "密码无效"),
    ROUTE_ERROR(404, "url不存在");

    public int code;
    public String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取message的值
     * @param code
     * @return
     */
    public static String getMessageByCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.code == code) {
                return resultEnum.msg;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
