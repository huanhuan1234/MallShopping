package hhh.bawei.com.myshopping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hhh.bawei.com.myshopping.fragment.IndexFragment;

/**
 * Created by Huangminghuan on 2017/6/21.
 */

public class FirstFragmentAdapter extends FragmentPagerAdapter {
    String [] titles = {"上新","纸尿裤","奶粉","洗护喂养","玩具","服饰","图书","车床座椅"} ;

    public FirstFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       /* Fragment fragment = null;

        switch (position) {

            case 0:

                fragment = new IndexFragment();
                break;

            case 1:

                fragment = new FirstFragmentTwo();

                break;

            case 2:

                fragment = new FirstFragmentThree();

                break;

            default:

                fragment = new FirstFragmentOther();

                Bundle bundle = new Bundle();

                bundle.putString("content", titles[position]);

                fragment.setArguments(bundle);

                break;

        }*/

        return IndexFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
