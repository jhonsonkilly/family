package presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.androidyuan.frame.base.protocal.http.HttpTool;

import config.ParamsConfig;
import iview.IQiangDanView;
import msg.LoginResMsg;
import msg.QiangDanReqMsg;
import msg.QiangDanResMsg;
import msg.QuitLoginResMsg;

/**
 * Created by mac on 18/1/22.
 */

public class QiangDanItemPresenter extends BaseCommPresenter<IQiangDanView> {


    private static final int QIANG_DAN = 0x1025;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case QIANG_DAN:

                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void getQiangDanList(String x, String y, int position, int page) {
        QiangDanReqMsg req = new QiangDanReqMsg(x, y, position, page, ParamsConfig.Page.total_number);
        QiangDanResMsg res = new QiangDanResMsg(QIANG_DAN);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {


        if (res instanceof QiangDanResMsg) {
            QiangDanResMsg msg = (QiangDanResMsg) res;
            if (msg.isSuc()) {

                iView.getQiangDanList(msg.getData());

            }
        }


    }
}
