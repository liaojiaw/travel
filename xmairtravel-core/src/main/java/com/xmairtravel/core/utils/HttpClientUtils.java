package com.xmairtravel.core.utils;

import com.xmairtravel.core.exception.ApiInvokeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author Liao
 * @version 1.0
 * @date 2021/8/19 9:48
 */
@Component
public class HttpClientUtils {

    private static RestTemplate normalRestTemplate;
    private static AsyncRestTemplate asyncNormalRestTemplate;

    @Autowired
    @Qualifier("normalRestTemplate")
    private RestTemplate normalRestTemplateInit;

    @Autowired
    @Qualifier("asyncNormalRestTemplate")
    private AsyncRestTemplate asyncNormalRestTemplateInit;

    /**
     * 对于自动选择方式的调用方法，这个参数决定了HTTP在调用时会选用负载还是直连方式
     */
    @Value("${use.ribbon.ind:true}")
    private boolean useRibbonInd;

    private static boolean useLbInd;

    //PostConstruct 注释用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化。此方法必须在将类放入服务之前调用。
    @PostConstruct
    public void init() {
        normalRestTemplate = normalRestTemplateInit;
        asyncNormalRestTemplate = asyncNormalRestTemplateInit;
        useLbInd = useRibbonInd;
    }


    /***-----------------------------------------------GET-----------------------------------------------*/

    public static <T> T syncGet(String dstUrl, Map<String, Object> parameterMap,Class<T> clazz) {
        return syncGet(dstUrl,parameterMap,null,clazz);
    }

    public static <T> T syncGet(String dstUrl, Map<String, Object> parameterMap, HttpHeaders headers,Class<T> clazz) {
        String dstUrlWithParam = integrateGetUri(dstUrl, parameterMap);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return doHttpRequest(dstUrlWithParam,entity,clazz,HttpMethod.GET,normalRestTemplate);
    }

    public static <T> T syncGet(String dstUrl, Map<String, Object> parameterMap, HttpHeaders headers,
                                 ParameterizedTypeReference<T> typeRef) {
        String dstUrlWithParam = integrateGetUri(dstUrl, parameterMap);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return doHttpRequest(dstUrlWithParam,entity,typeRef,HttpMethod.GET,normalRestTemplate);
    }

    public static <T> T asyncGet( String dstUrl, Map<String, Object> parameterMap,
                                                                     HttpHeaders headers,Class<T> clazz) {
        String dstUrlWithParam = integrateGetUri(dstUrl, parameterMap);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return doAsyncHttpRequest(dstUrlWithParam,entity,clazz,HttpMethod.GET,asyncNormalRestTemplate);
    }

    public static <T> T asyncGet( String dstUrl, Map<String, Object> parameterMap,
                                   HttpHeaders headers,ParameterizedTypeReference<T> typeRef) {
        String dstUrlWithParam = integrateGetUri(dstUrl, parameterMap);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return doAsyncHttpRequest(dstUrlWithParam,entity,typeRef,HttpMethod.GET,asyncNormalRestTemplate);
    }


    /***-----------------------------------------------POST-----------------------------------------------*/
    public static <T> T syncPost(String dstUrl, Object parameterMap, HttpHeaders headers,Class<T> clazz) {
        HttpEntity<String> entity = new HttpEntity(parameterMap,headers);
        return doHttpRequest(dstUrl,entity,clazz,HttpMethod.POST,normalRestTemplate);
    }

    public static <T> T syncPost(String dstUrl, Map<String, Object> parameterMap, HttpHeaders headers,
                                 ParameterizedTypeReference<T> typeRef) {
        HttpEntity<String> entity = new HttpEntity(parameterMap,headers);
        return doHttpRequest(dstUrl,entity,typeRef,HttpMethod.POST,normalRestTemplate);
    }

    public static <T> T asyncPost( String dstUrl, Map<String, Object> parameterMap,
                                   HttpHeaders headers,Class<T> clazz) {
        HttpEntity<String> entity = new HttpEntity(parameterMap,headers);
        return doAsyncHttpRequest(dstUrl,entity,clazz,HttpMethod.POST,asyncNormalRestTemplate);
    }

    public static <T> T asyncPost( String dstUrl, Map<String, Object> parameterMap,
                                   HttpHeaders headers,ParameterizedTypeReference<T> typeRef) {
        HttpEntity<String> entity = new HttpEntity(parameterMap,headers);
        return doAsyncHttpRequest(dstUrl,entity,typeRef,HttpMethod.POST,asyncNormalRestTemplate);
    }


    private static <T> T doAsyncHttpRequest(String dstUrl, HttpEntity entity,
                          ParameterizedTypeReference<T> typeRef,HttpMethod httpMethod, AsyncRestTemplate asyncNormalRestTemplate) {
        T result = null;
        try {
            ListenableFuture<ResponseEntity<T>> response = asyncNormalRestTemplate.exchange(dstUrl, httpMethod, entity, typeRef);
            result = response.get().getBody();
            if(!response.get().getStatusCode().is2xxSuccessful()){
                throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",null);
            }
        } catch (Exception e) {
            throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",e);
        }
        return result;
    }

    private static <T> T doAsyncHttpRequest(String dstUrl, HttpEntity entity, Class<T> clazz,HttpMethod httpMethod,
                                            AsyncRestTemplate asyncNormalRestTemplate){
        T result = null;
        try {
            ListenableFuture<ResponseEntity<T>> response = asyncNormalRestTemplate.exchange(dstUrl, httpMethod, entity, clazz);
            result = response.get().getBody();
            if(!response.get().getStatusCode().is2xxSuccessful()){
                throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",null);
            }
        } catch (Exception e) {
            throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",e);
        }
        return result;
    }



    private static <T> T doHttpRequest(String dstUrl, HttpEntity entity,ParameterizedTypeReference<T> typeRef,
                                       HttpMethod httpMethod, RestTemplate restTemplate) {
        T result = null;
        try {
            ResponseEntity<T> response = restTemplate.exchange(dstUrl, httpMethod, entity, typeRef);
            result = response.getBody();
            if(!response.getStatusCode().is2xxSuccessful()){
                throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",null);
            }
        } catch (Exception e) {
            if(e instanceof ApiInvokeException){
                throw e;
            }
            throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",e);
        }
        return result;
    }


    private static <T> T doHttpRequest(String dstUrl, HttpEntity entity,Class<T> clazz,HttpMethod httpMethod,
                                       RestTemplate restTemplate) {
        T result = null;
        try {
            ResponseEntity<T> response = restTemplate.exchange(dstUrl, httpMethod, entity, clazz);
            result = response.getBody();
            if(!response.getStatusCode().is2xxSuccessful()){
                throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",null);
            }
        } catch (Exception e) {
            if(e instanceof ApiInvokeException){
                throw e;
            }
            throw new ApiInvokeException(dstUrl,httpMethod,entity.getHeaders(),entity.getBody()+"",result+"",e);
        }
        return result;
    }


    /**
     * 拼接URL参数的方法
     *
     * @param url
     * @param parameterMap
     * @return
     * @throws URISyntaxException
     */
    public static String integrateGetUri(String url, Map<String, Object> parameterMap){
        if (parameterMap == null) {
            return url;
        }
//        URIBuilder uriBuilder = new URIBuilder(url);
        StringBuffer params = new StringBuffer();
        boolean first = true;
        for (Map.Entry<String, Object> entity : parameterMap.entrySet()) {
            if(Objects.isNull(entity.getValue()) || StringUtils.isEmpty(entity.getValue().toString())){
                continue;
            }
            if(first){
                params.append(entity.getKey()+"="+entity.getValue().toString());
                first = !first;
            }else{
                params.append("&"+entity.getKey()+"="+entity.getValue().toString());
            }
        }
        return url+"?"+params;
    }

    public static HttpHeaders buildAcceptJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        return headers;
    }

}
