package com.xmairtravel.core.exception;


/**
 * 整个应用的所有异常代码都要放在这里
 *
 * @author liuyiling
 */
public enum ErrorEnums implements IErrorCode {

    /**
     * 大类异常
     */
    SUCCESS(200, "响应正常"),
    PARAM_ERROR(201, "参数错误"),
    REQUEST_ERROR(300, "请求异常"),
    BUSINESS_ERROR(400, "业务异常"),
    UNAUTHORIZED(401, "登陆信息无效"),
    SERVER_ERROR(500, "服务异常"),

    NO_TOKEN(402, "未检测到token"),
    INVALID_TOKEN(403, "无效token"),
    NOT_LOGIN(401001, "无访问权限，请登录"),

    /**
     * 小类异常,前3位为大类异常码，后4位为小类异常码
     */
    USER_NOT_FOUND(2010001, "未找到用户"),
    FILE_NAME_NOT_FOUND(201015, "文件名称为空"),
    FILE_NOT_FOUND(201016, "文件为空"),
    FILE_OVERSIZE(201017, "文件不得超过5M"),
    FILE_NOT_PICTURE(201018, "上传文件不是图片，请重新上传"),
    FILE_FORMAT_ERROR(201019,"上传文件格式错误,格式仅为JPEG,GIF,PNG"),

    SAVE_FILE_ERROR(400011, "保存文件失败"),
    DELETE_FILE_ERROR(400012, "删除文件失败"),
    FILE_UPLOAD_ERROR(400017, "上传文件失败"),

    DELETE_REFUSED(500001, "无法删除，是否强制删除"),
    API_INVOKE_ERROR(500002, "调用外部接口异常"),
    INPUT_STREAM_NOT_FOUND(500004, "得到文件输入流失败"),
    OUTPUT_STREAM_NOT_FOUND(500005, "得到文件输出流失败"),



    THIRD_PARTY_API_INVOKE_ERROR(600001, "调用第三方接口异常");





    private Integer code;
    private String message;

    ErrorEnums(Integer errorCode, String errorStr) {
        this.code = errorCode;
        this.message = errorStr;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer errorCode) {
        this.code = errorCode;
    }

    @Override
    public String  getMessage() {
        return message;
    }

    public void setMessage(String errorStr) {
        this.message = errorStr;
    }
}
