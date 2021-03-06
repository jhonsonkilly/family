package activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;


import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import Event.GongJiangFrgAttachEvent;
import Event.JianCaiFrgAttachEvent;
import Event.LocationEvent;
import Event.QiangDanFrgAttachEvent;
import family.live.R;
import fragment.GongJiangFragment;
import fragment.HomeFragment;
import fragment.JianCaiFragment;
import fragment.MineFragment;
import fragment.QiangDanFragment;
import model.MapWrapper;
import otto.OttoBus;
import otto.Subscribe;
import presenter.MainTabsPresenter;
import utils.LocationManager;
import widget.TabChooser;
import widget.TabChooserBean;
import widget.TabSelectListener;


/**
 * Created by mac on 2017/10/16.
 */

public class MainTabsActivity extends BaseCommActivity<MainTabsPresenter> {


    private String[] titleArr;
    private TabChooser tab_bar;
    private List<TabChooserBean> list = new ArrayList<>();

    private Fragment newFragment;
    private Fragment oldFragment;

    private HomeFragment fragmentHome;
    private GongJiangFragment fragmentGongJiang;
    private QiangDanFragment fragmentQiangDan;

    private MineFragment fragmentMy;
    private JianCaiFragment fragmentJianCai;

    protected boolean bActive = true;

    LocationManager locationManager;


    private int[] imgArr = new int[]{R.mipmap.footer_home_icon, R.mipmap.home_30, R.mipmap.home_36, R.mipmap.home_39, R.mipmap.home_42};


    LocationManager.MapLocation location;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_tabs_main;
    }

    @Override
    protected void initAllWidget() {


        titleArr = getResources().getStringArray(R.array.home_tabs);
        tab_bar = (TabChooser) findViewById(R.id.tab_bar);
        tab_bar.setTabSelectListener(new TabSelectListener() {
            @Override
            public void select(int position) {
                switch (position) {
                    case 0:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentHome);
                        newFragment = fragmentHome;

                        break;
                    case 1:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentJianCai);
                        newFragment = fragmentJianCai;


                        break;
                    case 2:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentQiangDan);
                        newFragment = fragmentQiangDan;
                        break;
                    case 3:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentGongJiang);
                        newFragment = fragmentGongJiang;

                        break;
                    case 4:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentMy);
                        newFragment = fragmentMy;
                        //StatusBarCompat.setStatusBarColor(MainActivity.this, Color.parseColor("#FFB300"), false);
                        break;
                    default:
                        break;
                }
            }
        });
        fragmentHome = new HomeFragment();
        fragmentJianCai = new JianCaiFragment();

        fragmentQiangDan = new QiangDanFragment();

//
        fragmentMy = new MineFragment();


        fragmentGongJiang = new GongJiangFragment();

        for (int i = 0; i < titleArr.length; i++) {
            TabChooserBean bean = new TabChooserBean();
            bean.imagesrc = imgArr[i];
            bean.tabcontent = titleArr[i];
            list.add(bean);
        }
        newFragment = fragmentHome;
        addFragment(newFragment);
        tab_bar.setTabList(list);



        OttoBus.getInstance().register(this);

        locationManager = new LocationManager(this);
        getLocationMes();


    }

    public void getLocationMes() {
        locationManager.setLocationListener(new LocationManager.LocationListener() {
            @Override
            public void onLocationChanged(LocationManager.MapLocation location) {
                if (location != null) {

                    MainTabsActivity.this.location = location;

                    OttoBus.getInstance().post(new LocationEvent(location));


                }
            }
        }).setOnceLocation(true)
                .startLocation(getActivity());
    }


    public void switchFragment(Fragment from, Fragment to) {
        if (!bActive) return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (to.isAdded() || fragmentManager.getFragments().contains(to)) {
            // 隐藏当前的fragment，显示下一个
            fragmentManager.beginTransaction().hide(from).show(to).commitAllowingStateLoss();
        } else {
            // 隐藏当前的fragment，add下一个到Activity中
            fragmentManager.beginTransaction().hide(from).add(R.id.centerlayout, to).commitAllowingStateLoss();
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.centerlayout, fragment).commitAllowingStateLoss();
        }
    }


    @Override
    protected void clickView(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }

    @Subscribe
    public void getJianCaiFrg(JianCaiFrgAttachEvent event) {

        OttoBus.getInstance().post(new LocationEvent(location));
    }

    @Subscribe
    public void getQiangDanFrg(QiangDanFrgAttachEvent event) {

        OttoBus.getInstance().post(new LocationEvent(location));
    }

    @Subscribe
    public void getGongJiangFrg(GongJiangFrgAttachEvent event) {

        OttoBus.getInstance().post(new LocationEvent(location));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exitApp();
    }

    long[] mHits = new long[2];

    //定义一个所需的数组
    private void exitApp() {
//         数组向左移位操作
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - 2000)) {// 2000代表设定的间隔时间
            finish();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }


}
