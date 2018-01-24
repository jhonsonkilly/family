package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/24.
 */

public class GetPasswordReqMsg extends RequestMsg {

    public GetPasswordReqMsg(String code, String password, String phone) {
        super();
        params.put("code", code);
        params.put("password", password);
        params.put("phone", phone);


    }

    @Override
    public String getUrl() {
        return UrlConfig.getPasswordUrl();
    }
}
