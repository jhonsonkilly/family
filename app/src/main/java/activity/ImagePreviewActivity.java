package activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import adapter.ImagePreviewAdapter;
import family.live.R;
import widget.AnimPhotoView;


public class ImagePreviewActivity extends AppCompatActivity implements ViewTreeObserver.OnPreDrawListener {

    public static final String IMAGE_INFO = "IMAGE_INFO";
    public static final String CURRENT_ITEM = "CURRENT_ITEM";
    public static final String IMAGE_RECT = "IMAGE_RECT";

    private RelativeLayout rootView;

    private ImagePreviewAdapter imagePreviewAdapter;
    private List<String> mImageEntities;
    private List<Rect> mEndRects;
    private int currentItem;
    private View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final TextView tv_pager = (TextView) findViewById(R.id.tv_pager);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        container = findViewById(R.id.photo_container);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        Intent intent = getIntent();
        mImageEntities = (List<String>) intent.getSerializableExtra(IMAGE_INFO);
        mEndRects = (List<Rect>) intent.getSerializableExtra(IMAGE_RECT);
        currentItem = intent.getIntExtra(CURRENT_ITEM, 0);

        imagePreviewAdapter = new ImagePreviewAdapter(this, mImageEntities);
        viewPager.setAdapter(imagePreviewAdapter);
        viewPager.setCurrentItem(currentItem);
        viewPager.getViewTreeObserver().addOnPreDrawListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                tv_pager.setText(String.format(getString(R.string.select), String.valueOf(currentItem + 1), String.valueOf(mImageEntities.size())));
            }
        });
        tv_pager.setText(String.format(getString(R.string.select), String.valueOf(currentItem + 1), String.valueOf(mImageEntities.size())));
        imagePreviewAdapter.setOnImageLongClickListener(new ImagePreviewAdapter.OnImageLongClickListener() {
            @Override
            public void onLongClick(View view, int position, String url) {
                Toast.makeText(ImagePreviewActivity.this, "长按第" + position + "条，图片URL为" + url, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishActivityAnim();
    }

    /**
     * 绘制前开始动画
     */
    @Override
    public boolean onPreDraw() {
        rootView.getViewTreeObserver().removeOnPreDrawListener(this);
        final AnimPhotoView photoView = imagePreviewAdapter.getPrimaryImageView();
        final Rect startRect = mEndRects.get(currentItem);
        photoView.playEnterAnim(startRect, container, null);
        return true;
    }

    /**
     * activity的退场动画
     */
    public void finishActivityAnim() {
        final AnimPhotoView currentPhotoView = imagePreviewAdapter.getPrimaryImageView();
        if (currentPhotoView == null) {
            super.finish();
            return;
        }

        Rect endRect;

        endRect = mEndRects.get(currentItem);


        currentPhotoView.playExitAnim(endRect, container, new AnimPhotoView.OnExitAnimEndListener() {
            @Override
            public void onExitAnimEnd() {
                ImagePreviewActivity.super.finish();
                // 禁用动画
                overridePendingTransition(0, 0);
            }
        });
    }
}
