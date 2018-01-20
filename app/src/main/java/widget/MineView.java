package widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

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
 * Created by mac on 2018/1/19.
 */

public class MineView extends FrameLayout implements View.OnClickListener {
    Context context;
    private UserView userView;
    private GongJiangView gongJiangView;
    private ImageView my_left;
    private ImageView my_right;
    private TextView tx_user;
    private TextView tx_gongjiang;
    private ToolBar toolBar;
    private TextView textView_phone;
    private TextView textView_cost;
    private SimpleDraweeView simpleDraweeView;

    public MineView(@NonNull Context context) {
        super(context);
        init(context);

    }

    public MineView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MineView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.act_mine, null);
        toolBar = root.findViewById(R.id.tool_bar);
        toolBar.setTitle("个人中心");
        userView = root.findViewById(R.id.userview);
        gongJiangView = root.findViewById(R.id.gongjiangview);
        my_left = root.findViewById(R.id.my_left);
        my_right = root.findViewById(R.id.my_right);
        tx_user = root.findViewById(R.id.user_text);
        tx_user.setOnClickListener(this);
        tx_gongjiang = root.findViewById(R.id.gongjiang_text);
        tx_gongjiang.setOnClickListener(this);
        textView_phone = root.findViewById(R.id.phone);
        textView_cost = root.findViewById(R.id.cost);
        simpleDraweeView = root.findViewById(R.id.circle);
        root.findViewById(R.id.tx_setting).setOnClickListener(this);

        addView(root);
        OttoBus.getInstance().register(this);
        showUser();
        showPersonalMes();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_text:
                showUser();
                break;
            case R.id.gongjiang_text:
                showGongJiang();
                break;
            case R.id.tx_setting:
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(ParamsConfig.LOADURL, "http://tz.tensdo.com/account/edit");
                context.startActivity(intent);

                break;
        }
    }

    public void showUser() {
        my_left.setVisibility(VISIBLE);
        my_right.setVisibility(INVISIBLE);
        userView.setVisibility(VISIBLE);
        gongJiangView.setVisibility(GONE);
        tx_user.setTextColor(getResources().getColor(R.color.theme_color));
        tx_gongjiang.setTextColor(getResources().getColor(R.color.main_title_color));


    }



    @Subscribe
    public void LoginSucess(LoginSucessEvent event) {
        try {
            showPersonalMes();
        } catch (Exception e) {

        }

    }
    public void showPersonalMes() {
        textView_phone.setText(UserModel.getPhone());
        textView_cost.setText("余额:  " + UserModel.getScore());
        FrescoUtils.displayUrl(simpleDraweeView, UserModel.getCover());
    }

    public void showGongJiang() {
        my_left.setVisibility(INVISIBLE);
        my_right.setVisibility(VISIBLE);
        userView.setVisibility(GONE);
        gongJiangView.setVisibility(VISIBLE);
        tx_user.setTextColor(getResources().getColor(R.color.main_title_color));
        tx_gongjiang.setTextColor(getResources().getColor(R.color.theme_color));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OttoBus.getInstance().unregister(this);
    }
}
