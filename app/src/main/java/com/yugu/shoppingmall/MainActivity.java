package com.yugu.shoppingmall;

import android.os.Bundle;

import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.yugu.shoppingmall.base.BaseFragment;
import com.yugu.shoppingmall.home.fragment.CommunityFragment;
import com.yugu.shoppingmall.home.fragment.HomeFragment;
import com.yugu.shoppingmall.home.fragment.ShoppingCartFragment;
import com.yugu.shoppingmall.home.fragment.TypeCartFragment;
import com.yugu.shoppingmall.home.fragment.UserCartFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    //装fragment的实例集合
    private ArrayList<BaseFragment> fragments;

    private int position = 0;

    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);

        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();
    }

    private void initListener() {
        rgMain.check(R.id.rb_home);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home: //首页
                        position = 0;
                        break;
                    case R.id.rb_type: //分类
                        position = 1;
                        break;
                    case R.id.rb_community: //发现
                        position = 2;
                        break;
                    case R.id.rb_cart: //购物车
                        position = 3;
                        break;
                    case R.id.rb_user: //个人中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置得到相应的Fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数: 上次显示的Fragment
                 * 第二个参数: 当前正要显示的Fragment
                 */

                switchFragment(tempFragment,baseFragment);
            }
        });
    }

    /**
     * 添加的时候按照顺序
     */
    private void initFragment(){
        fragments = new ArrayList<BaseFragment>();
        HomeFragment homeFragment = new HomeFragment();

        fragments.add(homeFragment);
        fragments.add(new TypeCartFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserCartFragment());
//        首次加载时，初始化HomeFragment
        if (tempFragment == null){
            BaseFragment baseFragment = getFragment(position);
            tempFragment = baseFragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,tempFragment).commit();
        }
    }

    /**
     * 根据位置得到对应的 Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     * @param fragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fragment,BaseFragment nextFragment){
        if (tempFragment != nextFragment){
            tempFragment = nextFragment;
            if (nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()){
                    //隐藏当前的Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}