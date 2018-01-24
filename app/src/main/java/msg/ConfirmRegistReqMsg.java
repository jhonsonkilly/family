package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/24.
 */

public class ConfirmRegistReqMsg extends RequestMsg {

    public ConfirmRegistReqMsg(String code, String password,String passconf,String phone) {
        super();
        params.put("code", code);
        params.put("password", password);
        params.put("passconf", passconf);
        params.put("phone", phone);


    }

    @Override
    public String getUrl() {
        return UrlConfig.registUrl();
    }
}
