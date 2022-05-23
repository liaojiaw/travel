package com.xmairtravel.core.config.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;


@Configuration
public class CacheConfig {

    //微信token CacheManager
    @Primary
    @Bean(name = "wxTokenCache")
    public CacheManager wxTokenCache() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder()
                //cache的初始容量值,整数，表示能存储多少个缓存对象。
                .initialCapacity(10)
                //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight(最大权重)不可以同时使用，
                // 最大容量，如果缓存中的数据量超过这个数值，Caffeine 会有一个异步线程来专门负责清除缓存，按照指定的清除策略来清除掉多余的缓存。
                // 注意：比如最大容量是 2，此时已经存入了2个数据了，此时存入第3个数据，触发异步线程清除缓存，在清除操作没有完成之前，
                // 缓存中仍然有3个数据，且 3 个数据均可读，缓存的大小也是 3，只有当缓存操作完成了，缓存中才只剩 2 个数据，至于清除掉了哪个数据，
                // 这就要看清除策略了
                .maximumSize(10)
                //最后一次写入或者访问后过久过期
                .expireAfterWrite(7100, TimeUnit.SECONDS)
                //最大权重，存入缓存的每个元素都要有一个权重值，当缓存中所有元素的权重值超过最大权重时，就会触发异步清除。
//                .maximumWeight(1000)
                //需要配合最大权重使用，指定计算权重的方法
//                .weigher();
                //同步监听器，同步监听器一个实现了接口 CacheWriter 的实例化对象，我们需要自定义接口的实现类
//                .writer(new CacheWriter() {
//                    @Override
//                    public void write(@NonNull Object o, @NonNull Object o2) {
//
//                    }
//
//                    @Override
//                    public void delete(@NonNull Object o, @Nullable Object o2, @NonNull RemovalCause removalCause) {
//
//                    }
//                })
//                //写操作完成后多久才将数据刷新进缓存中，两个参数只是用于设置时间长短的,需要设置cacheLoader
//                .refreshAfterWrite(10, TimeUnit.SECONDS)
                ;
        cacheManager.setCaffeine(caffeine);
        //cacheManager.setCacheNames(names);//根据名字可以创建多个cache，但是多个cache使用相同的策略
        cacheManager.setAllowNullValues(false);//是否允许值为空
        return cacheManager;
    }


    //通用缓存
    @Bean(name = "nomalCache")
    public CacheManager nomalCache() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }


    @Bean("caffeineCache")
    public LoadingCache getCache(@Qualifier("cacheLoader") CacheLoader cacheLoader) {

        return Caffeine.newBuilder()
                //cache的初始容量值
                .initialCapacity(100)
                //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight(最大权重)不可以同时使用，
                .maximumSize(1000)
                //最后一次写入或者访问后过久过期
                .expireAfterAccess(500, TimeUnit.SECONDS)
                //创建或更新之后多久刷新,需要设置cacheLoader
                .refreshAfterWrite(10, TimeUnit.SECONDS).build(cacheLoader);
    }

    /**
     * 必须要指定这个Bean，refreshAfterWrite配置属性才生效
     */
    @Bean(name = "cacheLoader")
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<Object, Object>() {

            //第一次请求访问这个方法，这时缓存为空，可以在这时去请求数据库填满缓存
            @Override
            public Object load(Object key) throws Exception {

                return null;
            }


            // 过期刷新缓存是回调这个方法，这时候应该去请求数据库
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                System.out.println("--refresh--:" + key);
                return oldValue;
            }
        };
    }

}