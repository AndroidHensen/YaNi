# YaNi
基于Bmob第三方后端云+MVC框架模式的商城项目-暂时定为2018年6月毕业设计项目-完成度90%

# APK

谢谢各位朋友，给个Star，点击下面链接进入页面，在右上角找到DownLoad按钮进行下载

雅妮商城-[安装包下载](https://github.com/AndroidHensen/YaNi/blob/master/preview/yani_release.apk)

# Screenshot
首页模块部分截图展示

![](https://github.com/AndroidHensen/YaNi/blob/master/preview/version1.0-home.png)

其他模块部分截图展示

![](https://github.com/AndroidHensen/YaNi/blob/master/preview/version1.0-other.png)
# Usage
1. 注册用户->添加新收货地址、选择默认收货地址->购买商品->查看订单

# Module

* 通用模块(common)
* 主页面模块(main)
* 首页模块(home)
* 分类模块(category)
* 购物车模块(cart)
* 发现模块(find)
* 我的模块(mine)
* 订单模块(order)
 
# dependencies

 * compile 'cn.bmob.android:bmob-sdk:3.5.5'
 * compile project(':lib_PullToRefresh_JD_0213')
 * compile project(':oneKeyShareNew')
 * compile files('libs/Msc.jar')
 * compile files('libs/zxing.jar')
 * compile files('libs/umeng-analytics-v5.6.7.jar')
 * debugCompile 'com.squareup.leakcanary:leakcanary-android:1.5'
 * releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
 * testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
 * compile 'com.github.bumptech.glide:glide:3.7.0'
 * compile 'com.android.support:design:24.0.0'
 * compile 'org.greenrobot:greendao:3.2.0'
 * compile 'org.greenrobot:eventbus:3.0.0'
 * compile 'com.github.chrisbanes:PhotoView:2.0.0'
 * compile 'com.android.support:support-v4:25.3.1'
# issue

首页模块

* 快递物流还未接入接口
* 领金豆、搜索功能未完成
* 一键分享需要release才能使用
* 优化上拉加载，更改为自动加载

购物车模块

* 结算时默认为第一个物品结算
* 增加商品数量的选择

订单模块

* 订单模块数据还未添加修改页面
* 未接入支付接口
* 订单重复只会显示一个
 
# About me
* QQ：510402535
* e-mail：xyj510402535@qq.com
* g-mail：xyj51042535@gmail.com
* Blog：http://blog.csdn.net/qq_30379689
* Github：https://github.com/AndroidHensen

