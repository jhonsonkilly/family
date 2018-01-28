package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


import java.util.List;

import activity.ImagePreviewActivity;
import family.live.R;
import widget.AnimPhotoView;
import widget.photoview.PhotoViewAttacher;


public class ImagePreviewAdapter extends PagerAdapter implements PhotoViewAttacher.OnViewTapListener {

    private List<String> mImageEntities;
    private Context context;
    private View currentView;

    public ImagePreviewAdapter(Context context, @NonNull List<String> imageInfo) {
        super();
        this.mImageEntities = imageInfo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImageEntities.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentView = (View) object;
    }

    public AnimPhotoView getPrimaryImageView() {
        return (AnimPhotoView) currentView.findViewById(R.id.pv);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photoview, container, false);
        final ProgressBar pb = (ProgressBar) view.findViewById(R.id.pb);
        final AnimPhotoView imageView = (AnimPhotoView) view.findViewById(R.id.pv);

        final String info = this.mImageEntities.get(position);
        imageView.setOnViewTapListener(this);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.onLongClick(view, position, info);
                return false;
            }
        });
        //showExcessPic(info, imageView);

        Glide.with(context).load(info)
                .placeholder(R.color.ic_default_placeholder)
                .error(R.color.ic_default_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        pb.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pb.setVisibility(View.GONE);
                        return false;
                    }
                }).into(imageView);

        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onViewTap(View view, float x, float y) {
        ((ImagePreviewActivity) context).finishActivityAnim();
    }

    public interface OnImageLongClickListener {
        void onLongClick(View view, int position, String url);
    }

    public OnImageLongClickListener mListener;

    public void setOnImageLongClickListener(OnImageLongClickListener listener) {
        this.mListener = listener;
    }
}