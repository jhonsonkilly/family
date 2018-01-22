package fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import adapter.QiangDanFragmentAdapter;
import family.live.R;
import presenter.QiangDanPresenter;

/**
 * Created by mac on 18/1/19.
 */

public class QiangDanFragment extends BaseCommFragment<QiangDanPresenter> {

    private TextView title1;
    private TextView title2;
    private TextView title3;
    private ViewPager viewPager;
    private QiangDanFragmentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_qiangdan;
    }

    @Override
    protected void initAllWidget(View view) {
        title1 = view.findViewById(R.id.title_1);
        title1.setOnClickListener(this);
        title2 = view.findViewById(R.id.title_2);
        title2.setOnClickListener(this);
        title3 = view.findViewById(R.id.title_3);
        title3.setOnClickListener(this);

        viewPager = view.findViewById(R.id.view_page);
        adapter = new QiangDanFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.title_1:
                setSelector(true, false, false);
                break;
            case R.id.title_2:
                setSelector(false, true, false);
                break;
            case R.id.title_3:
                setSelector(false, false, true);
                break;
        }
    }

    public void setSelector(boolean color1, boolean color2, boolean color3) {

        title1.setSelected(color1);
        title2.setSelected(color2);
        title3.setSelected(color3);
    }
}
