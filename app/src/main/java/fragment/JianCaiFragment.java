package fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.banner.BannerBaseAdapter;
import com.androidyuan.frame.cores.banner.BannerView;
import com.androidyuan.frame.cores.widget.FixHeightListView;

import java.util.ArrayList;

import Event.JianCaiFrgAttachEvent;
import Event.LocationEvent;
import activity.LoginActivity;
import activity.WebActivity;
import adapter.HorListAdapter;
import adapter.JianCaiHorListAdapter;
import adapter.TuiJianAdapter;
import config.LoginHelper;
import config.ParamsConfig;
import family.live.R;
import iview.IJianCaiView;
import model.HomePageModel;
import model.ShopListModel;
import otto.OttoBus;
import otto.Subscribe;
import presenter.JianCaiPresenter;
import widget.ToolBar;

/**
 * Created by mac on 18/1/19.
 */

public class JianCaiFragment extends BaseCommFragment<JianCaiPresenter> implements IJianCaiView {

    private BannerView bannerView;

    private ArrayList<View> indicationList;

    private BannerAdapter mAdapter;
    LinearLayout ll;
    private RecyclerView recyclerView;
    private JianCaiHorListAdapter adapter;
    private FixHeightListView fixHeightListView;
    private ToolBar toolBar;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_jiancai;
    }

    @Override
    protected void initAllWidget(View view) {

        bannerView = view.findViewById(R.id.bannerView);
        ll = view.findViewById(R.id.corn_ll);
        recyclerView = view.findViewById(R.id.hor_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        fixHeightListView = view.findViewById(R.id.product_list);
        toolBar = view.findViewById(R.id.tool_bar);
        toolBar.showSarch().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginHelper.isLogin()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ParamsConfig.LOADURL, "http://tz.tensdo.com/search");
                    to(WebActivity.class, bundle);
                } else {
                    to(LoginActivity.class);
                }
            }
        });
    }

    @Override
    protected void clickView(View v) {

    }

    @Subscribe
    public void getLocMes(LocationEvent event) {
        try {
            presenter.getShopList(event.location.x, event.location.y);
        } catch (Exception e) {

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        OttoBus.getInstance().register(this);
        OttoBus.getInstance().post(new JianCaiFrgAttachEvent());
    }

    @Override
    public void getShopList(ShopListModel model) {

        if (mAdapter == null) {
            bannerView.setAdapter(mAdapter = new BannerAdapter(getContext()));
            mAdapter.setOnPageTouchListener(new BannerBaseAdapter.OnPageTouchListener<ShopListModel.Rotate>() {


                @Override
                public void onPageClick(int position, ShopListModel.Rotate rotate) {

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
            mAdapter.setData(model.rotate);

            indicationList = new ArrayList<View>();
            for (int i = 0; i < model.rotate.size(); i++) {

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

            adapter = new JianCaiHorListAdapter(getContext(), model.cates);
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

            recyclerView.setAdapter(adapter);

            TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getContext(), model.items);

            fixHeightListView.setAdapter(tuiJianAdapter);
        }


    }

    private class BannerAdapter extends BannerBaseAdapter<ShopListModel.Rotate> {

        public BannerAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getLayoutResID() {
            return R.layout.banner_item;
        }

        @Override
        protected void convert(View convertView, ShopListModel.Rotate data) {
            setImageUrl(R.id.img_1, data.img_url);
        }
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
}
