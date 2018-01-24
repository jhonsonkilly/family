package fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidyuan.frame.base.activity.WineApplication;
import com.androidyuan.frame.base.fragment.BaseCommFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Event.JianCaiFrgAttachEvent;
import Event.LocationEvent;
import Event.QiangDanClickEvent;
import Event.QiangDanFrgAttachEvent;
import activity.LoginActivity;
import adapter.QiangDanAdapter;
import config.LoginHelper;
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
    int total_page;
    private List<QiangDanModel.Items> mList = new ArrayList<>();
    private TextView text_no_data;
    private QiangDanAdapter adapter;
    Button button;


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
        text_no_data = view.findViewById(R.id.no_data);
        recyclerView.setPullLoadMoreListener(new PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getQiangDanList(event.location.x, event.location.y, position, page);
            }

            @Override
            public void onLoadMore() {

                if (page < total_page) {
                    page++;
                    presenter.getQiangDanList(event.location.x, event.location.y, position, page);
                } else {
                    recyclerView.stopAnim();
                }


            }
        });
        adapter = new QiangDanAdapter(mList, getContext());
        adapter.setOnQiangDanClick(new QiangDanAdapter.OnQiangDanClick() {
            @Override
            public void click(final String id, Button button) {
                QiangDanFragmentItem.this.button = button;
                if (LoginHelper.isLogin()) {
                    final Dialog dialog = new Dialog(getContext(), R.style.MyDialog);

                    dialog.setContentView(R.layout.layout_servicedialog);
                    final EditText editText = dialog.findViewById(R.id.money);
                    dialog.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.findViewById(R.id.text_confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            presenter.getQiangDanYuYue(id, editText.getText().toString());
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
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


        total_page = (int) Math.ceil((Integer.parseInt(model.total_count) + 1f) / ParamsConfig.Page.total_number);

        if (model.items.size() != 0) {
            recyclerView.setVisibility(View.VISIBLE);
            text_no_data.setVisibility(View.GONE);
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
            text_no_data.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void QiangDanSucess(String msg) {
        Toast.makeText(WineApplication.gainContext(), msg, Toast.LENGTH_LONG).show();
        button.setBackgroundResource(R.drawable.shape_gray_corner_3);
        button.setClickable(false);

    }
}
