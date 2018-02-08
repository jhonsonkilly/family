package presenter;

import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import activity.WineApplication;
import com.androidyuan.frame.base.presenter.BaseCommPresenter;

import config.ParamsConfig;
import iview.IGongJiangView;
import msg.GongJiangReqMsg;
import msg.GongJiangResMsg;
import msg.GongJiangZanReqMsg;
import msg.GongJiangZanResMsg;

/**
 * Created by mac on 18/1/19.
 */

public class GongJiangItemPresenter extends BaseCommPresenter<IGongJiangView> {

    private static final int GONGJIANG = 0x1025;

    private static final int GONGJINAG_ZAN = 0x1026;


    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case GONGJIANG:
            case GONGJINAG_ZAN:

                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void getGongJiangList(String x, String y, int position, int page) {
        GongJiangReqMsg req = new GongJiangReqMsg(x, y, position, page, ParamsConfig.Page.total_number);
        GongJiangResMsg res = new GongJiangResMsg(GONGJIANG);
        sendHttpGet(req, res);
    }

    public void getGongJiangZan(String id) {
        GongJiangZanReqMsg req = new GongJiangZanReqMsg(id);
        GongJiangZanResMsg res = new GongJiangZanResMsg(GONGJINAG_ZAN);
        sendHttpGet(req, res);
    }



    public void handleResult(Object res) {


        if (res instanceof GongJiangResMsg) {
            GongJiangResMsg msg = (GongJiangResMsg) res;
            if (msg.isSuc()) {

                iView.getGongJiangList(msg.getData());

            }
        }

        if (res instanceof GongJiangZanResMsg) {
            GongJiangZanResMsg msg = (GongJiangZanResMsg) res;
            if (msg.isSuc()) {
                Toast.makeText(WineApplication.gainContext(), msg.getMsg(), Toast.LENGTH_LONG).show();

                iView.QiangDanSucess();

            }
        }


    }
}
