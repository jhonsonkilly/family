package fragment;

import android.content.Intent;
import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import Event.LoginEvent;
import Event.LoginSucessEvent;
import Event.QuitLoginEvent;
import Event.RegistSucessEvent;
import activity.RegistActivity;
import config.LoginHelper;
import family.live.R;
import iview.ILoginView;
import model.UpdateModel;
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
        if (View.VISIBLE ==mineView.getVisibility() ) {

            presenter.updateData();
        }


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

    @Subscribe
    public void RegistSucess(RegistSucessEvent event) {

        getLoginState();
    }


    @Override
    public void showLogin(UserModel model) {

        LoginHelper.setLoginMes(model);

        OttoBus.getInstance().post(new LoginSucessEvent());

    }

    @Override
    public void quitLogin() {

        LoginHelper.cancelLoginMes();
        getLoginState();
        loginView.clearMes();


    }

    @Override
    public void updateData(UpdateModel model) {

        LoginHelper.updateMes(model);
        OttoBus.getInstance().post(new LoginSucessEvent());
    }

    public void getLoginState() {

        if (LoginHelper.isLogin()) {

            loginView.setVisibility(View.GONE);
            mineView.setVisibility(View.VISIBLE);

        } else {
            loginView.setVisibility(View.VISIBLE);
            mineView.setVisibility(View.GONE);
        }

        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
        while (namesRepository.getIterator().hasNext()){
            namesRepository.getIterator().next();
        }
    }


    public interface Iterator {
        public boolean hasNext();
        public Object next();
    }


}
