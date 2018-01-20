package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.androidyuan.frame.base.activity.BaseCommActivity;

import config.ParamsConfig;
import family.live.R;
import presenter.WebPresenter;
import widget.H5InputWebView;

/**
 * Created by mac on 18/1/20.
 */

public class WebActivity extends BaseCommActivity<WebPresenter> {

    private String url;
    private H5InputWebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.act_web;
    }

    @Override
    protected void initAllWidget() {
        url = getIntent().getStringExtra(ParamsConfig.LOADURL);
        webView = findViewById(R.id.webView);
        webView.loadUrl(url);
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        webView.onActivityResult(requestCode, resultCode, data);

    }


}
