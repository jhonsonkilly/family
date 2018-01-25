package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import family.live.R;
import model.HomePageModel;
import utils.BaseViewHolder;


/**
 * Created by mac on 2017/11/16.
 */

public class HorItemListAdapter extends RecyclerView.Adapter<HorItemListAdapter.Holder> {


    Context context;
    List<String> datalist;




    public HorItemListAdapter(Context context, List<String> list) {
        this.datalist = list;

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
}
