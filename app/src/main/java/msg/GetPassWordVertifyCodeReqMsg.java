package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/24.
 */

public class GetPassWordVertifyCodeReqMsg extends RequestMsg {

    String phone;

    public GetPassWordVertifyCodeReqMsg(String phone) {
        this.phone = phone;
    }

    @Override
    public String getUrl() {
        return UrlConfig.sendGetPasswordSmsUrl(phone);
    }
}
