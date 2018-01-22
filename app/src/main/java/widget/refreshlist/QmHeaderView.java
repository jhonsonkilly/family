/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package widget.refreshlist;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import family.live.R;


/**
 * @author Chris
 * @time 16/7/11 下午6:10
 * @function 头部的布局
 */

public class QmHeaderView extends RelativeLayout {

    FrameLayout mContainer;
    TextView mHintTextView;
    SimpleDraweeView mRefresh_img;






    public QmHeaderView(Context context) {
        super(context);
        initView(context);
    }


    public QmHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QmHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

        mContainer = (FrameLayout) LayoutInflater.from(context).inflate(
                R.layout.act_recycle_header, null);





    }






    public View getContentView(){
        return mContainer;
    }





}
