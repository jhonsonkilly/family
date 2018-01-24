package msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;
import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;

/**
 * Created by mac on 18/1/24.
 */

public class GetVertifyCodeResMsg extends LiveResponseMsg {


    public GetVertifyCodeResMsg(int what) {
        super(what);
    }
}
