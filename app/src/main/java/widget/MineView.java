package widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import family.live.R;

/**
 * Created by mac on 2018/1/19.
 */

public class MineView extends FrameLayout {

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
        View root = View.inflate(context, R.layout.act_mine, null);
        addView(root);
    }
}
