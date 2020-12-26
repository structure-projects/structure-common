package cn.structure.common.entity;

import cn.structure.common.enums.ResultCodeEnum;
import cn.structure.common.exception.CommonException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>
 *     出参 ： 返回结果封装 - VO
 *     对微服务比较友好
 * </p>
 * @param <T> data部分的类型
 * @author chuck
 * @since 2020-12-26
 * @version 1.0.1
 */
@ApiModel(description = "出参 ： 返回结果封装 - VO")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {
    /**
     * 系统级别的CODE码
     */
    @ApiModelProperty(value = "系统级别的CODE码",example = "SUCCESS")
    private String code;
    /**
     * 系统级别的消息内容
     */
    @ApiModelProperty(value = "系统级别的消息内容",example = "成功！")
    private String msg;
    /**
     * 业务级别的code码
     */
    @ApiModelProperty(value = "业务级别的code码",example = "SUCCESS")
    private String subCode;
    /**
     * 业务级别的消息内容
     */
    @ApiModelProperty(value = "业务级别的消息内容",example = "成功！")
    private String subMsg;
    /**
     * 系统响应的时间戳
     */
    @ApiModelProperty(value = "系统响应的时间戳",example = "112345644446")
    private long timestamp;
    /**
     * 系统响应的回参数据是一个泛型
     */
    @ApiModelProperty(value = "系统响应的回参数据是一个泛型",example = "{}")
    private T data;

    /**
     * builder 一个成功的ResultVO
     * @param <T>
     * @return
     */
    public static <T> ResultVO success() {
        ResultVO<T> result = new ResultVO<>(
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMsg(),
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMsg(),
                (new Date()).getTime(),null
        );
        return result;
    }

    /**
     * builder 一个成功的ResultVO 入参为出参data即可
     * @param d
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(T d) {
        return builder(d).code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.SUCCESS.getCode()).timestamp((new Date()).getTime()).build();
    }

    /**
     * 异常的处理
     * @param message 异常信息
     * @return
     */
    public static ResultVO exception(String message) {
        return builder().code(ResultCodeEnum.ERR.getCode()).msg(message).timestamp((new Date()).getTime()).build();
    }
    /**
     * 异常的处理
     * @param ce 公共异常类
     * @return
     */
    public static ResultVO exception(CommonException ce) {
        return builder().code(ResultCodeEnum.ERR.getCode()).msg(ce.getMessage()).subCode(ce.getCode()).subMsg(ce.getMsg()).timestamp((new Date()).getTime()).build();
    }

    /**
     * 异常的处理
     * @param msg 异常消息
     * @param subCode 业务code
     * @param subMsg 业务消息
     * @return
     */
    public static ResultVO exception(String msg, String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.ERR.getCode()).msg(msg).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    /**
     * 业务失败的构建
     * @param subMsg 业务消息
     * @return
     */
    public static ResultVO fail(String subMsg) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.FAIL.getCode()).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    /**
     * 业务失败的构建
     * @param subCode 业务code
     * @param subMsg 业务消息
     * @return
     */
    public static ResultVO fail(String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).data(null).build();
    }

    /**
     * 业务失败的构建
     * @param subCode 业务code
     * @param subMsg 业务消息
     * @return
     */
    public static ResultVO fail(Integer subCode, String subMsg) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(subCode + "").subMsg(subMsg).timestamp((new Date()).getTime()).data(null).build();
    }

    /**
     * 业务失败但是仍然要返回数据
     * @param subMsg 失败的消息
     * @param data 返回的数据
     * @return
     */
    public static ResultVO fail(String subMsg, Object data) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.FAIL.getCode()).subMsg(subMsg).timestamp((new Date()).getTime()).data(data).build();
    }

    /**
     * 验证失败但是仍然要返回业务状态吗和业务消息还有data
     * @param subCode 业务状态吗
     * @param subMsg 业务消息
     * @param d 返回的data
     * @param <T> 泛型
     * @return
     */
    public static <T> ResultVO<T> fail(String subCode, String subMsg, T d) {
        return builder(d).code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).data(d).build();
    }

    /**
     * 短路的构建
     * @param subCode
     * @param subMsg
     * @return
     */
    public static ResultVO fallback(String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.FALLBACK.getCode()).msg(ResultCodeEnum.FALLBACK.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    /**
     * 无权限的构建
     * @param subCode
     * @param subMsg
     * @return
     */
    public static ResultVO unauthorized(String subCode, String subMsg) {
        return builder().code(ResultCodeEnum.UNAUTHORIZED.getCode()).msg(ResultCodeEnum.UNAUTHORIZED.getMsg()).subCode(subCode).subMsg(subMsg).timestamp((new Date()).getTime()).build();
    }

    /**
     * 格式校验失败返回的构建
     * @param verificationFailedMsgList
     * @return
     */
    public static ResultVO verificationFailed(List<VerificationFailedMsg> verificationFailedMsgList) {
        return builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMsg()).subCode(ResultCodeEnum.VERIFICATION_FAILED.getCode()).subMsg(ResultCodeEnum.VERIFICATION_FAILED.getMsg()).timestamp((new Date()).getTime()).data(verificationFailedMsgList).build();
    }

    /**
     * 没有这个资源返回的构建
     * @return
     */
    public static ResultVO notfound() {
        return builder().code(ResultCodeEnum.FALLBACK.getCode()).msg(ResultCodeEnum.FALLBACK.getMsg()).subCode(ResultCodeEnum.NOT_FOUND.getCode()).subMsg(ResultCodeEnum.NOT_FOUND.getMsg()).timestamp((new Date()).getTime()).build();
    }

    /**
     * 判断是否成功
     * @return
     */
    public boolean isSuccess() {
        return this.code.equals(ResultCodeEnum.SUCCESS.getCode()) && this.subCode.equals(ResultCodeEnum.SUCCESS.getCode());
    }

    /**
     * 判断是否为失败
     * @return
     */
    public boolean isFail() {
        return !this.isSuccess();
    }

    /**
     * 验证一个ResultVO是否成功
     * @param r
     * @return
     */
    public static boolean isSuccess(ResultVO r) {
        return r.code.equals(ResultCodeEnum.SUCCESS.getCode()) && r.subCode.equals(ResultCodeEnum.SUCCESS.getCode());
    }

    /**
     * 验证一个ResultVO是否失败
     * @param r
     * @return
     */
    public static boolean isFail(ResultVO r) {
        return !isSuccess(r);
    }

    /**
     * builder 一个ResultBuilder 无参数的构造
     * @param <T>
     * @return
     */
    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<T>();
    }

    /**
     * builder ResultBuilder 带data参数的构造
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultBuilder<T> builder(T t) {
        return new ResultBuilder<T>(t);
    }

    /**
     * 判断是否为 ResultVO 类型
     * @param other
     * @return
     */
    protected boolean canEqual(Object other) {
        return other instanceof ResultVO;
    }

    /**
     * 一个ResultBuilder方便链式调用
     * @param <T>
     */
    public static class ResultBuilder<T> {
        /**
         * 系统级别的CODE码
         */
        private String code;
        /**
         * 系统级别的消息内容
         */
        private String msg;
        /**
         * 业务级别的code码
         */
        private String subCode;
        /**
         * 业务级别的消息内容
         */
        private String subMsg;
        /**
         * 系统响应的时间戳
         */
        private long timestamp;
        /**
         * 系统响应的回参数据是一个泛型
         */
        private T data;


        /**
         * 无参数构造器
         */
        ResultBuilder() {
        }

        /**
         * 带data的构造器
         * @param t
         */
        ResultBuilder(T t) {
            this.data = t;
        }

        /**
         * 设置系统编码
         * @param code
         * @return
         */
        public ResultBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        /**
         * 设置一个系统消息
         * @param msg
         * @return
         */
        public ResultBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        /**
         * 设置一个业务编码
         * @param subCode
         * @return
         */
        public ResultBuilder<T> subCode(String subCode) {
            this.subCode = subCode;
            return this;
        }

        /**
         * 设置一个业务描述
         * @param subMsg
         * @return
         */
        public ResultBuilder<T> subMsg(String subMsg) {
            this.subMsg = subMsg;
            return this;
        }

        /**
         * 设置时间戳
         * @param timestamp
         * @return
         */
        public ResultBuilder<T> timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        /**
         * 设置 data
         * @param data
         * @return
         */
        public ResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        /**
         * 构建一个ResultVO
         * @return
         */
        public ResultVO<T> build() {
            return new ResultVO(this.code, this.msg, this.subCode, this.subMsg, this.timestamp, this.data);
        }
    }

}

