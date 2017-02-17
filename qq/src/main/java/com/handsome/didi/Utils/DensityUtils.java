package com.handsome.didi.Utils;

import android.content.Context;

public class DensityUtils {

	public static int dp2px(float dp, Context ctx) {
		float density = ctx.getResources().getDisplayMetrics().density;
		// 4.1->4, 4.9->4
		int px = (int) (dp * density + 0.5f);// 加0.5可以四舍五入
		return px;
	}

	public static float px2dp(int px, Context ctx) {
		float density = ctx.getResources().getDisplayMetrics().density;
		float dp = px / density;
		return dp;
	}
}
