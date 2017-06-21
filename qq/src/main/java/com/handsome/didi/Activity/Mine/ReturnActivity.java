package com.handsome.didi.Activity.Mine;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;

public class ReturnActivity extends BaseActivity {

    private TextView tv_error, tv_suggest, tv_express;
    private EditText et_content;

    @Override
    public int getLayoutId() {
        return R.layout.activity_return;
    }

    @Override
    public void initViews() {
        tv_error = findView(R.id.tv_error);
        tv_express = findView(R.id.tv_express);
        tv_suggest = findView(R.id.tv_suggest);
        et_content = findView(R.id.et_content);
    }

    @Override
    public void initListener() {
        setOnClick(tv_error);
        setOnClick(tv_express);
        setOnClick(tv_suggest);
    }

    @Override
    public void initData() {
        setTitle("回馈帮助");
        setTitleCanBack();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_error:
                setTagColor(v.getId());
                et_content.append("#功能异常#");
                break;
            case R.id.tv_express:
                setTagColor(v.getId());
                et_content.append("#体验问题#");
                break;
            case R.id.tv_suggest:
                setTagColor(v.getId());
                et_content.append("#新功能建议#");
                break;
        }
    }

    /**
     * 设置标签颜色
     *
     * @param tvId
     */
    private void setTagColor(int tvId) {
        tv_error.setBackgroundResource(tvId == R.id.tv_error ? R.drawable.common_bg_orange_22x8 : R.drawable.common_bg_gray_border_22x8);
        tv_error.setTextColor(tvId == R.id.tv_error ? Color.WHITE : Color.parseColor("#888888"));
        tv_express.setBackgroundResource(tvId == R.id.tv_express ? R.drawable.common_bg_orange_22x8 : R.drawable.common_bg_gray_border_22x8);
        tv_express.setTextColor(tvId == R.id.tv_express ? Color.WHITE : Color.parseColor("#888888"));
        tv_suggest.setBackgroundResource(tvId == R.id.tv_suggest ? R.drawable.common_bg_orange_22x8 : R.drawable.common_bg_gray_border_22x8);
        tv_suggest.setTextColor(tvId == R.id.tv_suggest ? Color.WHITE : Color.parseColor("#888888"));
    }
}
