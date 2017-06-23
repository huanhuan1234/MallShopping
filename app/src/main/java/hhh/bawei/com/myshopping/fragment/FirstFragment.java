package hhh.bawei.com.myshopping.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hhh.bawei.com.myshopping.R;
import hhh.bawei.com.myshopping.adapter.FirstFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private View view;
    private TabLayout tablayout;
    private ViewPager vp;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        vp = (ViewPager) view.findViewById(R.id.first_viewpager);

        //TabLayout 和 ViewPager 关联
        tablayout.setupWithViewPager(vp);

        // 设置 选中 未选中 字的颜色
//        tablayout.setTabTextColors(getResources().getColor(R.color.cgray),getResources().getColor(R.color.coffer));

        // 设置 指示器的颜色
        tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.title_bg));

        //设置滚动模式
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //        设置正常模式  平分
        //        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        FirstFragmentAdapter adapter = new FirstFragmentAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(adapter);
    }
}
