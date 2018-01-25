package adapter;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import family.live.R;


/**
 * Created by mac on 2017/11/4.
 */

public class GongJiangItemAdapter extends BaseAdapter {

    List<String> list = new ArrayList<>();
    Context context;

    SimpleDraweeView img;

    TextView textView;

    public GongJiangItemAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;

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


            return convertView;

        } catch (Exception e) {
            return null;
        }


    }
}
