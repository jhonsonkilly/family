package widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import Event.LoginEvent;
import activity.ForgetPassWordActivity;
import activity.RegistActivity;
import family.live.R;
import otto.OttoBus;

/**
 * Created by mac on 2018/1/19.
 */

public class LoginView extends FrameLayout implements View.OnClickListener {

    private EditText phone_number;
    private EditText phone_password;
    private Button login;
    private ToolBar toolBar;
    Context context;

    public LoginView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LoginView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoginView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context=context;
        View root = View.inflate(context, R.layout.login_view, null);
        toolBar = root.findViewById(R.id.tool_bar);
        phone_number = root.findViewById(R.id.phone);
        phone_password = root.findViewById(R.id.password);
        root.findViewById(R.id.login).setOnClickListener(this);
        root.findViewById(R.id.regist).setOnClickListener(this);
        root.findViewById(R.id.get_password).setOnClickListener(this);
        addView(root);
        OttoBus.getInstance().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OttoBus.getInstance().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                OttoBus.getInstance().post(new LoginEvent(phone_number.getText().toString(),phone_password.getText().toString()));
                break;
            case R.id.regist:


                context.startActivity(new Intent(getContext(), RegistActivity.class));
                break;
            case R.id.get_password:
                context.startActivity(new Intent(getContext(), ForgetPassWordActivity.class));

                break;
        }
    }

    public void clearMes(){
        phone_number.setText("");
        phone_password.setText("");
    }

    public void showBack(){
        toolBar.showBack();
    }
}
