package presenter;

import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;

import iview.ILoginView;
import msg.LoginReqMsg;
import msg.LoginResMsg;
import msg.QuitLoginReqMsg;
import msg.QuitLoginResMsg;
import msg.UpdateDataReqMsg;
import msg.UpdateDataResMsg;

/**
 * Created by mac on 18/1/19.
 */

public class MinePresenter extends BaseCommPresenter<ILoginView> {

    private static final int RES_LOGIN_MES = 0x1024;
    private static final int QUIT_LOGIN_MES = 0x1025;
    private static final int UPDATE_DATA_MSG = 0x1026;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case RES_LOGIN_MES:
            case QUIT_LOGIN_MES:
            case UPDATE_DATA_MSG:

                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void getLoginMes(String phone, String password) {
        LoginReqMsg req = new LoginReqMsg(phone, password);
        LoginResMsg res = new LoginResMsg(RES_LOGIN_MES);
        sendHttpPostJson(req, res);
    }

    public void quitLoginMes() {
        QuitLoginReqMsg req = new QuitLoginReqMsg();
        QuitLoginResMsg res = new QuitLoginResMsg(QUIT_LOGIN_MES);
        sendHttpGet(req, res);
    }

    public void updateData() {
        UpdateDataReqMsg req = new UpdateDataReqMsg();
        UpdateDataResMsg res = new UpdateDataResMsg(UPDATE_DATA_MSG);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {


        if (res instanceof LoginResMsg) {
            LoginResMsg msg = (LoginResMsg) res;
            if (msg.isSuc()) {

                iView.showLogin(msg.getData());

            }
        }

        if (res instanceof QuitLoginResMsg) {
            QuitLoginResMsg msg = (QuitLoginResMsg) res;
            if (msg.isSuc()) {

                //iView.showLogin(msg.getData());
                iView.quitLogin();

            }
        }

        if (res instanceof UpdateDataResMsg) {
            UpdateDataResMsg msg = (UpdateDataResMsg) res;
            if (msg.isSuc()) {

                //iView.showLogin(msg.getData());
                // iView.quitLogin();
                iView.updateData(msg.getData());

            }
        }
    }
}
