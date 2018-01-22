package widget.refreshlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mac on 16/7/13.
 */
public abstract class PullRefreshAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final List<T> mList = new LinkedList<T>();


    public PullRefreshAdapter(List<T> list) {
        if (list != null) {
            mList.addAll(list);
        }
    }

    public PullRefreshAdapter(List<T> list, Context context) {
        if (list != null) {
            mList.addAll(list);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return onCreateItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        onBindItemViewHolder((VH) holder, position);
        
    }

    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindItemViewHolder(final VH holder, int position);

    @Override
    public int getItemCount() {


        return mList.size();

    }

    public List<T> getList() {

        return mList;
    }

    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);

        notifyDataSetChanged();
    }

    public void removeAll() {

        mList.clear();


    }


}
