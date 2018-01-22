package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Event.JianCaiFrgAttachEvent;
import Event.LocationEvent;
import Event.QiangDanFrgAttachEvent;
import adapter.QiangDanAdapter;
import config.ParamsConfig;
import family.live.R;
import iview.IQiangDanView;
import model.QiangDanModel;
import otto.OttoBus;
import otto.Subscribe;
import presenter.QiangDanItemPresenter;
import widget.PullLoadMoreListener;
import widget.refreshlist.NewPullLoadMoreRecycleView;

/**
 * Created by mac on 18/1/22.
 */

public class QiangDanFragmentItem extends BaseCommFragment<QiangDanItemPresenter> implements IQiangDanView {

    private NewPullLoadMoreRecycleView recyclerView;
    private int position;
    LocationEvent event;
    int page = 1;
    private List<QiangDanModel.Items> mList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt(ParamsConfig.QIANGDAN_POS);

            OttoBus.getInstance().register(this);
            OttoBus.getInstance().post(new QiangDanFrgAttachEvent());

        }

        return R.layout.frg_qiangdan_item;
    }

    @Override
    protected void initAllWidget(View view) {
        recyclerView = view.findViewById(R.id.pull_recyclerview);
        recyclerView.setPullLoadMoreListener(new PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getQiangDanList(event.location.x, event.location.y, position, page);
            }

            @Override
            public void onLoadMore() {

                if (page < 3) {
                    page++;
                    presenter.getQiangDanList(event.location.x, event.location.y, position, page);
                } else {
                    recyclerView.stopAnim();
                }


            }
        });
        recyclerView.setLinearLayout(new QiangDanAdapter(mList, getContext()));

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
                presenter.getQiangDanList(event.location.x, event.location.y, position, page);
            }

        } catch (Exception e) {

        }

    }


    public static QiangDanFragmentItem getInstance(int position) {
        QiangDanFragmentItem newFragment = new QiangDanFragmentItem();
        Bundle bundle = new Bundle();
        bundle.putInt(ParamsConfig.QIANGDAN_POS, position);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    @Override
    public void getQiangDanList(QiangDanModel model) {

        recyclerView.stopAnim();
        if (page == 1) {
            recyclerView.clearData();
        }
        recyclerView.showMoreData(model.items);
        if (page == 3) {
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.showEndAnimation(true);
                }
            }, 300);

        } else {

            recyclerView.showEndAnimation(false);
        }
    }
}
