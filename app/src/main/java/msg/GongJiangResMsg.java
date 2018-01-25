package msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;

import model.GongJiangModel;
import model.QiangDanModel;

/**
 * Created by mac on 18/1/23.
 */

public class GongJiangResMsg extends LiveResponseMsg<GongJiangModel> {


    public GongJiangResMsg(int what) {
        super(what);
    }
}
