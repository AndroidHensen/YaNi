package com.handsome.didi.Activity.Mine;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.AlertUtils;

public class UserDetailActivity extends BaseActivity {

    private UserController userController;

    private TextView tv_sex, tv_age;
    private Button bt_sure;

    private Boolean sex;
    private int age;
    private String[] sexs = {"男", "女"};
    private String[] ages = {"14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};

    private User currentUser;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tv_sex.setText(sex ? "男" : "女");
                    break;
                case 1:
                    tv_age.setText(age + "");
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initViews() {
        tv_sex = findView(R.id.tv_sex);
        tv_age = findView(R.id.tv_age);
        bt_sure = findView(R.id.bt_sure);
    }

    @Override
    public void initListener() {
        setOnClick(tv_sex);
        setOnClick(tv_age);
        setOnClick(bt_sure);
    }

    @Override
    public void initData() {
        setTitle("完善资料");
        setTitleCanBack();

        userController = UserController.getInstance();

        initUserDetailViews();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_age:
                chooseAge();
                break;
            case R.id.tv_sex:
                chooseSex();
                break;
            case R.id.bt_sure:
                updateUser();
                break;
        }
    }

    /**
     * 初始化用户详情页面
     */
    private void initUserDetailViews() {
        currentUser = userController.getCurrentUser();
        sex = currentUser.sex;
        age = currentUser.age;
        tv_age.setText(age + "");
        tv_sex.setText(sex ? "男" : "女");
    }

    /**
     * 更新用户信息
     */
    private void updateUser() {
        currentUser.sex = sex;
        currentUser.age = age;
        userController.update(currentUser, new BaseController.OnBmobCommonListener() {
            @Override
            public void onSuccess(String success) {
                onChangeDataInUI(UserActivity.class.getName());
                showToast(success);
                finish();
            }

            @Override
            public void onError(String error) {
                onChangeDataInUI(UserActivity.class.getName());
                showToast(error);
                finish();
            }
        });
    }

    /**
     * 选择性别
     */
    private void chooseSex() {
        AlertUtils.showAlert(this, sexs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sex = sexs[which].equals("男");
                dialog.dismiss();
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    /**
     * 选择年龄
     */
    private void chooseAge() {
        AlertUtils.showAlert(this, ages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                age = Integer.parseInt(ages[which]);
                dialog.dismiss();
                mHandler.sendEmptyMessage(1);
            }
        });
    }


}
