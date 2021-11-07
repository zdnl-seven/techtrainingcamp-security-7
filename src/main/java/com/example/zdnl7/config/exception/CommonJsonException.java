package com.example.zdnl7.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.example.zdnl7.utils.CommonUtil;
import com.example.zdnl7.utils.constants.ErrorEnum;


/**
 * @description: 在校验参数时, 如果不符合要求, 可以抛出此错误类
 *   拦截器可以统一拦截此错误,将其中json返回给前端
 * @author yanzx
 * @date: 2021/11/7 4:45 下午
 */
public class CommonJsonException extends RuntimeException {
    private final JSONObject resultJson;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     *
     * @param errorEnum 以错误的ErrorEnum做参数
     */
    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
