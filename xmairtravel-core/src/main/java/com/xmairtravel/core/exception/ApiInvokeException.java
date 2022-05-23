package com.xmairtravel.core.exception;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * 调用外部接口出现的异常
 *
 * @author liuyiling
 * @date on 2018/12/24
 */
@Data
public class ApiInvokeException extends CommonException {
    /**
     * 调用的URL
     */
    private String apiUrl;

    /**
     * 方法，GET/POST
     */
    private HttpMethod requestMethod;

    /**
     * 请求头
     */
    private HttpHeaders header;

    /**
     * 请求参数：如果是Map类型请自行序列化进来
     */
    private String requestBody;

    /**
     * 响应参数：如果是Map类型请自行序列化进来
     */
    private String responseBody;

    /**
     * 导致的异常
     */
    private Exception causeException;

    public ApiInvokeException(String apiUrl, HttpMethod requestMethod, HttpHeaders header,
                              String requestBody, String responseBody, Exception causeException) {
        this(null,apiUrl,requestMethod,header,requestBody,responseBody,causeException);
    }

    public ApiInvokeException(Object data, String apiUrl, HttpMethod requestMethod, HttpHeaders header,
                              String requestBody, String responseBody, Exception causeException) {
        super(data);
        this.apiUrl = apiUrl;
        this.requestMethod = requestMethod;
        this.header = header;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.causeException = causeException;
    }

    @Override
    public String toString() {
        return "ApiInvokeException{" +
                "apiUrl='" + apiUrl + '\'' +
                ", requestMethod=" + requestMethod +
                ", header=" + header +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", causeException=" + causeException +
                '}';
    }
}
