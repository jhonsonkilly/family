package presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;

import iview.IRegistView;
import msg.ConfirmRegistReqMsg;
import msg.ConfirmRegistResMsg;
import msg.GetVertifyCodeReqMsg;
import msg.GetVertifyCodeResMsg;
import msg.QiangDanResMsg;

/**
 * Created by mac on 2018/1/24.
 */

public class ReigstPresenter extends BaseCommPresenter<IRegistView> {

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

        GetVertifyCodeReqMsg reqMsg = new GetVertifyCodeReqMsg(phone);
        GetVertifyCodeResMsg resMsg = new GetVertifyCodeResMsg(VERTIFY_CODE);
        sendHttpGet(reqMsg, resMsg);

    }

    public void confirmRegist(String code,String password,String conPassword,String phone) {

        ConfirmRegistReqMsg reqMsg = new ConfirmRegistReqMsg(code,password,conPassword,phone);
        ConfirmRegistResMsg resMsg = new ConfirmRegistResMsg(CONFIRM_CODE);
        sendHttpPostJson(reqMsg, resMsg);

    }

    public void handleResult(Object res) {
        if (res instanceof GetVertifyCodeResMsg) {
            GetVertifyCodeResMsg msg = (GetVertifyCodeResMsg) res;
            if (msg.isSuc()) {

                iView.getVertifyCodeRes(msg.getMsg());

            }
        }
        if (res instanceof ConfirmRegistResMsg) {
            ConfirmRegistResMsg msg = (ConfirmRegistResMsg) res;
            if (msg.isSuc()) {

                iView.confirmCode(msg.getMsg());

            }
        }
    }
}
