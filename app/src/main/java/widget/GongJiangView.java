package widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import Event.LoginSucessEvent;
import Event.QuitLoginEvent;
import activity.LoginActivity;
import activity.WebActivity;
import config.LoginHelper;
import config.ParamsConfig;
import family.live.R;
import model.UserModel;
import otto.OttoBus;
import otto.Subscribe;

/**
 * Created by mac on 18/1/21.
 */

public class GongJiangView extends FrameLayout implements View.OnClickListener {

    Context context;
    private Button button;
    private LinearLayout ll_gongjiang;

    public GongJiangView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public GongJiangView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GongJiangView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        OttoBus.getInstance().register(this);
        this.context = context;
        View root = View.inflate(context, R.layout.gongjiang_view, null);
        root.findViewById(R.id.service_order).setOnClickListener(this);
        root.findViewById(R.id.order_center).setOnClickListener(this);
        root.findViewById(R.id.sucess_case).setOnClickListener(this);
        root.findViewById(R.id.host_evaluate).setOnClickListener(this);
        button = root.findViewById(R.id.button_gongjiang);
        button.setOnClickListener(this);
        ll_gongjiang = root.findViewById(R.id.ll_gongjiang);
        addView(root);
        getIsUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_order:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/c_services");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }

                break;
            case R.id.order_center:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/bids");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.sucess_case:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/cases/2");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.host_evaluate:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/comments");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;

            case R.id.button_gongjiang:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/apply");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;


        }
    }

    @Subscribe
    public void loginSucess(LoginSucessEvent event) {


        getIsUser();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OttoBus.getInstance().unregister(this);
    }

    public void getIsUser() {

        if (UserModel.isCraftsman()) {
            ll_gongjiang.setVisibility(VISIBLE);
            button.setVisibility(GONE);
        } else {
            ll_gongjiang.setVisibility(GONE);
            button.setVisibility(VISIBLE);
        }
    }
}
