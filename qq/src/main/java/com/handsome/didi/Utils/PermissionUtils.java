package com.handsome.didi.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by handsome on 2016/4/21.
 */
public class PermissionUtils {

    public static void StartPermissionWithCameraAndAudio(final Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //第一次被拒绝后，第二次访问时，向用户说明为什么需要此权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                Toast.makeText(activity, "需要开启权限使用二维码扫描", Toast.LENGTH_SHORT).show();
            }
            //若权限没有开启，则请求权限
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.CAMERA}, requestCode);
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            //第一次被拒绝后，第二次访问时，向用户说明为什么需要此权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(activity, "需要开启权限使用语音识别功能", Toast.LENGTH_SHORT).show();
            }
            //若权限没有开启，则请求权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE}, requestCode);
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //第一次被拒绝后，第二次访问时，向用户说明为什么需要此权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                Toast.makeText(activity, "需要开启权限使用语音识别功能", Toast.LENGTH_SHORT).show();
            }
            //若权限没有开启，则请求权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, requestCode);
        }
    }


    public static void StartPermissionWithLocation(final Activity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //第一次被拒绝后，第二次访问时，向用户说明为什么需要此权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(activity, "需要开启权限使用快递查询", Toast.LENGTH_SHORT).show();
            }
            //若权限没有开启，则请求权限
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);
        }
    }
}
