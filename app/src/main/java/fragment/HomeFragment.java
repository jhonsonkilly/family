package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.banner.BannerBaseAdapter;
import com.androidyuan.frame.cores.banner.BannerView;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Event.LocationEvent;
import activity.LoginActivity;
import activity.WebActivity;
import adapter.HorListAdapter;
import config.LoginHelper;
import config.ParamsConfig;
import family.live.R;
import iview.IHomeView;
import model.HomePageModel;
import otto.OttoBus;
import otto.Subscribe;
import presenter.HomePresenter;
import widget.ToolBar;

/**
 * Created by mac on 18/1/19.
 */

public class HomeFragment extends BaseCommFragment<HomePresenter> implements IHomeView {

    private BannerView bannerView;
    private ToolBar toolBar;
    private BannerAdapter mAdapter;

    private LinearLayout ll;
    private ArrayList<View> indicationList;
    private RecyclerView recyclerView;
    private HorListAdapter adapter;

    LocationEvent event;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initAllWidget(View view) {
        toolBar = view.findViewById(R.id.tool_bar);
        toolBar.setTitle("都市民工");
        toolBar.showRightIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(ParamsConfig.LOADURL, "http://180110fg0025.umaman.com/hongbao/game.html?nickname=&mobile=15818888899&avatar=&ut=abc&activity_id=5a607604d3df9001a5343685");
                getActivity().startActivity(intent);

            }
        });
        toolBar.showLacIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), event.location.address, Toast.LENGTH_LONG).show();
            }
        });
        bannerView = view.findViewById(R.id.bannerView);
        ll = view.findViewById(R.id.corn_ll);
        recyclerView = view.findViewById(R.id.hor_recycle);

        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        OttoBus.getInstance().register(this);
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    @Subscribe
    public void getLocationMes(LocationEvent event) {
        this.event = event;

    }

    @Override
    public void getHomeMes(HomePageModel model) {
        model.convertMes(model.rotate);

        bannerView.setAdapter(mAdapter = new BannerAdapter(getContext()));
        mAdapter.setOnPageTouchListener(new BannerBaseAdapter.OnPageTouchListener<HomePageModel.PageView>() {
            @Override
            public void onPageClick(int position, HomePageModel.PageView pageView) {

            }

            @Override
            public void onPageDown() {
                bannerView.stopAutoScroll();
            }

            @Override
            public void onPageUp() {
                bannerView.startAutoScroll();
            }
        });
        mAdapter.setData(model.reRotate);

        indicationList = new ArrayList<View>();
        for (int i = 0; i < model.reRotate.size(); i++) {

            ImageView iv2 = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(15, 0, 0, 0);
            iv2.setLayoutParams(lp);
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
            indicationList.add(iv2);
            //添加到圆点布局
            ll.addView(iv2);
        }

        bannerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bannerPointLight(position % indicationList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        adapter = new HorListAdapter(getContext(), model.cates);
        recyclerView.setAdapter(adapter);
        adapter.setOnfenleiClickListener(new HorListAdapter.OnfenleiClickListener() {
            @Override
            public void jump(String link) {

                if (LoginHelper.isLogin()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ParamsConfig.LOADURL, link);
                    to(WebActivity.class, bundle);
                } else {
                    to(LoginActivity.class);
                }

            }
        });


    }

    private void bannerPointLight(int currentPoint) {
        for (int i = 0; i < indicationList.size(); i++) {
            if (currentPoint == i) {
                indicationList.get(i).setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                indicationList.get(i).setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
        }
    }

    private class BannerAdapter extends BannerBaseAdapter<HomePageModel.PageView> {


        public BannerAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getLayoutResID() {
            return R.layout.banner_item;
        }

        @Override
        protected void convert(View convertView, HomePageModel.PageView data) {
            setImageUrl(R.id.img_1, data.url);
        }
    }


}
