package activity;

import android.view.View;

import com.androidyuan.frame.base.activity.BaseCommActivity;

import Event.RegistSucessEvent;
import family.live.R;
import iview.IRegistView;
import otto.OttoBus;
import presenter.ReigstPresenter;
import widget.RegistView;

/**
 * Created by mac on 2018/1/24.
 */

public class RegistActivity extends BaseCommActivity<ReigstPresenter> implements IRegistView {

    private RegistView registView;

    @Override
    protected int getLayoutId() {
        return R.layout.act_regist;
    }

    @Override
    protected void initAllWidget() {
        registView = (RegistView)findViewById(R.id.regist);
        OttoBus.getInstance().register(this);
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    @Override
    public void getVertifyCodeRes(String msg) {
        showToast(msg);
        registView.startCountDown();
    }

    @Override
    public void confirmCode(String msg) {
        OttoBus.getInstance().post(new RegistSucessEvent());
        showToast(msg);
        finish();
    }
}
