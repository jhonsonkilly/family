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
        Holder liveFragholder=new Holder(convertView);
        liveFragholder.iv_item_isLive=BaseViewHolder.get(convertView, R.id.hor_text);


        return liveFragholder;
    }

    @Override
    public void onBindItemViewHolder(QiangDanAdapter.Holder holder, int position) {
       QiangDanModel.Items map=getList().get(position);
        holder.iv_item_isLive.setText(map.address);

    }

    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView iv_item_live_cover;
        SimpleDraweeView iv_item_recycle_host_head;
        //SimpleDraweeView iv_item_recycle_host_head;
        TextView tv_live_hostname;
        TextView tv_item_live_viewernum;
        TextView tv_item_live_title;
        TextView iv_item_isLive;

        public Holder(View itemView) {
            super(itemView);
        }

    }
}
