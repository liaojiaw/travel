package com.xmairtravel.core.server.wechat.entity;

import com.xmairtravel.core.utils.text.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 返回值的封装类
 * 例如：
 * {
 * "code": 300,
 * "errStr": "ARGUMENTS_INVALID",
 * "message": "非法的参数数值:Validation failed for argument at index 3 in method: public com.xmair.core.util.JsonStringResult com.xmair.restapi.controller.OfferController.searchOffer(com.xmair.promotion.model.distribution.Channel,java.lang.String,java.lang.String,com.xmair.contractmodel.offer.request.FlightOfferRequest), with 1 error(s): [Field error in object 'flightOfferRequest' on field 'itineraries': rejected value [null]; codes [NotNull.flightOfferRequest.itineraries,NotNull.itineraries,NotNull.java.util.List,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [flightOfferRequest.itineraries,itineraries]; arguments []; default message [itineraries]]; default message [may not be null]] ",
 * "traceId": "e6b65dac6f44be67daa05657b2c5d8e7"
 * "data":{}
 * }
 *
 * @param <T>
 * @author liuyiling
 */
@Data
public class ResultBean<T> extends HashMap<String, Object> implements Serializable {

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
    private String message;
    @ApiModelProperty(value = "数据内容", example = "SUCCESS", required = true)
    private T data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public ResultBean()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public ResultBean(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ResultBean(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResultBean success()
    {
        return ResultBean.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResultBean success(Object data)
    {
        return ResultBean.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResultBean success(String msg)
    {
        return ResultBean.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResultBean success(String msg, Object data)
    {
        return new ResultBean(HttpStatus.OK.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResultBean error()
    {
        return ResultBean.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultBean error(String msg)
    {
        return ResultBean.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResultBean error(String msg, Object data)
    {
        return new ResultBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultBean error(int code, String msg)
    {
        return new ResultBean(code, msg, null);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public ResultBean<T> put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
