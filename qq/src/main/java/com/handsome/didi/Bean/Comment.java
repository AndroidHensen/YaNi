package com.handsome.didi.Bean;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/6.
 */
public class Comment {

    private long id;
    //用户id
    private long U_ID;
    //评论内容
    private String content;
    //评论时间
    private String date;
    //商品id
    private long S_ID;

    public long getS_ID() {
        return S_ID;
    }

    public void setS_ID(long s_ID) {
        S_ID = s_ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getU_ID() {
        return U_ID;
    }

    public void setU_ID(long u_ID) {
        U_ID = u_ID;
    }
}
