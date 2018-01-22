package widget.refreshlist;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import family.live.R;


/**
 * Created by mac on 16/7/12.
 */
public class QmFooterView extends LinearLayout {

    FrameLayout mContainer;

    ImageView mRefresh_img;


    public QmFooterView(Context context) {
        super(context);
        initView(context);
    }

    public QmFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QmFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

        mContainer = (FrameLayout) LayoutInflater.from(context).inflate(
                R.layout.act_recycle_footer, null);


    }


    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContainer
                .getLayoutParams();
        lp.bottomMargin = height;
        mContainer.setLayoutParams(lp);
    }

    public View getContentView() {

        return mContainer;
    }

    public ImageView getAnimationView() {

        return mRefresh_img;
    }


}
