package msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 2018/1/25.
 */

public class UpdateDataReqMsg extends RequestMsg {
    @Override
    public String getUrl() {
        return UrlConfig.updateDataUrl();
    }
}
