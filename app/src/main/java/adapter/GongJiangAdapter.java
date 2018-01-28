package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidyuan.frame.cores.widget.FixChildHeightGridView;

import java.util.List;

import activity.WebActivity;
import config.ParamsConfig;
import family.live.R;
import model.GongJiangModel;
import model.QiangDanModel;
import utils.BaseViewHolder;
import utils.ParamsUtils;
import widget.refreshlist.PullRefreshAdapter;


/**
 * Created by mac on 2017/11/16.
 */

public class GongJiangAdapter extends PullRefreshAdapter<GongJiangModel.Items, GongJiangAdapter.Holder> {

    OnQiangDanClick click;
    Context context;

    public GongJiangAdapter(List<GongJiangModel.Items> list, Context context) {

        super(list, context);
        this.context = context;
    }

    @Override
    public GongJiangAdapter.Holder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gongjiang_list, parent, false);
        Holder liveFragholder = new Holder(convertView);
        liveFragholder.text_title = BaseViewHolder.get(convertView, R.id.text_title);
        liveFragholder.text_date = BaseViewHolder.get(convertView, R.id.text_date);
        liveFragholder.dianzan_number = BaseViewHolder.get(convertView, R.id.dianzan_number);
        liveFragholder.pinglun_number = BaseViewHolder.get(convertView, R.id.pinglun_number);
        liveFragholder.text_date = BaseViewHolder.get(convertView, R.id.text_date);
        liveFragholder.text_name = BaseViewHolder.get(convertView, R.id.text_name);
        liveFragholder.dianzan_number_img = BaseViewHolder.get(convertView, R.id.dianzan_number_img);
        liveFragholder.text_juli = BaseViewHolder.get(convertView, R.id.text_juli);
        liveFragholder.pinglun_ll = BaseViewHolder.get(convertView, R.id.pinglun_ll);
        liveFragholder.fixChildHeightGridView = BaseViewHolder.get(convertView, R.id.gridview);
        liveFragholder.fixChildHeightGridView.setFocusableInTouchMode(false);
        liveFragholder.fixChildHeightGridView.requestFocus();

        return liveFragholder;
    }

    @Override
    public void onBindItemViewHolder(final GongJiangAdapter.Holder holder, int position) {
        final GongJiangModel.Items item = getList().get(position);
        holder.text_title.setText(item.title);
        holder.text_date.setText(item.dateline);
        holder.text_juli.setText(item.juli + "公里");
        holder.dianzan_number.setText(item.ding_count);
        holder.pinglun_number.setText(item.comment_count);
        holder.text_name.setText("工匠: " + item.user.name);
        if (item.is_ding.equals("1")) {
            holder.dianzan_number_img.setBackgroundResource(R.mipmap.show_18);

        } else {
            holder.dianzan_number_img.setBackgroundResource(R.mipmap.show_17);

        }
        holder.dianzan_number_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click != null) {
                    click.click(item.id);
                }
            }
        });
        holder.pinglun_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra(ParamsConfig.LOADURL, item.linkurl);
                context.startActivity(intent);
            }
        });

        GongJiangItemAdapter adapter = new GongJiangItemAdapter(context, item.images,holder.fixChildHeightGridView);
        holder.fixChildHeightGridView.setAdapter(adapter);


    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text_title;
        TextView text_date;
        FixChildHeightGridView fixChildHeightGridView;

        TextView dianzan_number;
        TextView pinglun_number;
        TextView text_name;
        TextView text_juli;
        ImageView dianzan_number_img;
        LinearLayout pinglun_ll;

        public Holder(View itemView) {
            super(itemView);
        }

    }

    public interface OnQiangDanClick {
        void click(String id);
    }

    public void setOnQiangDanClick(OnQiangDanClick click) {
        this.click = click;
    }
}
