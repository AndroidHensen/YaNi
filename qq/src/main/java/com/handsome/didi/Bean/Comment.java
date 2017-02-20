package com.handsome.didi.Bean;

import cn.bmob.v3.BmobObject;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/6.
 */
public class Comment  extends BmobObject {

    //用户id
    private String U_OID;
    //评论内容
    private String content;
    //评论时间
    private String date;
    //商品id
    private String S_OID;

    public String getS_OID() {
        return S_OID;
    }

    public void setS_OID(String s_OID) {
        S_OID = s_OID;
    }

    public String getU_OID() {
        return U_OID;
    }

    public void setU_OID(String u_OID) {
        U_OID = u_OID;
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


}
