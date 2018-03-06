package activity;

import android.content.Intent;
import android.view.View;

import com.androidyuan.frame.base.activity.BaseCommActivity;

import Event.LoginEvent;
import Event.LoginSucessEvent;
import config.LoginHelper;
import family.live.R;
import iview.ILoginView;
import model.UpdateModel;
import model.UserModel;
import otto.OttoBus;
import otto.Subscribe;
import presenter.MinePresenter;
import widget.LoginView;

/**
 * Created by mac on 18/1/20.
 */

public class LoginActivity extends BaseCommActivity<MinePresenter> implements ILoginView {

    private LoginView loginView;

    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    protected void initAllWidget() {
        loginView = (LoginView)findViewById(R.id.login);
        loginView.showBack();
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

    @Subscribe
    public void loginMes(LoginEvent event) {
        presenter.getLoginMes(event.phone, event.password);
    }

    @Subscribe
    public void loginSucess(LoginSucessEvent event) {

        finish();
    }



    @Override
    public void showLogin(UserModel model) {
        //先后顺序
        LoginHelper.setLoginMes(model);

        OttoBus.getInstance().post(new LoginSucessEvent());


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    @Override
    public void quitLogin() {

    }

    @Override
    public void updateData(UpdateModel model) {

    }
}
