package cn.structure.common.entity;

import cn.structure.common.enums.ExceptionRsType;
import cn.structure.common.enums.ResultCodeEnum;
import cn.structure.common.exception.CommonException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 出参：返回结果 - VO
 * 对单体比较友好也可以支持微服务
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2020/12/26 21:32
 */
@Data
@ApiModel(description = "出参：返回结果 - VO")
public class ResResultVO<T> {

    /**
     * 业务状态码
     */
    @ApiModelProperty(value = "业务状态码",example = "SUCCESS")
    private String code;

    /**
     * 返回的消息内容
     */
    @ApiModelProperty(value = "返回的消息内容",example = "成功！")
    private String message;

    /**
     * 业务是否成功
     */
    @ApiModelProperty(value = "业务是否成功",example = "true")
    private Boolean success;

    /**
     * 返回的数据
     */
    @ApiModelProperty(value = "返回的数据",example = "{}")
    private T data;

    /**
     * 系统响应的时间戳
     */
    @ApiModelProperty(value = "系统响应的时间戳",example = "112345644446")
    private Long timestamp;

    /**
     * 构建一个成功的ResResultVO
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResResultVO success(T t){
        ResResultVO<T> resResultVO = new ResResultVO<>();
        resResultVO.setCode(ResultCodeEnum.SUCCESS.getCode());
        resResultVO.setMessage(ResultCodeEnum.SUCCESS.getMsg());
        resResultVO.setData(t);
        resResultVO.setSuccess(true);
        resResultVO.setTimestamp(System.currentTimeMillis());
        return resResultVO;
    }

    /**
     * 构建一个业务失败的ResResultVO
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResResultVO fail(String code,String message){
        ResResultVO<T> resResultVO = new ResResultVO<>();
        resResultVO.setCode(code);
        resResultVO.setMessage(message);
        resResultVO.setSuccess(false);
        resResultVO.setTimestamp(System.currentTimeMillis());
        return resResultVO;
    }

    /**
     * 构建一个业务失败的带有出参结果的ResResultVO
     * @param code
     * @param message
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResResultVO fail(String code,String message,T t){
        ResResultVO<T> resResultVO = new ResResultVO<>();
        resResultVO.setCode(code);
        resResultVO.setMessage(message);
        resResultVO.setData(t);
        resResultVO.setSuccess(false);
        resResultVO.setTimestamp(System.currentTimeMillis());
        return resResultVO;
    }

    /**
     * 构建一个已知异常的出参
     * @param code
     * @param message
     * @return
     */
    public static ResResultVO exception(String code,String message) {
        return fail(code,message);
    }

    /**
     * 构建一个CommonException异常的出参 用于业务异常的捕获
     * @param ce
     * @return
     */
    public static ResResultVO exception(CommonException ce) {
        return fail(ce.getCode(),ce.getMessage());
    }

    /**
     * 构建一个未知异常的出参
     * @param <T>
     * @return
     */
    public static <T> ResResultVO exception() {
        ResResultVO<T> resResultVO = new ResResultVO<>();
        resResultVO.setCode(ResultCodeEnum.ERR.getCode());
        resResultVO.setMessage(ResultCodeEnum.ERR.getMsg());
        resResultVO.setSuccess(false);
        resResultVO.setTimestamp(System.currentTimeMillis());
        return resResultVO;
    }
    /**
     * 构建一个未知异常需要输出消息提示的的出参
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResResultVO exception(String message) {
        ResResultVO<T> resResultVO = new ResResultVO<>();
        resResultVO.setCode(ResultCodeEnum.ERR.getCode());
        resResultVO.setMessage(message);
        resResultVO.setSuccess(false);
        resResultVO.setTimestamp(System.currentTimeMillis());
        return resResultVO;
    }

    /**
     * build 一个失败的返回结果
     * @param resultCodeEnum
     * @return
     */
    public static ResResultVO build(ResultCodeEnum resultCodeEnum) {
        return fail(resultCodeEnum.getCode(),resultCodeEnum.getMsg());
    }

    /**
     * build 一个没有权限的返回结果
     * @param exceptionRsType
     * @return
     */
    public static ResResultVO build(ExceptionRsType exceptionRsType) {
        return fail(exceptionRsType.getCode(),exceptionRsType.getMsg());
    }
}
