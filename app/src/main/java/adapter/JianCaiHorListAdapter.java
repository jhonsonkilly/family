package adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import family.live.R;
import model.HomePageModel;
import model.ShopListModel;
import utils.BaseViewHolder;

/**
 * Created by mac on 18/1/20.
 */

public class JianCaiHorListAdapter extends RecyclerView.Adapter<JianCaiHorListAdapter.Holder> {

    Context context;
    List<ShopListModel.Cates> datalist;

    HorListAdapter.OnfenleiClickListener onfenleiClickListener;


    public JianCaiHorListAdapter(Context context, List<ShopListModel.Cates> list) {
        this.datalist = list;

        this.context = context;

    }

    @Override
    public JianCaiHorListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JianCaiHorListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jiancai_item_hor_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final JianCaiHorListAdapter.Holder holder, final int position) {
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

    public void setOnfenleiClickListener(HorListAdapter.OnfenleiClickListener onfenleiClickListener) {
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
