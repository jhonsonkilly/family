package widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


import family.live.R;

/**
 * Created by mac on 2018/1/24.
 */

public class RegistView extends FrameLayout implements View.OnClickListener {


    private ToolBar toolbar;

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
        View root = View.inflate(context, R.layout.regist_view, null);
        toolbar = root.findViewById(R.id.tool_bar);
        toolbar.setTitle("注册");
        root.findViewById(R.id.phone);
        root.findViewById(R.id.vertify_code);
        root.findViewById(R.id.vertify_button);
        root.findViewById(R.id.set_password);
        root.findViewById(R.id.cofirmPassword);
        root.findViewById(R.id.confirm_regist);
        root.findViewById(R.id.login);
    }

    @Override
    public void onClick(View view) {

    }
}
