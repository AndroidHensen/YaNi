package com.handsome.didi.Controller;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Find;
import com.handsome.didi.Utils.AlertUtils;

import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class FindController extends BaseController {

    public static FindController findController;

    public static FindController getInstance() {
        if (findController == null) {
            synchronized (FindController.class) {
                if (findController == null) {
                    findController = new FindController();
                }
            }
        }
        return findController;
    }

    /**
     * 查询发现
     *
     * @param listener
     */
    public void query(final int currentPage, final OnBmobListener listener) {
        BmobQuery<Find> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.order("id");
        query.setLimit(pageCount);
        query.setSkip(currentPage * pageCount);
        query.findObjects(new FindListener<Find>() {
            @Override
            public void done(List<Find> list, BmobException e) {
                if (e != null) {

                    if (e.getErrorCode() == 9016) {
                        listener.onError("无网络连接，请检查您的手机网络");
                        return;
                    }

                    listener.onError("服务器异常，正在重连");
                    //重连机制
                    new CountDownTimer(connect_time, interval_time) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            query(currentPage, listener);
                        }
                    }.start();
                    return;
                }
                if (list.isEmpty()) {
                    listener.onError("空空如也");
                    return;
                }
                if (listener != null) {
                    listener.onSuccess(list);
                }
            }
        });
    }

    public void insert(Find find) {
        find.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {

                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    /**
     * 上传文件
     *
     * @param filePaths
     */
    private void uploadFiles(final String[] filePaths) {
        BmobFile.uploadBatch(filePaths, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                if (urls.size() == filePaths.length) {
                    Log.e("TAG", files.get(0).getFileUrl());
                }
            }

            @Override
            public void onError(int statuscode, String errormsg) {

            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                //1、curIndex--表示当前第几个文件正在上传
                //2、curPercent--表示当前上传文件的进度值（百分比）
                //3、total--表示总的上传文件数
                //4、totalPercent--表示总的上传进度（百分比）
            }
        });
    }

}
