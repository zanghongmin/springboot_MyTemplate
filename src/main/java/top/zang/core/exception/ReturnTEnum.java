package top.zang.core.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 类描述: 主要时用于返回错误码和错误信息
 */
public enum ReturnTEnum implements IAssert<MyException> {
    OK(200, "success"),
    ERROR(500, "服务器开小差"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
    UNAUTHO_ERROR(403, "没有该权限"),
    BAD_REQUEST(407, "参数解析失败"),
    INVALID_TOKEN(401, "无效的token或token过期");

    private int code;
    private String msg;

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

    ReturnTEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public MyException ofException(Object... args) {
        Object msg = args[0];
        if(msg==null || StrUtil.isEmpty(msg.toString())){
            return new MyException(this.msg,this);
        }
        return new MyException(String.valueOf(msg),this);
    }
}

