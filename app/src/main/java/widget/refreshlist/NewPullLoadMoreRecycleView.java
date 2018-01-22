package widget.refreshlist;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;


import java.util.List;

import family.live.R;
import widget.PullLoadMoreListener;

/**
 * Created by mac on 16/7/13.
 */
public class NewPullLoadMoreRecycleView extends FrameLayout
        implements SuperSwipeRefreshLayout.OnPullRefreshListener, SuperSwipeRefreshLayout.OnPushLoadMoreListener {

    SuperSwipeRefreshLayout mSwipeRefreshLayout;

    RecyclerView mRecyclerView;

    PullLoadMoreListener mPullLoadMoreListener;

    PullRefreshAdapter mAdapter;

    QmHeaderView headerView;
    QmFooterView footerView;
    QmLoadMoreFootView overfootView;

    Context context;
    OnEmptyDataListener onEmptyDatalistener;
    int state;


    public NewPullLoadMoreRecycleView(Context context) {
        super(context);
        initView(context);
    }

    public NewPullLoadMoreRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NewPullLoadMoreRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.new_pull_loadmore_layout, this);
        mSwipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setOnPullRefreshListener(this);
        mSwipeRefreshLayout.setOnPushLoadMoreListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        init();

    }

    @Override
    public void onRefresh() {

        if (mPullLoadMoreListener != null) {


            mPullLoadMoreListener.onRefresh();
        }

    }


    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }

    @Override
    public void onPullStatus() {

        //  headerView.setState((int) (1 + Math.random() * 4));
    }

    @Override
    public void onLoadMore() {

        if (mPullLoadMoreListener != null) {

            mPullLoadMoreListener.onLoadMore();
        }

    }

    @Override
    public void onPushDistance(int distance) {

    }

    @Override
    public void onPushEnable(boolean enable) {

    }

    @Override
    public void onPushStatus() {


        state = (int) (1 + Math.random() * 2);
        //  footerView.setState(state);
        //startAnimation();

    }


    @Override
    public void startUpAnimation(int distance) {


    }


    public void setPullLoadMoreListener(PullLoadMoreListener listener) {

        this.mPullLoadMoreListener = listener;
    }

    /**
     * 用于判断是横向滑动还是纵向滑动的 case  避免横滑成为竖滑
     *
     * @param ev
     * @return
     */
    private float xDistance, yDistance, xLast, yLast;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setAdapter(PullRefreshAdapter mAdapter) {

        this.mAdapter = mAdapter;
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLinearLayout(PullRefreshAdapter adapter) {

        if (mAdapter == null) {
            mAdapter = adapter;
            mRecyclerView.setAdapter(mAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }
    }


    // GridLayoutManager
    public void setGridLayout(PullRefreshAdapter adapter, int spanCount) {

        if (mAdapter == null) {
            mAdapter = adapter;
            mRecyclerView.setAdapter(mAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }
    }

    public RecyclerView getRecyclerView() {

        return mRecyclerView;
    }


    public void showMoreData(List list) {


        mAdapter.addAll(list);
    }

    public void clearData() {

        mAdapter.removeAll();
    }


    public void init() {

        headerView = new QmHeaderView(context);
        footerView = new QmFooterView(context);
        overfootView = new QmLoadMoreFootView(context);
        mSwipeRefreshLayout.setHeaderView(headerView.getContentView());
        showEndAnimation(false);
    }

    public void setHasMore(boolean hasMore) {

        mSwipeRefreshLayout.setLoadMore(hasMore);
    }

    public void setRefresh(boolean refresh) {

        mSwipeRefreshLayout.setLoadMore(refresh);
    }

    public boolean isHasMore() {

        return mSwipeRefreshLayout.isLoadMore();
    }

    /**
     * @author Chris
     * @time 16/7/15 下午2:51
     * @function 包含下啦上啦的逻辑
     */
    public void stopAnim() {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mSwipeRefreshLayout.isLoadMore()) {
            mSwipeRefreshLayout.setLoadMore(false);
        }

    }


    public void removeData(String room_id) {
    }

    public void removeHostData(String uid) {
    }

    public interface OnEmptyDataListener {

        void showEmptyResult(String title, String content, boolean isShow, int res);
    }

    public void setOnEmptyDatalistener(OnEmptyDataListener onEmptyDatalistener) {

        this.onEmptyDatalistener = onEmptyDatalistener;

    }

    /**
     * @author Chris
     * @time 16/7/29 下午2:00
     * @function 区分是最后一页还不是最后一页的加载
     */
    public void showEndAnimation(boolean isOver) {

        mSwipeRefreshLayout.setMoveOver(isOver);
        if (isOver) {
            mSwipeRefreshLayout.setFooterView(footerView.getContentView());

        } else {
            mSwipeRefreshLayout.setFooterView(overfootView.getContentView());

        }


    }


}
