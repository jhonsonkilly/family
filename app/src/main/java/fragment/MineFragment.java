package fragment;

import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import Event.LoginEvent;
import Event.LoginSucessEvent;
import Event.QuitLoginEvent;
import config.LoginHelper;
import family.live.R;
import iview.ILoginView;
import model.UserModel;
import otto.OttoBus;
import otto.Subscribe;
import presenter.MinePresenter;
import widget.LoginView;
import widget.MineView;

/**
 * Created by mac on 18/1/19.
 */

public class MineFragment extends BaseCommFragment<MinePresenter> implements ILoginView {

    private LoginView loginView;
    private MineView mineView;

    @Override
    protected int getLayoutId() {

        return R.layout.frg_mine;
    }

    @Override
    protected void initAllWidget(View view) {


        loginView = view.findViewById(R.id.login_view);
        mineView = view.findViewById(R.id.mine_view);
        getLoginState();

        OttoBus.getInstance().register(this);
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void onDetach() {
        super.onDetach();

        OttoBus.getInstance().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoginState();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            getLoginState();
        }
    }

    @Subscribe
    public void loginMes(LoginEvent event) {
        presenter.getLoginMes(event.phone, event.password);
    }

    @Subscribe
    public void quitLoginMes(QuitLoginEvent event) {
        presenter.quitLoginMes();
    }

    @Subscribe
    public void loginSucess(LoginSucessEvent event) {


        getLoginState();
    }


    @Override
    public void showLogin(UserModel model) {

        LoginHelper.setLoginMes(model);

        OttoBus.getInstance().post(new LoginSucessEvent(model));

    }

    @Override
    public void quitLogin() {

        LoginHelper.cancelLoginMes();
        getLoginState();
        loginView.clearMes();


    }

    public void getLoginState() {

        if (LoginHelper.isLogin()) {

            loginView.setVisibility(View.GONE);
            mineView.setVisibility(View.VISIBLE);
        } else {
            loginView.setVisibility(View.VISIBLE);
            mineView.setVisibility(View.GONE);
        }
    }
}
