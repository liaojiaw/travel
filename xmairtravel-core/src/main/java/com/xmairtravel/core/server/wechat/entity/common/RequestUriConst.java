package com.xmairtravel.core.server.wechat.entity.common;

public abstract class RequestUriConst {
    private RequestUriConst(){}
    public final static String WX_GETOPENID =
            "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";
    public final static String WX_LOGIN =
            "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";
    public final static String WX_TOKEN =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";


}
