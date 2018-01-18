package fragment;

import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import family.live.R;
import presenter.HomePresenter;

/**
 * Created by mac on 18/1/19.
 */

public class HomeFragment extends BaseCommFragment<HomePresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initAllWidget(View view) {

    }

    @Override
    protected void clickView(View v) {

    }
}
