package fragment;

import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import config.LoginHelper;
import family.live.R;
import otto.OttoBus;
import presenter.MinePresenter;
import widget.LoginView;

/**
 * Created by mac on 18/1/19.
 */

public class MineFragment extends BaseCommFragment<MinePresenter> {

    private LoginView loginView;

    @Override
    protected int getLayoutId() {

        return R.layout.frg_mine;
    }

    @Override
    protected void initAllWidget(View view) {


        loginView = view.findViewById(R.id.login_view);

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
       /* if (LoginHelper.isLogin()) {
            loginView.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }


}
