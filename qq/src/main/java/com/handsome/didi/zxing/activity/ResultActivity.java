package com.handsome.didi.zxing.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Adapter.Common.CommonShopListAdapter;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.handsome.didi.zxing.decode.DecodeThread;

import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends Activity implements AdapterView.OnItemClickListener {

    private ImageView mResultImage;
    private TextView mResultText;
    private ListView lv_zxing;
    private List<Shop> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();

        mResultImage = (ImageView) findViewById(R.id.result_image);
        mResultText = (TextView) findViewById(R.id.result_text);
        lv_zxing = (ListView) findViewById(R.id.lv_zxing);

        if (null != extras) {
            int width = extras.getInt("width");
            int height = extras.getInt("height");

            LayoutParams lps = new LayoutParams(width, height);
            lps.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
            lps.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            lps.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

            mResultImage.setLayoutParams(lps);

            String result = extras.getString("result");
            mResultText.setText(result);

            //判断该扫描是否为url
            if (URLUtil.isNetworkUrl(result)) {
                finish();
                return;
            } else {
                list = new ArrayList<Shop>();
                CommonShopListAdapter adapter = new CommonShopListAdapter(this, list);
                lv_zxing.setAdapter(adapter);
                lv_zxing.setOnItemClickListener(this);
            }

            //设置图片
            Bitmap barcode = null;
            byte[] compressedBitmap = extras.getByteArray(DecodeThread.BARCODE_BITMAP);
            if (compressedBitmap != null) {
                barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                // Mutable copy:
                barcode = barcode.copy(Bitmap.Config.RGB_565, true);
            }

            mResultImage.setImageBitmap(barcode);


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //开启商品详情
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
