package com.handsome.didi.Activity.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.handsome.didi.Adapter.Home.CardAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Card;
import com.handsome.didi.Controller.CardController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends BaseActivity {

    private ListView lv_card;
    private CardAdapter cardAdapter;
    private List<Card> cardList;

    private CardController cardController;

    @Override
    public int getLayoutId() {
        return R.layout.activity_card;
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
        setTitle("领券中心");
        setTitleCanBack();

        cardController = CardController.getInstance();

        initCardViews();
    }

    @Override
    public void processClick(View v) {

    }


    /**
     * 初始化卡券界面
     */
    private void initCardViews() {
        cardController.query(new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                cardList = (List<Card>) list;
                cardAdapter = new CardAdapter(CardActivity.this, cardList);
                lv_card.setAdapter(cardAdapter);
            }

            @Override
            public void onError(String error) {

            }
        });
    }


}
