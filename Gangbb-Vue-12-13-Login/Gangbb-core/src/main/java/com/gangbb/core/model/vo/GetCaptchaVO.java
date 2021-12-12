package com.gangbb.core.model.vo;

import java.io.Serializable;

/**
 * @Author Gangbb
 * @Description 获取验证码接口 VO
 * @Date 2021/7/16
 **/
public class GetCaptchaVO implements Serializable {

    private static final long serialVersionUID = 7021881515138847044L;

    /**
     * 验证码唯一标识符uuid
     */
    private String UUid;

    /**
     * 验证码图片
     */
    private String img;

    public String getUUid() {
        return UUid;
    }

    public void setUUid(String UUid) {
        this.UUid = UUid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}