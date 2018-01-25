package config;

import android.text.TextUtils;
import android.widget.Toast;

import com.androidyuan.frame.base.activity.WineApplication;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;

import model.UpdateModel;
import model.UserModel;


/**
 * Created by mac on 18/1/13.
 */

public class LoginHelper {

    public static boolean isLogin() {
        if (TextUtils.isEmpty(SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "token", ""))) {
            return false;
        }
        return true;
    }

    //验证参数
    public static String getClient() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "client", "");
    }

    //验证参数
    public static String token() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "token", "");
    }


    public static void setLoginMes(UserModel mes) {

        if (mes != null) {
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "client", mes.client_id);
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "token", mes.access_token);
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "isCraftsman", mes.is_craftsman);
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "phone", mes.phone);
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "cover", mes.cover);
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "score", mes.score);

        } else {

            Toast.makeText(WineApplication.gainContext(), "token存入失败", Toast.LENGTH_LONG).show();
        }


    }

    public static void updateMes(UpdateModel model){
        if(model!=null){
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "cover", model.cover);
            SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "score", model.score);
        }

    }

    public static void cancelLoginMes() {

        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "client", "");

        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "token", "");
        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "isCraftsman", "");
        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "phone", "");
        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "cover", "");
        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "score", "");
    }
}
