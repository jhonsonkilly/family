package activity;

import android.app.Application;
import android.content.Context;

import com.androidyuan.frame.base.activity.BaseApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by mac on 2017/10/16.
 */

public class WineApplication extends BaseApplication {



    @Override
    public void onCreate() {
        super.onCreate();



        PlatformConfig.setWeixin("wx32f09c7123498174 ","a8d839a1e43fa3178a41f6248669758e");

        UMShareAPI.get(this);


    }


}
