package cn.structure.common.enums;

import lombok.Getter;

/**
 * <p>
 *     返回结果code枚举
 * </p>
 * @author chuck
 * @version 1.0.0
 * @since  2019-10-20
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 验证成功
     */
    SUCCESS("成功！", "SUCCESS"),
    /**
     * 内部业务错误
     */
    FAIL("内部业务错误", "FAIL"),
    /**
     * 资源不存在
     */
    NOT_FOUND("资源不存在", "NOT_FOUND"),
    /**
     * 断路
     */
    FALLBACK("断路", "FALLBACK"),
    /**
     * 验证失败
     */
    UNAUTHORIZED("验证失败", "UNAUTHORIZED"),
    /**
     * 格式校验失败
     */
    VERIFICATION_FAILED("格式校验失败", "VERIFICATION_FAILED"),
    /**
     * 格式校验失败
     */
    ERR("异常", "ERR");

    private String msg;
    private String code;

    ResultCodeEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
