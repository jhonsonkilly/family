package fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidyuan.frame.base.activity.WineApplication;
import com.androidyuan.frame.base.fragment.BaseCommFragment;

import java.util.ArrayList;
import java.util.List;

import Event.GongJiangFrgAttachEvent;
import Event.LocationEvent;
import Event.QiangDanFrgAttachEvent;
import activity.LoginActivity;
import adapter.GongJiangAdapter;
import adapter.GongJiangFragmentAdapter;
import adapter.QiangDanAdapter;
import config.LoginHelper;
import config.ParamsConfig;
import family.live.R;
import iview.IGongJiangView;
import iview.IQiangDanView;
import model.GongJiangModel;
import model.GongJiangZanModel;
import model.QiangDanModel;
import otto.OttoBus;
import otto.Subscribe;
import presenter.GongJiangItemPresenter;
import presenter.QiangDanItemPresenter;
import widget.PullLoadMoreListener;
import widget.refreshlist.NewPullLoadMoreRecycleView;

/**
 * Created by mac on 2018/1/25.
 */

public class GongJiangFragmentItem extends BaseCommFragment<GongJiangItemPresenter> implements IGongJiangView {
    private NewPullLoadMoreRecycleView recyclerView;
    private int position;
    LocationEvent event;
    int page = 1;
    int total_page;
    private List<GongJiangModel.Items> mList = new ArrayList<>();

    private GongJiangAdapter adapter;


    @Override
    protected int getLayoutId() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt(ParamsConfig.GONGJING_POS);

            OttoBus.getInstance().register(this);
            OttoBus.getInstance().post(new GongJiangFrgAttachEvent());

        }

        return R.layout.frg_gongjiang_item;
    }

    @Override
    protected void initAllWidget(View view) {
        recyclerView = view.findViewById(R.id.pull_recyclerview);

        recyclerView.setPullLoadMoreListener(new PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                try {
                    page = 1;
                    presenter.getGongJiangList(event.location.x, event.location.y, position, page);
                } catch (Exception e) {

                }

            }

            @Override
            public void onLoadMore() {

                if (page < total_page) {
                    page++;
                    presenter.getGongJiangList(event.location.x, event.location.y, position, page);
                } else {
                    recyclerView.stopAnim();
                }


            }
        });
        adapter = new GongJiangAdapter(mList, getContext());
        adapter.setOnQiangDanClick(new GongJiangAdapter.OnQiangDanClick() {
            @Override
            public void click(String id) {


                if (LoginHelper.isLogin()) {

                    presenter.getGongJiangZan(id);


                } else {
                    to(LoginActivity.class);
                }

            }
        });
        recyclerView.setLinearLayout(adapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    protected void clickView(View v) {

    }

    @Subscribe
    public void getLocMes(LocationEvent event) {
        this.event = event;

        try {
            if (event != null) {
                presenter.getGongJiangList(event.location.x, event.location.y, position, page);
            }

        } catch (Exception e) {

        }

    }


    public static GongJiangFragmentItem getInstance(int position) {
        GongJiangFragmentItem newFragment = new GongJiangFragmentItem();
        Bundle bundle = new Bundle();
        bundle.putInt(ParamsConfig.GONGJING_POS, position);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }


    @Override
    public void getGongJiangList(GongJiangModel model) {

        total_page = (int) Math.ceil((Integer.parseInt(model.total_count) + 1f) / ParamsConfig.Page.total_number);

        if (model.items.size() != 0) {
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.stopAnim();
            if (page == 1) {
                recyclerView.clearData();
            }
            recyclerView.showMoreData(model.items);
            if (page == total_page) {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.showEndAnimation(true);
                    }
                }, 300);

            } else {

                recyclerView.showEndAnimation(false);
            }
        } else {
            recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void QiangDanSucess() {

        presenter.getGongJiangList(event.location.x, event.location.y, position, page);


    }


}
