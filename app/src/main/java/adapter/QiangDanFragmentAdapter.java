package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.QiangDanFragmentItem;

/**
 * Created by mac on 18/1/22.
 */

public class QiangDanFragmentAdapter extends FragmentStatePagerAdapter {


    public QiangDanFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return QiangDanFragmentItem.getInstance(position);

    }

    @Override
    public int getCount() {
        return 3;
    }
}
