package presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;

import iview.IRegistView;
import msg.ConfirmRegistReqMsg;
import msg.ConfirmRegistResMsg;
import msg.GetPassWordVertifyCodeReqMsg;
import msg.GetPassWordVertifyCodeResMsg;
import msg.GetPasswordReqMsg;
import msg.GetPasswordResMsg;
import msg.GetVertifyCodeReqMsg;
import msg.GetVertifyCodeResMsg;

/**
 * Created by mac on 2018/1/24.
 */

public class ForgetPassWordPresenter extends BaseCommPresenter<IRegistView> {

    private static final int VERTIFY_CODE = 0x1026;
    private static final int CONFIRM_CODE = 0x1027;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case VERTIFY_CODE:
            case CONFIRM_CODE:

                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }

    }

    public void getVertifyCode(String phone) {

        GetPassWordVertifyCodeReqMsg reqMsg = new GetPassWordVertifyCodeReqMsg(phone);
        GetPassWordVertifyCodeResMsg resMsg = new GetPassWordVertifyCodeResMsg(VERTIFY_CODE);
        sendHttpGet(reqMsg, resMsg);

    }

    public void confirmPassword(String code, String password, String phone) {

        GetPasswordReqMsg reqMsg = new GetPasswordReqMsg(code, password, phone);
        GetPasswordResMsg resMsg = new GetPasswordResMsg(CONFIRM_CODE);
        sendHttpPostJson(reqMsg, resMsg);

    }

    public void handleResult(Object res) {
        if (res instanceof GetPassWordVertifyCodeResMsg) {
            GetPassWordVertifyCodeResMsg msg = (GetPassWordVertifyCodeResMsg) res;
            if (msg.isSuc()) {

                iView.getVertifyCodeRes(msg.getMsg());

            }
        }
        if (res instanceof GetPasswordResMsg) {
            GetPasswordResMsg msg = (GetPasswordResMsg) res;
            if (msg.isSuc()) {

                iView.confirmCode(msg.getMsg());

            }
        }
    }
}
