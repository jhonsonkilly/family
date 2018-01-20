package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.LinkedHashMap;
import java.util.List;

import activity.LoginActivity;
import activity.WebActivity;
import config.LoginHelper;
import config.ParamsConfig;
import family.live.R;
import model.ShopListModel;

/**
 * Created by mac on 18/1/20.
 */

public class TuiJianAdapter extends BaseAdapter {

    Context context;
    List<ShopListModel.Items> list;

    SimpleDraweeView img;

    TextView textPrice;

    TextView textView;
    TextView saleText;

    ImageView addImg;
    private RelativeLayout pro_rl;


    public TuiJianAdapter(Context context, List<ShopListModel.Items> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
                convertView = View.inflate(context, R.layout.item_list, null);
            }

            img = (SimpleDraweeView) convertView.findViewById(R.id.img_banner);
            textView = (TextView) convertView.findViewById(R.id.text_1);
            textPrice = (TextView) convertView.findViewById(R.id.price);
            saleText = (TextView) convertView.findViewById(R.id.sale);
            addImg = (ImageView) convertView.findViewById(R.id.cart);
            pro_rl = convertView.findViewById(R.id.pro_rl);


            FrescoUtils.displayUrl(img, list.get(position).cover);
            textView.setText(list.get(position).title);
            textPrice.setText(list.get(position).city_name);
            saleText.setText("距离您" + list.get(position).juli + "公里");


            pro_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (LoginHelper.isLogin()) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra(ParamsConfig.LOADURL, list.get(position).link);
                        context.startActivity(intent);

                    } else {

                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                }
            });


            return convertView;

        } catch (Exception e) {
            return null;
        }
    }
}
