package adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.androidyuan.frame.cores.widget.FixChildHeightGridView;
import com.facebook.drawee.view.SimpleDraweeView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import activity.ImagePreviewActivity;
import family.live.R;


/**
 * Created by mac on 2017/11/4.
 */

public class GongJiangItemAdapter extends BaseAdapter {

    List<String> list = new ArrayList<>();
    Context context;

    SimpleDraweeView img;

    TextView textView;

    FixChildHeightGridView fixChildHeightGridView;

    public GongJiangItemAdapter(Context context, List<String> list, FixChildHeightGridView fixChildHeightGridView) {
        this.list = list;
        this.context = context;
        this.fixChildHeightGridView = fixChildHeightGridView;

    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_gridview, null);
            }

            img = (SimpleDraweeView) convertView.findViewById(R.id.img_1);


            FrescoUtils.displayUrl(img, list.get(position));

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ImagePreviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) list);
                    bundle.putSerializable(ImagePreviewActivity.IMAGE_RECT, (Serializable) getImageViewsDrawableRects(fixChildHeightGridView));
                    bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, position);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    // 禁用动画
                    ((Activity) context).overridePendingTransition(0, 0);
                }
            });


            return convertView;

        } catch (Exception e) {
            return null;
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
