# YaNi
基于Bmob第三方后端云+MVC框架模式的商城项目-暂时定为2018年6月毕业设计项目-完成度95%

# APK
谢谢各位朋友，给个Star，体验APK请点击链接进入[安装包下载](/qq/qq-release.apk)

# Screenshot
<img src="/preview/preview1.png" height="400px"></img>
<img src="/preview/preview2.png" height="400px"></img>
<img src="/preview/preview3.png" height="400px"></img>
<img src="/preview/preview4.png" height="400px"></img>
<img src="/preview/preview5.png" height="400px"></img>
<img src="/preview/preview6.png" height="400px"></img>
<img src="/preview/preview7.png" height="400px"></img>
<img src="/preview/preview8.png" height="400px"></img>
<img src="/preview/preview9.png" height="400px"></img>

# Usage
1. 注册用户->添加新收货地址、选择默认收货地址->购买商品->查看订单
2. 登陆测试账号->使用APK

测试账号|密码
------------ | -------------
俊俊俊|123
俊俊俊1|123
俊俊俊2|123

# Module

模块名字|对应的模块
------------ | -------------
通用模块|common
主页面模块|main
首页模块|home
分类模块|category
购物车模块|cart
发现模块|find
我的模块|mine
订单模块|order
 
# dependencies

* compile 'com.android.support:appcompat-v7:25.3.1'
* compile 'com.android.support:design:25.3.1'
* compile 'com.android.support:recyclerview-v7:23.4.0'
* compile project(':lib_PullToRefresh_JD_0213')
* compile project(':oneKeyShareNew')
* compile files('libs/Msc.jar')
* compile files('libs/zxing.jar')
* compile files('libs/umeng-analytics-v5.6.7.jar')
* compile 'cn.bmob.android:bmob-sdk:3.5.5'
* debugCompile 'com.squareup.leakcanary:leakcanary-android:1.5'
* releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
* testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
* compile 'com.github.bumptech.glide:glide:3.7.0'
* compile 'org.greenrobot:greendao:3.2.0'
* compile 'org.greenrobot:eventbus:3.0.0'
* compile 'com.github.chrisbanes:PhotoView:2.0.0'
* compile 'com.android.support:multidex:1.0.1'
* compile 'me.iwf.photopicker:PhotoPicker:0.9.5@aar'
* compile 'com.github.AndroidHensen:FastMenuBar:1.0.3'
* compile 'com.github.AndroidHensen:FastMenuBlock:1.0.1'
* compile 'com.github.AndroidHensen:FastBanner:1.0.4'

# issue

首页模块

* 快递物流查询接入聚合Api接口
* 领金豆模块通过签到的形式送出
* 搜索功能采用流布局保存历史记录，付费开启搜索功能
* 上拉加载更改为自动加载

购物车模块

* 增加商品数量的选择

订单模块

* 订单发票等数据通过底部弹窗进行选择
* 接入支付接口
* 评价成功后更新订单状态

# About me
* QQ：510402535
* e-mail：xyj510402535@qq.com
* g-mail：xyj51042535@gmail.com
* Blog：http://blog.csdn.net/qq_30379689
* Github：https://github.com/AndroidHensen

