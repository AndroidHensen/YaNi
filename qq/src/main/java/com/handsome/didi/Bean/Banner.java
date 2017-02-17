package com.handsome.didi.Bean;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/1/28.
 */
public class Banner {
    private long id;
    private String img_url;
    private String go_url;

    public String getGo_url() {
        return go_url;
    }

    public void setGo_url(String go_url) {
        this.go_url = go_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
