package com.xmairtravel.core.config.http;

import com.xmairtravel.core.config.http.interceptor.LoggingRequestInterceptor;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Liao
 * @version 1.0
 * @date 2021/8/18 12:04
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 在使用@Autowired注解时，首先在容器中查询对应类型的bean
     * 如果查询结果Bean刚好为一个，自动注入
     * 如果查询结果Bean不止一个，通过@Qualifier注解指定自动装配Bean的名称
     * 如果没有查询到对应类型的Bean，由于默认@Autowired(required=true)，会抛出异常，解决方法是使用@Autoiwired(quired=false)
     *
     * @Autowired(quired=true)意味着依赖是必须的
     * @Autowired(quired=false)等于告诉 Spring：在找不到匹配 Bean 时也不报错
     */
    @Autowired
    public MappingJackson2HttpMessageConverter customerJsonConvert;

    @Autowired
    private OkHttpClient okHttpClientl;

    /**
     * 默认情况下bean的名称和方法名称相同，你也可以使用name属性来指定
     *
     * @return
     */
    @Primary
    @Bean
    public ClientHttpRequestFactory okHttp3Factory() {
        OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClientl);
        //设置超时时间
//        okHttp3ClientHttpRequestFactory.setConnectTimeout(6000);
        return okHttp3ClientHttpRequestFactory;
    }

    @Primary
    @Bean
    public AsyncClientHttpRequestFactory asyncClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClientl);
        //设置超时时间
//        okHttp3ClientHttpRequestFactory.setConnectTimeout(6000);
        return okHttp3ClientHttpRequestFactory ;
    }

    @Bean(name = "normalRestTemplate")
    public RestTemplate restTemplate() {
        return commonRestTemplate(okHttp3Factory());
    }

    @Bean(name = "asyncNormalRestTemplate")
    public AsyncRestTemplate asyncNormalRestTemplate() {
        return asyncCommonRestTemplate(asyncClientHttpRequestFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClientNoTracing() {
        OkHttpClient.Builder builder = okhttpBuilder();
        /**
         * 因为我们使用了okHttpClient,所以原来在application.properties中的配置
         * ribbon.ConnectTimeout=1000
         * ribbon.ReadTimeout=10000
         * 配置无效，这些配置是用在ribbon-httpclient中的
         */
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1000);
        dispatcher.setMaxRequestsPerHost(200);
        return builder.build();
    }

    private OkHttpClient.Builder okhttpBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(20, 30, TimeUnit.MINUTES))
                .retryOnConnectionFailure(true);
    }

    private RestTemplate commonRestTemplate(ClientHttpRequestFactory okHttp3Factory) {
        RestTemplate restTemplate = new RestTemplate(okHttp3Factory);
        restTemplate.setMessageConverters(setHttpClientMessageConverter());
        restTemplate.setInterceptors(Arrays.asList(new LoggingRequestInterceptor()));
        //给请求的返回值加上自己的业务处理
//        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    private AsyncRestTemplate asyncCommonRestTemplate(AsyncClientHttpRequestFactory okHttp3Factory) {
        AsyncRestTemplate restTemplate = new AsyncRestTemplate(okHttp3Factory);
        restTemplate.setMessageConverters(setHttpClientMessageConverter());
        restTemplate.setInterceptors(Arrays.asList(new LoggingRequestInterceptor()));
        //给请求的返回值加上自己的业务处理
//        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    private List<HttpMessageConverter<?>> setHttpClientMessageConverter() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(customerJsonConvert);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        return messageConverters;
    }
}
