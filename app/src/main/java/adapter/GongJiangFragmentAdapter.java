package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.GongJiangFragmentItem;
import fragment.QiangDanFragmentItem;

/**
 * Created by mac on 18/1/22.
 */

public class GongJiangFragmentAdapter extends FragmentStatePagerAdapter {


    public GongJiangFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GongJiangFragmentItem.getInstance(position);

    }

    @Override
    public int getCount() {
        return 3;
    }
}
