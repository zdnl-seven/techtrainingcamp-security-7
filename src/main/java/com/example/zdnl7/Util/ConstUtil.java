package com.example.zdnl7.Util;

import org.springframework.stereotype.Component;

//存储魔法值，项目这边的习惯
@Component
public class ConstUtil {
    final public String REQUEST_PATH_APPLY_CODE = "applyCode";
    final public String REQUEST_PATH_REGISTER = "register";
    final public String REQUEST_PATH_LOGIN_USER_NAME = "login_username";
    final public String REQUEST_PATH_LOGIN_PHONE = "login_phone";
    final public String REQUEST_PATH_LOGOUT = "logout";

    final public String PARAM_KEY_PHONE_NUMBER = "PhoneNumber";
    final public String PARAM_KEY_ENVIRONMENT = "Environment";
    final public String PARAM_KEY_IP = "IP";
    final public String PARAM_KEY_DEVICE_ID = "DeviceID";
    final public String PARAM_KEY_USER_NAME = "UserName";
    final public String PARAM_KEY_PASSWORD = "PassWord";
    final public String PARAM_KEY_VERIFY_CODE = "VerifyCode";
    final public String PARAM_KEY_SESSION_ID = "SessionId";
    final public String PARAM_KEY_ACTION_TYPE = "ActionType";

    //TODO:service层魔法值
}
