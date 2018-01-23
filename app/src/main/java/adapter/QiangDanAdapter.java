package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Map;

import family.live.R;
import model.HomePageModel;
import model.QiangDanModel;
import utils.BaseViewHolder;
import widget.refreshlist.PullRefreshAdapter;


/**
 * Created by mac on 2017/11/16.
 */

public class QiangDanAdapter extends PullRefreshAdapter<QiangDanModel.Items, QiangDanAdapter.Holder> {

    public QiangDanAdapter(List<QiangDanModel.Items> list, Context context) {

        super(list, context);
    }

    @Override
    public QiangDanAdapter.Holder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qiangdan_list, parent, false);
        Holder liveFragholder = new Holder(convertView);
        liveFragholder.text_xuqiu = BaseViewHolder.get(convertView, R.id.text_xuqiu);
        liveFragholder.text_address = BaseViewHolder.get(convertView, R.id.text_address);
        liveFragholder.qiangdan_button = BaseViewHolder.get(convertView, R.id.qiangdan_button);
        liveFragholder.text_yuyue_date = BaseViewHolder.get(convertView, R.id.text_yuyue_date);
        liveFragholder.text_date = BaseViewHolder.get(convertView, R.id.text_date);
        liveFragholder.recycler_view = BaseViewHolder.get(convertView, R.id.recycler_view);
        liveFragholder.text_note = BaseViewHolder.get(convertView, R.id.beizhu);


        return liveFragholder;
    }

    @Override
    public void onBindItemViewHolder(QiangDanAdapter.Holder holder, int position) {
        QiangDanModel.Items item = getList().get(position);
        holder.text_xuqiu.setText("服务需求:  " + item.cate);
        holder.text_address.setText("服务地址:  " + item.address);
        holder.text_date.setText("预约日期:  " + item.book_date);
        holder.text_yuyue_date.setText("预约时间:  " + item.book_time);
        holder.text_note.setText("备注说明:  " + item.content);


    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text_date;
        TextView text_yuyue_date;
        Button qiangdan_button;
        TextView text_address;
        TextView text_xuqiu;
        RecyclerView recycler_view;
        TextView text_note;

        public Holder(View itemView) {
            super(itemView);
        }

    }
}
