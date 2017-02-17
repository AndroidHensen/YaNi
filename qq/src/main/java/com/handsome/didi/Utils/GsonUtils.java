package com.handsome.didi.Utils;

import android.content.Context;

import com.google.gson.Gson;
import com.handsome.didi.Bean.Delivery;
import com.handsome.didi.Bean.Voice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/17.
 */
public class GsonUtils {

    /**
     * 解析语音json
     *
     * @param resultString
     * @return
     */
    public static String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }

    /**
     * 解析快递
     * @param context
     * @param resultString
     * @return
     */
    public static String[] parseDelivery(Context context , String resultString) {
        String [] result = null;
        Gson gson = new Gson();
        Delivery deliveryBean = gson.fromJson(resultString, Delivery.class);
        String resultCode = deliveryBean.getResultcode();
        if(resultCode.equals("200")){
            ToastUtils.showToast(context,"查询成功");
            Delivery.ResultBean resultBean = deliveryBean.getResult();
            List<Delivery.ResultBean.ListBean> list = resultBean.getList();
            result = new String[list.size()];
            for (int i=0;i<list.size();i++){
                result[i] = list.get(i).getRemark();
            }
        }else{
            ToastUtils.showToast(context,"查询失败");
        }
        return result;
    }

}
