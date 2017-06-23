package hhh.bawei.com.myshopping.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hhh.bawei.com.myshopping.R;
import hhh.bawei.com.myshopping.adapter.ThirdFragmentAdapter;
import hhh.bawei.com.myshopping.bean.ShopBean;
import hhh.bawei.com.myshopping.utils.StringUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    TextView thirdAllselect;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    @BindView(R.id.third_pay_linear)
    LinearLayout thirdPayLinear;
    Unbinder unbinder;
    private ThirdFragmentAdapter adapter;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third, container, false);

        unbinder = ButterKnife.bind(this, view);

        showData();
        return view;

    }

    //购物车所有的商品
    List<ShopBean.OrderDataBean.CartlistBean> mAllOrderList=new ArrayList<ShopBean.OrderDataBean.CartlistBean>();

    //解析shop.json文件 添加到集合
    private void showData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new ThirdFragmentAdapter(getActivity());
        thirdRecyclerview.setAdapter(adapter);
        thirdRecyclerview.setLayoutManager(linearLayoutManager);

        try {
            InputStream inputStream = getActivity().getAssets().open("shop.json");
            String data = StringUtils.convertStreamToString(inputStream);
            Gson gson = new Gson();
            ShopBean shopBean = gson.fromJson(data, ShopBean.class);
            for (int i = 0; i < shopBean.getOrderData().size(); i++) {
                int length = shopBean.getOrderData().get(i).getCartlist().size();
                for (int j = 0; j <length ; j++) {
                    mAllOrderList.add(shopBean.getOrderData().get(i).getCartlist().get(j));
                }
            }
            setFirstState(mAllOrderList);

            adapter.setData(mAllOrderList);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //删除数据的回调
        adapter.setOnDeleteClickListener(new ThirdFragmentAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position, int cartid) {

            }
        });

        //选中状态发生变化的接口回调
        adapter.setOnRefershListener(new ThirdFragmentAdapter.OnRefershListener() {
            @Override
            public void onRefersh(boolean isSelect, List<ShopBean.OrderDataBean.CartlistBean> list) {
                //标记底部 全选按钮
                if (isSelect){
                    Drawable left=getResources().getDrawable(R.drawable.shopcart_selected);
                    thirdAllselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }else {
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_unselected);
                    thirdAllselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }
                //商品总价
                float mTotlaPrice=0f;
                int mTotalNum=0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect()){
                        mTotlaPrice +=list.get(i).getPrice()*list.get(i).getCount();
                        mTotalNum +=list.get(i).getCount();
                    }
                }
                System.out.println("mTotlaPrice = " + mTotlaPrice);
                thirdTotalprice.setText("总价:"+mTotlaPrice );
                thirdTotalnum.setText("共:" +mTotalNum +"件商品");

            }
        });
    }

    /**
     * 标记第一条数据 isFirst 1 显示商户名称 2 隐藏
     */
public static void setFirstState(List<ShopBean.OrderDataBean.CartlistBean> list){
    if(list.size() > 0){
        list.get(0).setIsFirst(1);
        for(int i=1;i<list.size();i++){

            if(list.get(i).getShopId() == list.get(i-1).getShopId()){
                list.get(i).setIsFirst(2);
            }else {
                list.get(i).setIsFirst(1);
            }
        }
    }

}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
