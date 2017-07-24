package com.handsome.didi.Controller;

import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Find;

import java.util.ArrayList;
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
        query.order("-id");
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

    /**
     * 添加评价到发现栏目
     *
     * @param filePaths
     */
    public void insert(final Find find, final List<String> filePaths, final onBmobInsertListener listener) {

        listener.onLoading(0);

        String [] files = new String[filePaths.size()];
        for (int i = 0;i<filePaths.size();i++){
            files[i] = filePaths.get(i);
        }

        //上传文件
        BmobFile.uploadBatch(files, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> bmobFiles, List<String> urls) {
                if (urls.size() == filePaths.size()) {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < bmobFiles.size(); i++) {
                        list.add(bmobFiles.get(i).getFileUrl());
                    }
                    //添加栏目
                    find.user_pic_url = list;
                    find.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                listener.onSuccess("评价成功");
                            } else {
                                listener.onError("服务器异常，评价失败");
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                listener.onError("服务器异常，评价失败");
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                if (totalPercent <= 95) {
                    listener.onLoading(totalPercent);
                }else{
                    listener.onLoading(98);
                }
            }
        });
    }

}
