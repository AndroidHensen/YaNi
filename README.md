# YaNi
基于Bmob第三方后端云+MVC框架模式的商城项目-暂时定为2018年6月毕业设计项目-完成度90%
# Screenshot
首页模块部分截图展示

![](https://github.com/AndroidHensen/YaNi/blob/master/preview/version1.0-home.png)

其他模块部分截图展示

![](https://github.com/AndroidHensen/YaNi/blob/master/preview/version1.0-other.png)
# Usage
1. 注册用户->添加新收货地址、选择默认收货地址->购买商品->查看订单

# Module

* 通用模块(common)
* 首页模块(home)
* 分类模块(sort)
* 购物车模块(cart)
* 发现模块(find)
* 我的模块(mine)
 
# dependencies

 * compile 'cn.bmob.android:bmob-sdk:3.5.5'  bmob第三方后端云
 * compile project(':lib_PullToRefresh_JD_0213')  下拉刷新、上拉加载
 * compile project(':oneKeyShareNew')  一键分享Sharesdk
 * compile files('libs/Msc.jar')  语音识别，科大讯飞sdk
 * compile files('libs/zxing.jar')  二维码扫描
 * compile files('libs/umeng-analytics-v5.6.7.jar')  有盟统计sdk
 * debugCompile 'com.squareup.leakcanary:leakcanary-android:1.5'
 * releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
 * testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
 * compile 'com.android.support:cardview-v7:24.1.0'
 * compile 'com.github.bumptech.glide:glide:3.7.0'
 * compile 'com.android.support:design:24.0.0'
 * compile 'org.greenrobot:greendao:3.2.0'
# issue

首页模块

 * 快递物流还未接入接口
 * 电影票、领金券、领金豆、搜索，功能未完成
 * 语音识别后为处理
 * 优化充值、游戏充值界面
 * 优化扫一扫功能
 * 订单中心不可以出现重复的物品
 * 一键分享需要release才能使用

购物车模块

 * 结算时只能单一物品结算

我的模块

 * 订单中心不可以出现重复的物品
 * 订单中心不可以出现按店铺分组
 * 未接入Eventbus完成网络操作后更新UI
# About me
* QQ:510402535
* Blog:http://blog.csdn.net/qq_30379689
* Github:https://github.com/AndroidHensen

