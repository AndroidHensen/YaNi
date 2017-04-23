package com.handsome.didi.Bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by handsome on 2016/4/20.
 */
public class Delivery {

    /*根据GSONFormat生成的快递查询*/
    public String resultcode;
    public String reason;
    public ResultBean result;

    public static class ResultBean {
        public String company;
        public String com;
        public String no;
        public String status;
        public List<ListBean> list;

        public static class ListBean {
            public String datetime;
            public String remark;
            public String zone;
        }
    }
}
