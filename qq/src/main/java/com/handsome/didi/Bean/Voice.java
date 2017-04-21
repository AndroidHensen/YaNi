package com.handsome.didi.Bean;

import java.util.ArrayList;

/**
 * 语音对象封装
 *
 * @author Kevin
 * @date 2015-8-16
 */
public class Voice {

    /*由GSONFormat生成的语音识别实体*/
    public ArrayList<WSBean> ws;

    public class WSBean {
        public ArrayList<CWBean> cw;
    }

    public class CWBean {
        public String w;
    }
}
