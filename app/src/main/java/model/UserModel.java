package model;

import activity.WineApplication;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;

/**
 * Created by mac on 18/1/13.
 */

public class UserModel {


    public String access_token;
    public String client_id;
    public String name;
    public String phone;
    public String cover;
    public String score;
    public String uid;
    public String is_craftsman;

    public static boolean isCraftsman() {
        if (getIs_craftsman().equals("1")) {
            return true;
        }
        return false;
    }

    public static String getIs_craftsman() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "isCraftsman", "");

    }

    public void setIs_craftsman(String is_craftsman) {
        this.is_craftsman = is_craftsman;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getPhone() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "phone", "");
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static String getCover() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "cover", "");
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public static String getScore() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "score", "");
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static String getUid() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "uid", "");
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
