package com.example.springboot_traing.result;

import java.io.Serializable;

/**
 *
 * 统一api相应结果封装
 *
 * @Author: Think
 * @Date: 2018/5/15
 * @Time: 21:59
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
    private String msg;
    private Object result;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
