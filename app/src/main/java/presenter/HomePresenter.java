package presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;

import iview.IHomeView;
import msg.HomePageReqMsg;
import msg.HomePageResMsg;
import msg.LoginResMsg;
import msg.QuitLoginResMsg;

/**
 * Created by mac on 18/1/19.
 */

public class HomePresenter extends BaseCommPresenter<IHomeView> {

    private static final int HOME_PAGE = 0x1025;

    @Override
    public void initData(Bundle saveInstnce) {
        getHomePage();
    }

    @Override
    public void handMsg(Message msg) {

        switch (msg.what) {


            case HOME_PAGE:


                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }

    }

    public void getHomePage() {
        HomePageReqMsg req = new HomePageReqMsg();
        HomePageResMsg res = new HomePageResMsg(HOME_PAGE);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {


        if (res instanceof HomePageResMsg) {
            HomePageResMsg msg = (HomePageResMsg) res;
            if (msg.isSuc()) {

                iView.getHomeMes(msg.getData());


            }
        }


    }
}
