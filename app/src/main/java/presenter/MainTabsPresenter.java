package presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;


/**
 * Created by mac on 2017/10/16.
 */

public class MainTabsPresenter extends BaseCommPresenter {

    private static final int RES_PERSONAL_MES = 0x1025;
    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case RES_PERSONAL_MES:
                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }



    public void handleResult(Object res) {







    }
}
