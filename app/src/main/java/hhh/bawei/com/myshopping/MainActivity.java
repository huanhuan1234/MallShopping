package hhh.bawei.com.myshopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import hhh.bawei.com.myshopping.fragment.FirstFragment;
import hhh.bawei.com.myshopping.fragment.FourthFragment;
import hhh.bawei.com.myshopping.fragment.SecondFragment;
import hhh.bawei.com.myshopping.fragment.ThirdFragment;

import static hhh.bawei.com.myshopping.R.id.sm;

public class MainActivity extends FragmentActivity {

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private LinearLayout richScan;
    private EditText et;
    private LinearLayout xiaoxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        creatFragment(savedInstanceState);

        switchFragment(0);

        initView();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


                switch (checkedId) {

                    case R.id.radiobutton_home:

                        switchFragment(0);
                        break;

                    case R.id.radiobutton_discover:
                        switchFragment(1);

                        break;

                    case R.id.radiobutton_feed:
                        switchFragment(2);

                        break;

                    case R.id.radiobutton_me:
                        switchFragment(3);

                        break;
                }

            }
        });

        et.setHintTextColor(Color.GRAY);
//        et.setInputType(InputType.TYPE_NULL);

        et.clearFocus();//失去焦点
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        et.requestFocus();//让EditText获得焦点，但是获得焦点并不会自动弹出键盘


        richScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.tab_radiogroup);

        RadioButton radioButtonFirst = (RadioButton) findViewById(R.id.radiobutton_home);
        RadioButton radioButtonSecond = (RadioButton) findViewById(R.id.radiobutton_discover);
        RadioButton radioButtonThird = (RadioButton) findViewById(R.id.radiobutton_feed);
        RadioButton radioButtonFourth = (RadioButton) findViewById(R.id.radiobutton_me);

        richScan = (LinearLayout) findViewById(sm);
        et = (EditText) findViewById(R.id.et);
        xiaoxi = (LinearLayout) findViewById(R.id.xiaoxi);

    }

    private void creatFragment(Bundle savedInstanceState) {
        FirstFragment firstFragment = (FirstFragment) fragmentManager.findFragmentByTag("FirstFragment");
        SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag("SecondFragment");
        ThirdFragment thirdFragment = (ThirdFragment) fragmentManager.findFragmentByTag("ThirdFragment");
        FourthFragment fourthFragment = (FourthFragment) fragmentManager.findFragmentByTag("FourthFragment");

        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }

        if (secondFragment == null) {
            secondFragment = new SecondFragment();
        }
        if (thirdFragment == null) {
            thirdFragment = new ThirdFragment();
        }
        if (fourthFragment == null) {
            fourthFragment = new FourthFragment();
        }


        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);
        fragments.add(fourthFragment);

    }

    public void switchFragment(int pos) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        if (!fragments.get(pos).isAdded()) {

            transaction.add(R.id.container, fragments.get(pos), fragments.get(pos).getClass().getSimpleName());
        }

        for (int i = 0; i < fragments.size(); i++) {

            if (i == pos) {
                transaction.show(fragments.get(pos));
            } else {
                transaction.hide(fragments.get(i));
            }

        }
        transaction.commit();

    }
/*
    public void onSelect(int index) {
        switchFragment(index);
    }*/

}
