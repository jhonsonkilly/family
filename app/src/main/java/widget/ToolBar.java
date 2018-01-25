package widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidyuan.frame.base.activity.BaseCommActivity;

import family.live.R;


/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.widget</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class ToolBar extends FrameLayout implements View.OnClickListener {
    Context context;
    private TextView title;
    private ImageView back_img;
    private TextView zhaungxiu;
    private ImageView locImg;
    private LinearLayout ll_search;
    private ImageView search;
    private ImageView add;


    public ToolBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.toolbar_view, null);
        title = (TextView) view.findViewById(R.id.title);
        back_img = (ImageView) view.findViewById(R.id.back);
        back_img.setOnClickListener(this);
        zhaungxiu = (TextView) view.findViewById(R.id.zhaungxiu);
        locImg = view.findViewById(R.id.loc);
        locImg.setOnClickListener(this);
        ll_search = view.findViewById(R.id.search_ll);
        ll_search.setOnClickListener(this);
        search = view.findViewById(R.id.search);
        add=view.findViewById(R.id.add);
        add.setOnClickListener(this);
        addView(view);
    }

    public void setTitle(String content) {
        title.setText(content);
    }

    public void hideBack() {
        back_img.setVisibility(GONE);
    }

    public void showBack() {
        back_img.setVisibility(VISIBLE);
    }

    public TextView showRightIcon() {
        zhaungxiu.setVisibility(VISIBLE);
        return zhaungxiu;
    }

    public ImageView showLacIcon() {
        locImg.setVisibility(VISIBLE);
        return locImg;
    }
    public ImageView showAddIcon() {
        add.setVisibility(VISIBLE);
        return add;
    }

    public LinearLayout showSarch() {
        ll_search.setVisibility(VISIBLE);
        search.setVisibility(VISIBLE);
        title.setVisibility(GONE);
        return ll_search;
    }


    @Override
    public void onClick(View view) {
        BaseCommActivity activity = (BaseCommActivity) context;
        activity.finish();
    }
}
