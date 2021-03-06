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

public class HorListAdapter extends RecyclerView.Adapter<HorListAdapter.Holder> {


    Context context;
    List<HomePageModel.Cates> datalist;

    OnfenleiClickListener onfenleiClickListener;


    public HorListAdapter(Context context, List<HomePageModel.Cates> list) {
        this.datalist = list;

        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hor_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        try {
            holder.hor_text.setText(datalist.get(position).name);
            FrescoUtils.displayUrl(holder.hor_img, datalist.get(position).cover);
            holder.hor_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onfenleiClickListener != null) {
                        onfenleiClickListener.jump(datalist.get(position).link);
                    }
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    public void setOnfenleiClickListener(OnfenleiClickListener onfenleiClickListener) {
        this.onfenleiClickListener = onfenleiClickListener;
    }

    public interface OnfenleiClickListener {


        void jump(String link);


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
