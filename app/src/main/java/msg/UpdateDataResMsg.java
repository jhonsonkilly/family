package msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;
import com.androidyuan.frame.base.protocal.http.RequestMsg;

import config.UrlConfig;
import model.UpdateModel;

/**
 * Created by mac on 2018/1/25.
 */

public class UpdateDataResMsg extends LiveResponseMsg<UpdateModel> {
    public UpdateDataResMsg(int what) {
        super(what);
    }
}
