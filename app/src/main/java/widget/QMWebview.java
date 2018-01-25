package widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.util.HashMap;

import config.LoginHelper;


/**
 * Created by wei on 16-4-21.
 * <p>
 * 重写webview实现自动绑定cookies 和 加载的监听
 * 公司内部约定的文档存在于
 * http://doc.quanmin.tv/pages/viewpage.action?pageId=1311155
 * <p>
 * 此控件功能
 * 1. 关闭在网页上长按弹出选择菜单的体验问题
 * 2. 配置userAgent
 * 3. 自动添加cookies 在loadurl的时候
 * 4. 隐藏缩放按钮
 * 5. 默认开启js的使用
 */
public abstract class QMWebview extends WebView {

    final String BLANK_URL = "about:blank";
    Context context;


    private boolean loadError = false;
    protected WebSettings settings;

    HashMap<String, String> map = new HashMap<>();

    private final static String ALIPAY = "zfb";

    private final static String WX = "wx";

    String[] AliPayPar = {"body", "sn", "subject", "total_amount"};

    String[] WXPar = {"sn", "total_fee"};


    public QMWebview(Context context) {

        super(context);
        init(context);
    }

    public QMWebview(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context);
    }

    public QMWebview(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(context);
    }

    protected void init(final Context context) {

        this.context = context;

        settings = getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);


        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(false);
//        setEnableCache(false); //不应该关闭这个缓存
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }

        String loginMes = "access_token=" + LoginHelper.token() + "&client_id=" + LoginHelper.getClient();
        String str = settings.getUserAgentString() + loginMes;;
        settings.setUserAgentString(str);


        setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.indexOf("quanmin://") == 0) {


                    try {

                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    } catch (Exception ex) {

                    }
                    return true;
                }
                if (url.contains("home/wechat")
                        || url.contains("stores/index")
                        || url.contains("bids/index")
                        || url.contains("cases/index")
                        || url.contains("account/index")
                        || url.endsWith("account")) {


                    try {

                        ((Activity) context).finish();
                    } catch (Exception ex) {

                    }
                    return true;
                }

                if (url.contains("tel")) {


                    try {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + url.split(":")[1]));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ((Activity) context).startActivity(intent);

                    } catch (Exception ex) {

                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);

            }


            @Override
            public void onLoadResource(WebView view, String url) {

                loadError = false;
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                if (!loadError) {

                } else {

                }
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {

                super.onReceivedHttpError(view, request, errorResponse);
                loadError = true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                loadError = true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                super.onReceivedError(view, errorCode, description, failingUrl);
                loadError = true;
                if (!TextUtils.isEmpty(failingUrl) && failingUrl.startsWith("http")) {
                    loadBlank();
                }
            }


        });


    }

    protected void onFinish() {

    }

    //重写此方法 实现绑定cookies   但是setCookies之后不能再进行 setjsEnable
    @Override
    public void loadUrl(String url) {

        //clearHistory();


        Log.i("QMWeb", url + "");

        super.loadUrl(url);

    }

    public abstract String putExParams(HashMap<String, String> map);


    protected boolean isNeedPesonalMes() {
        return true;
    }


    private String getVersionName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (packInfo == null)
            return "";

        String version = packInfo.versionName;
        return version;
    }


    public void loadBlank() {

        loadUrl(BLANK_URL);
    }


}