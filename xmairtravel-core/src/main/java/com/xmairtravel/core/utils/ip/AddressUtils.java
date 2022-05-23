package com.xmairtravel.core.utils.ip;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.server.wechat.entity.Constants;
import com.xmairtravel.core.utils.HttpClientUtils;
import com.xmairtravel.core.utils.text.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取地址类
 * 
 * @author ruoyi
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        try
        {
            Map param = new HashMap<>();
            param.put("ip",ip);
            param.put("json",true);
            String rspStr = HttpClientUtils.syncGet(IP_URL, param, String.class);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            return String.format("%s %s", region, city);
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}
