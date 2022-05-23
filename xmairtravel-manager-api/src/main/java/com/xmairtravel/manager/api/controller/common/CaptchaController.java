package com.xmairtravel.manager.api.controller.common;

import com.google.code.kaptcha.Producer;
import com.xmairtravel.core.common.RedisCache;
import com.xmairtravel.core.server.wechat.entity.Constants;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.utils.sign.Base64;
import com.xmairtravel.core.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 * 
 * @author ruoyi
 */
@RestController
public class CaptchaController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Value("${spring.captchaType:math}")
    private String captchaType;

    @Autowired
    private RedisCache redisCache;
    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ResultBean getCode(HttpServletResponse response) throws IOException
    {
        ResultBean resultBean = ResultBean.success();

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return ResultBean.error(e.getMessage());
        }

        resultBean.put("uuid", uuid);
        resultBean.put("img", Base64.encode(os.toByteArray()));
        return resultBean;
    }
}
