package widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


import Event.GetVertifyEvent;
import activity.RegistActivity;
import family.live.R;
import otto.OttoBus;

/**
 * Created by mac on 2018/1/24.
 */

public class RegistView extends FrameLayout implements View.OnClickListener {


    private ToolBar toolbar;
    private CountDownTextView vertify_button;
    private EditText vertify_code;
    private EditText editText_phone;
    private EditText set_password;
    private EditText cofirmPassword;
    private Button confirm_regist;


    Context context;

    public RegistView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public RegistView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RegistView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.regist_view, null);
        toolbar = root.findViewById(R.id.tool_bar);
        toolbar.setTitle("注册");
        toolbar.showBack();
        editText_phone = root.findViewById(R.id.phone);
        vertify_code = root.findViewById(R.id.vertify_code);
        vertify_button = root.findViewById(R.id.vertify_button);
        vertify_button.setOnClickListener(this);
        set_password = root.findViewById(R.id.set_password);
        cofirmPassword = root.findViewById(R.id.cofirmPassword);
        confirm_regist = root.findViewById(R.id.confirm_regist);
        confirm_regist.setOnClickListener(this);
        addView(root);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.vertify_button:


                RegistActivity act = (RegistActivity) context;
                act.getPresenter().getVertifyCode(editText_phone.getText().toString());

                break;


            case R.id.confirm_regist:
                if (vertify()) {
                    RegistActivity act1 = (RegistActivity) context;
                    act1.getPresenter().confirmRegist(vertify_code.getText().toString(),
                            set_password.getText().toString(),
                            cofirmPassword.getText().toString(),
                            editText_phone.getText().toString());
                }


                break;
        }
    }

    public boolean vertify() {

        if (TextUtils.isEmpty(editText_phone.getText().toString())) {
            Toast.makeText(context, "手机号不为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(vertify_code.getText().toString())) {
            Toast.makeText(context, "验证码不为空", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    public void startCountDown() {
        vertify_button.startCountDown();
    }
}
