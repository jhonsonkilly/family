package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/20.
 */

public class JianCaiReqMsg extends RequestMsg {

    String lat;
    String lng;

    public JianCaiReqMsg(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String getUrl() {
        return UrlConfig.JianCaiUrl("&lat=" + lat + "&lng=" + lng);
    }
}
