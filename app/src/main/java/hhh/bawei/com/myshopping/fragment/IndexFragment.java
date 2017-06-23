package hhh.bawei.com.myshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import hhh.bawei.com.myshopping.R;
import hhh.bawei.com.myshopping.adapter.FirstFragmentIndextAdapter;
import hhh.bawei.com.myshopping.bean.IndexBean;
import hhh.bawei.com.myshopping.network.BaseObserver;
import hhh.bawei.com.myshopping.network.RetrofitFactory;


/**
 * Created by : Xunqiang
 * 2017/6/21 11:44
 */

public class IndexFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;

    private RecyclerView recyclerView;

    private List<IndexBean.DataBean> list = new ArrayList<IndexBean.DataBean>();
    private FirstFragmentIndextAdapter adapter;


    private String [] url = {"http://lib.suning.com/app/redbaby/babydiapers-64.json",
            "http://lib.suning.com/app/redbaby/babymilk-41.json",
            "http://lib.suning.com/app/redbaby/BabyBottles-56.json",
            "http://lib.suning.com/app/redbaby/babytoys-50.json",
            "http://lib.suning.com/app/redbaby/babyclothes-49.json",
            "http://lib.suning.com/app/redbaby/babybooks-39.json"} ;


    public static IndexFragment newInstance(int param1) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_first_fragment_one, container, false);

        initView(view);

        return  view;
    }

    public void initView(View view){

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_id);


        getData(mParam1);


        adapter = new FirstFragmentIndextAdapter(getActivity(),list);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }


    public void getData(int pos){

        RetrofitFactory.get(url[pos])
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {

                        Gson gson = new Gson();
                        IndexBean indexBean =  gson.fromJson(result, IndexBean.class);

                        list = indexBean.getData();
                    }

                    @Override
                    public void onFailed(int code) {

                    }
                });

    }


}
