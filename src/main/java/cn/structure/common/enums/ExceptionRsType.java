package cn.structure.common.enums;

/**
 * <p>
 *     鉴权异常返回枚举
 * </p>
 */
public enum ExceptionRsType {
    /**
     * 用户未登录
     */
    NOT_LOGGED_IN("用户未登录", "NOT_LOGGED_IN"),
    /**
     * 无效TOKEN
     */
    INVALID_AUTHENTICATION("无效的token", "INVALID_AUTHENTICATION"),
    /**
     * 权限被拒绝
     */
    PERMISSION_DENIED("无权限", "PERMISSION_DENIED"),
    ;

    private String msg;
    private String code;

    ExceptionRsType(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
