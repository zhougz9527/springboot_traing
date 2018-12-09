package com.example.springboot_traing.result;

/**
 *
 * 响应结果生成工具
 *
 * @Author: Think
 * @Date: 2018/5/15
 * @Time: 22:05
 */
public class ResultUtil {

    private static int[] data = {};

    public static Result success(Object object) {
        return new Result(200, ResultEnum.getMessageByCode(200), object);
    }

    public static Result succeedNoData() {
        return success(data);
    }

    public static Result error(int code) {
        return new Result(code, ResultEnum.getMessageByCode(code), data);
    }

    public static Result error(int code, Object object) {
        return new Result(code, ResultEnum.getMessageByCode(code), object);
    }

}
