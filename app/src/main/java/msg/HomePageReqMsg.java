package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/20.
 */

public class HomePageReqMsg extends RequestMsg {
    @Override
    public String getUrl() {
        return UrlConfig.homePageUrl();
    }
}
