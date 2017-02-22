package com.handsome.didi.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Adapter.Cart.CartAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import java.util.List;

public class LoveActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    //中
    private LinearLayout ly_love_bg;
    private ListView lv_love;
    private List<Shop> loveList;
    private CartAdapter adapter;
    //头
    private TextView tv_header_edit;
    private boolean isEdit;
    //底
    private TextView tv_delete;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_love);
        ly_love_bg = (LinearLayout) findViewById(R.id.ly_love_bg);
        lv_love = (ListView) findViewById(R.id.lv_love);
        tv_header_edit = (TextView) findViewById(R.id.tv_header_edit);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
    }

    @Override
    public void initListener() {
        tv_header_edit.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        lv_love.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        //初始化关注数据
        initLoveData();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_header_edit:
                //编辑
                if (isEdit) {
                    closeEditMenu();
                } else {
                    showEditMenu();
                }
                break;
            case R.id.tv_delete:
                //删除
                deleteLove();
                break;
        }
    }

    /**
     * 初始化关注数据
     */
    private void initLoveData() {
//        loveList = ShopDao.queryLove(getContentResolver());
        if (loveList.size() > 0) {
            ly_love_bg.setVisibility(View.GONE);
            lv_love.setVisibility(View.VISIBLE);
            tv_header_edit.setVisibility(View.VISIBLE);
            adapter = new CartAdapter(this, loveList);
            lv_love.setAdapter(adapter);
        } else {
            ly_love_bg.setVisibility(View.VISIBLE);
            lv_love.setVisibility(View.GONE);
            tv_header_edit.setVisibility(View.GONE);
        }
    }

    /**
     * 删除购物车数据
     */
    private void deleteLove() {
        List<Integer> list = adapter.getSelected_Id();
        if (list.size() > 0) {
            for (Integer _id : list) {
//                ShopDao.deleteLove(this.getContentResolver(), _id);
            }
            //初始化购物车数据
            initLoveData();
            //关闭菜单
            closeEditMenu();
        } else {
            ToastUtils.showToast(this, "请选择商品");
        }
    }

    /**
     * 关闭编辑菜单
     */
    private void closeEditMenu() {
        //关闭结算板
        tv_header_edit.setText("编辑");
        tv_delete.setVisibility(View.GONE);
        //关闭编辑模式
        adapter.closeEditMode();
        isEdit = false;
    }


    /**
     * 打开编辑菜单
     */
    private void showEditMenu() {
        //显示结算板
        tv_header_edit.setText("取消");
        tv_delete.setVisibility(View.VISIBLE);
        //打开编辑模式
        adapter.openEditMode();
        isEdit = true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isEdit) {
            //选择条目
//            adapter.selectSingle(position);
        } else {
            //进入详情页
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        }
    }
}
