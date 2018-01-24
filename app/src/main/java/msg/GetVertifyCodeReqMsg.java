package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/24.
 */

public class GetVertifyCodeReqMsg extends RequestMsg {

    String phone;

    public GetVertifyCodeReqMsg(String phone) {
        this.phone = phone;
    }

    @Override
    public String getUrl() {
        return UrlConfig.sendSmsUrl(phone);
    }
}
