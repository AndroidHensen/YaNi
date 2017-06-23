package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.handsome.didi.Adapter.Mine.MyCardAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Card;
import com.handsome.didi.Controller.CardController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

public class MyCardActivity extends BaseActivity {

    private CardController cardController;
    private UserController userController;

    private ListView lv_card;
    private MyCardAdapter adapter;
    private List<Card> cardList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_card;
    }

    @Override
    public void initViews() {
        lv_card = findView(R.id.lv_card);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("我的卡券");
        setTitleCanBack();

        cardController = CardController.getInstance();
        userController = UserController.getInstance();

        //初始化我的卡券页面
        initMyCardViews();
    }


    @Override
    public void processClick(View v) {

    }


    /**
     * 初始化我的卡券页面
     */
    private void initMyCardViews() {
        List<String> cardOid = userController.getCardOid();
        cardController.query(cardOid, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                cardList = (List<Card>) list;
                adapter = new MyCardAdapter(MyCardActivity.this,cardList);
                lv_card.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }
}
