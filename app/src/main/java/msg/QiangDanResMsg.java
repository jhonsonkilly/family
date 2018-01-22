package msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;

import model.QiangDanModel;

/**
 * Created by mac on 18/1/23.
 */

public class QiangDanResMsg extends LiveResponseMsg<QiangDanModel> {


    public QiangDanResMsg(int what) {
        super(what);
    }
}
