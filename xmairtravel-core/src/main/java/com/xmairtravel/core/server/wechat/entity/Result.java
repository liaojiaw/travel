package com.xmairtravel.core.server.wechat.entity;

import com.xmairtravel.core.exception.ErrorEnums;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";
    /**
     * 业务层返回标识（注意与HTTP进行有效区分）
     */
    @ApiModelProperty(value = "返回状态码", example = "200", required = true)
    private Integer code;
    @ApiModelProperty(value = "返回消息", example = "SUCCESS", required = true)
    private String msg;
    @ApiModelProperty(value = "数据内容", example = "SUCCESS", required = true)
    private T data;

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success()
    {
        return Result.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data)
    {
        return Result.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Result success(String msg)
    {
        return Result.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Result success(String msg, Object data)
    {
        return new Result(ErrorEnums.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error()
    {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(String msg)
    {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Result error(String msg, Object data)
    {
        return new Result(ErrorEnums.SERVER_ERROR.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(Integer code, String msg)
    {
        return new Result(code, msg, null);
    }

}

