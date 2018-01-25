package fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import adapter.GongJiangFragmentAdapter;
import adapter.QiangDanFragmentAdapter;
import family.live.R;
import presenter.GongJiangPresenter;
import widget.ToolBar;

/**
 * Created by mac on 18/1/19.
 */

public class GongJiangFragment extends BaseCommFragment<GongJiangPresenter> {

    private TextView title1;
    private TextView title2;
    private TextView title3;
    private ViewPager viewPager;
    private GongJiangFragmentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_gongjiang;
    }

    @Override
    protected void initAllWidget(View view) {

        ToolBar toolbar=view.findViewById(R.id.tool_bar);
        toolbar.setTitle("抢单中心");
        title1 = view.findViewById(R.id.title_1);
        title1.setOnClickListener(this);
        title2 = view.findViewById(R.id.title_2);
        title2.setOnClickListener(this);
        title3 = view.findViewById(R.id.title_3);
        title3.setOnClickListener(this);

        viewPager = view.findViewById(R.id.view_page);
        viewPager.setOffscreenPageLimit(4);
        adapter = new GongJiangFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        setSelector(true, false, false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setSelector(true, false, false);
                } else if (position == 1) {
                    setSelector(false, true, false);
                }else{
                    setSelector(false, false, true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.title_1:
                viewPager.setCurrentItem(0,false);
                setSelector(true, false, false);
                break;
            case R.id.title_2:
                viewPager.setCurrentItem(1,false);
                setSelector(false, true, false);
                break;
            case R.id.title_3:
                viewPager.setCurrentItem(2,false);
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
