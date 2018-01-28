package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import activity.ImagePreviewActivity;
import family.live.R;
import model.HomePageModel;
import utils.BaseViewHolder;


/**
 * Created by mac on 2017/11/16.
 */

public class HorItemListAdapter extends RecyclerView.Adapter<HorItemListAdapter.Holder> {


    Context context;
    List<String> datalist;
    RecyclerView recyclerView;


    public HorItemListAdapter(Context context, List<String> list, RecyclerView view) {
        this.datalist = list;
        this.recyclerView = view;
        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qiangdan_hor_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        try {

            FrescoUtils.displayUrl(holder.hor_img, datalist.get(position));
            holder.hor_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ImagePreviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) datalist);
                    bundle.putSerializable(ImagePreviewActivity.IMAGE_RECT, (Serializable) getImageViewsDrawableRects(recyclerView));
                    bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, position);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    // 禁用动画
                    ((Activity) context).overridePendingTransition(0, 0);

                }
            });


        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }


    class Holder extends RecyclerView.ViewHolder {


        SimpleDraweeView hor_img;

        TextView hor_text;

        public Holder(View convertView) {

            super(convertView);
            hor_img = BaseViewHolder.get(convertView, R.id.hor_img);
            hor_text = BaseViewHolder.get(convertView, R.id.hor_text);
        }
    }

    public List<Rect> getImageViewsDrawableRects(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        if (childCount <= 0) {
            return null;
        } else {
            LinkedList<Rect> viewRects = new LinkedList<>();

            for (int i = 0; i < childCount; ++i) {
                View v = viewGroup.getChildAt(i);
                if (v != null) {
                    Rect rect = this.getDrawableBoundsInView((ViewGroup) v);
                    viewRects.add(rect);
                }
            }

            return viewRects;
        }
    }

    private Rect getDrawableBoundsInView(ViewGroup iv) {
        if (iv != null) {

            Rect result = new Rect();
            iv.getGlobalVisibleRect(result);


            return result;
        } else {
            return null;
        }
    }
}
