package com.sukelin.doctoronlineapp.maxdemo;

import android.app.Application;
import android.os.Environment;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.aliyun.vodplayer.downloader.AliyunDownloadConfig;
import com.aliyun.vodplayer.downloader.AliyunDownloadManager;
import com.squareup.leakcanary.LeakCanary;

/**
 * @Author: lifujun@alibaba-inc.com
 * @Date: 2016/12/29.
 * @Description:
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        VcPlayerLog.enableLog();
        initLeakcanary();//初始化内存检测


        //初始化播放器
        final String businessId = "";
        AliVcMediaPlayer.init(getApplicationContext(), businessId);

        //设置保存密码。此密码如果更换，则之前保存的视频无法播放
        AliyunDownloadConfig config = new AliyunDownloadConfig();
        config.setSecretImagePath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/aliyun/encryptedApp.dat");
//        config.setDownloadPassword("123456789");
        //设置保存路径。请确保有SD卡访问权限。
        config.setDownloadDir(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test_save/");
        //设置同时下载个数
        config.setMaxNums(2);

        AliyunDownloadManager.getInstance(this).setDownloadConfig(config);

    }


    private void initLeakcanary() {
        LeakCanary.install(this);
    }
}
