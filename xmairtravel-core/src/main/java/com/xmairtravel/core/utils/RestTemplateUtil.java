package com.xmairtravel.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.config.http.interceptor.LoggingRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class RestTemplateUtil {
    /**
     * 实际执行请求的template
     */
    private static RestTemplate restTemplate = new RestTemplate();

    static {
//       restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        restTemplate.setMessageConverters(Arrays.asList(converter,jackson2HttpMessageConverter,formHttpMessageConverter));
        restTemplate.setInterceptors(Arrays.asList(new LoggingRequestInterceptor()));
    }
    public static String put( Object entity,String url){
        return put(entity,url,String.class);
    }

    public static  <T>T put(Object entity,String url, Class<T> cls){
        logParams(RequestMethod.PUT,url,entity);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Object> request = new HttpEntity<>(entity,headers);
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.PUT, request, cls);
        T result = exchange.getBody();
        log.info("put result:{}",result);
        return result;
    }

    public static String post( Object entity,String url){
        return post(url,entity,String.class);
    }

    /**
     * 得到 参数的字符串
     * @param entity
     * @return
     */
    private static String getStringParams(Object entity){
        if(entity instanceof String){
            return entity.toString();
        }
        return JSONObject.toJSONString(entity);
    }

    private static void logParams(RequestMethod method, String url, Object entity){
        String params=getStringParams(entity);
        log.info("{} url:{}  params:{}",method,url,params);
    }

    /**
     * json请求,可以指定header
     */
    public static <T>T post(String url, Object entity, Map<String,String> headerMap,Class<T> cls){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(!CollectionUtils.isEmpty(headerMap)){
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.set(entry.getKey(),entry.getValue());
            }
        }

        HttpEntity<Object> request = new HttpEntity<>(entity,headers);
        return executePost(url,request,cls);

    }
    /**
     * json请求
     */
    public static <T>T post(String url, Object entity, Class<T> cls){
        return post(url,entity,null,cls);
    }

    /**
     * 表单请求
     */
    public static <T>T formPost(String url, MultiValueMap<String, Object> params, Class<T> cls){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Object> request = new HttpEntity<>(params,headers);
        return executePost(url,request,cls);
    }

    /**
     * post 请求
     */
    private static <T>T executePost(String url, HttpEntity<Object> request, Class<T> cls){
        logParams(RequestMethod.POST,url,request);
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.POST, request, cls);
        T body = exchange.getBody();
        log.info("post result:{}", body);
        return body;
    }

    public static <T>T upload(String url, Map<String, Object> updateMap, Class<T> returnClass, Resource resource) {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        parts.add("file",resource);
        for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            log.info("--->upload param key:{},val:{}",key,value);
            parts.add(key,value);
        }

        HttpEntity<Object> httpEntity = new HttpEntity<>(parts, headers);

        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, returnClass);

        T body = exchange.getBody();
        return null;
    }

    private static <T>T executeGet(String url, HttpEntity<Object> request, Class<T> cls){
        logParams(RequestMethod.GET,url,request);
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.GET, request, cls);
        T body = exchange.getBody();
        log.info("get result:{}", body);
        return body;
    }

    public static  String get(String url){
        return get(url,null,String.class);
    }
    public static  <T>T get(String url, Map<String,Object> params,Map<String,String> headerMap, Class<T> cls){
        String reqUrl = buildUrl(url,params);
        HttpHeaders headers = new HttpHeaders();
        if(headerMap != null){
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.set(entry.getKey(),entry.getValue());
            }
        }
        HttpEntity<Object> request = new HttpEntity<>(headers);
        return executeGet(reqUrl,request,cls);
    }
    /**
     * get 请求
     */
    public static  <T>T get(String url, Map<String,Object> params, Class<T> cls){
        String reqUrl = buildUrl(url,params);
        log.info("get url:{}",reqUrl);
        T result = restTemplate.getForObject(reqUrl, cls);
        log.info("get result:{}", getStringParams(result));
        return result;
    }

    /**
     * 设置的url
     */
    public static String buildUrl(String url, Map<String,Object> params){
        StringBuilder urlBuiler=new StringBuilder(url);
        if(!url.contains("?")){
            urlBuiler.append("?");
        }
        if(!CollectionUtils.isEmpty(params)){
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                urlBuiler.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return urlBuiler.toString();
    }


}
