package activity;

import android.view.View;

import com.androidyuan.frame.base.activity.BaseCommActivity;

import family.live.R;
import iview.IRegistView;
import presenter.ForgetPassWordPresenter;
import widget.ForgetPassWordView;

/**
 * Created by mac on 18/1/24.
 */

public class ForgetPassWordActivity extends BaseCommActivity<ForgetPassWordPresenter> implements IRegistView {

    ForgetPassWordView view;

    @Override
    protected int getLayoutId() {
        return R.layout.act_forgetpassword;
    }

    @Override
    protected void initAllWidget() {
        view = (ForgetPassWordView)findViewById(R.id.view);
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
    public void getVertifyCodeRes(String msg) {
        showToast(msg);
        view.startCountDown();
    }

    @Override
    public void confirmCode(String msg) {
        showToast(msg);
        finish();
    }
}
