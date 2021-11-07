package com.example.zdnl7.utils.constants;

import org.springframework.stereotype.Component;

//存储魔法值，项目这边的习惯
@Component
public class ConstUtil {
    final public String REQUEST_PATH_APPLY_CODE = "apply_code";
    final public String REQUEST_PATH_REGISTER = "register";
    final public String REQUEST_PATH_LOGIN_USER_NAME = "login_username";
    final public String REQUEST_PATH_LOGIN_PHONE = "login_phone";
    final public String REQUEST_PATH_LOGOUT = "logout";

    final public String PARAM_KEY_PHONE_NUMBER = "phone_number";
    final public String PARAM_KEY_ENVIRONMENT = "environment";
    final public String PARAM_KEY_IP = "ip";
    final public String PARAM_KEY_DEVICE_ID = "device_id";
    final public String PARAM_KEY_USER_NAME = "username";
    final public String PARAM_KEY_PASSWORD = "password";
    final public String PARAM_KEY_VERIFY_CODE = "verify_code";
    final public String PARAM_KEY_SESSION_ID = "session_id";
    final public String PARAM_KEY_ACTION_TYPE = "action_type";

    //TODO:service层魔法值
}
