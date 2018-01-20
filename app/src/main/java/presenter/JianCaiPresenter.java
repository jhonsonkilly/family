package presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;

import iview.IJianCaiView;
import msg.HomePageResMsg;
import msg.JianCaiReqMsg;
import msg.JianCaiResMsg;

/**
 * Created by mac on 18/1/19.
 */

public class JianCaiPresenter extends BaseCommPresenter<IJianCaiView> {

    private static final int SHOP_LIST = 0x1025;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case SHOP_LIST:


                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void getShopList(String lat, String lng) {

        JianCaiReqMsg reqMsg = new JianCaiReqMsg(lat, lng);
        JianCaiResMsg resMsg = new JianCaiResMsg(SHOP_LIST);
        sendHttpGet(reqMsg, resMsg);
    }

    public void handleResult(Object res) {


        if (res instanceof JianCaiResMsg) {
            JianCaiResMsg msg = (JianCaiResMsg) res;
            if (msg.isSuc()) {

                iView.getShopList(msg.getData());


            }
        }


    }
}
