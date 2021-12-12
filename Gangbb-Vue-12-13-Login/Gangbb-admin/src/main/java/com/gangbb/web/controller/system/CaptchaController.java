package com.gangbb.web.controller.system;

import com.gangbb.common.constant.Constants;
import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.common.utils.redis.RedisUtil;
import com.gangbb.common.utils.sign.Base64;
import com.gangbb.common.utils.uuid.IdUtils;
import com.gangbb.core.model.vo.GetCaptchaVO;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Author Gangbb
 * @Description 验证码操作处理
 * @Date 2021/7/16
 **/
@RestController
public class CaptchaController
{
    //日志记录
    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisUtil redisUtil;

    // 验证码类型
    @Value("${gangbb.captchaType}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ApiRestResponse getCode(HttpServletResponse response) throws IOException
    {
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
        // 写入缓存
        redisUtil.set(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return ApiRestResponse.error("B0002", e.getMessage());
        }
        log.info("验证码信息capStr：{}，code：{}",capStr, code);
        // 验证码图片
        String encode = Base64.encode(os.toByteArray());
        GetCaptchaVO captchaVO = new GetCaptchaVO();
        captchaVO.setUUid(uuid);
        captchaVO.setImg(encode);

        return ApiRestResponse.success(captchaVO);
    }
}
