package widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import Event.QuitLoginEvent;
import activity.LoginActivity;
import activity.WebActivity;
import config.LoginHelper;
import config.ParamsConfig;
import family.live.R;
import otto.OttoBus;

/**
 * Created by mac on 18/1/21.
 */

public class UserView extends FrameLayout implements View.OnClickListener {

    Context context;

    public UserView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public UserView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void init(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.user_view, null);
        root.findViewById(R.id.service_order).setOnClickListener(this);
        root.findViewById(R.id.shop_order).setOnClickListener(this);
        root.findViewById(R.id.shouhuo_address).setOnClickListener(this);
        root.findViewById(R.id.my_message).setOnClickListener(this);
        root.findViewById(R.id.my_account).setOnClickListener(this);
        root.findViewById(R.id.my_rizhi).setOnClickListener(this);
        root.findViewById(R.id.my_collection).setOnClickListener(this);
        root.findViewById(R.id.set_password).setOnClickListener(this);
        root.findViewById(R.id.share).setOnClickListener(this);
        root.findViewById(R.id.quit_login).setOnClickListener(this);
        addView(root);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_order:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/services");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }

                break;
            case R.id.shop_order:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/orders");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.shouhuo_address:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/shippings");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.my_message:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/messages");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.my_account:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/score");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.my_rizhi:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/cases/1");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.my_collection:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/collect");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.set_password:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/password");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.share:
                if (LoginHelper.isLogin()) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/share");
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.quit_login:
                OttoBus.getInstance().post(new QuitLoginEvent());
                break;
        }
    }
}
