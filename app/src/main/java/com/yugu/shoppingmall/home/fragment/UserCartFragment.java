package com.yugu.shoppingmall.home.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yugu.shoppingmall.R;
import com.yugu.shoppingmall.base.BaseFragment;

/**
 * 个人中心Fragment
 */
public class UserCartFragment extends BaseFragment {

    private final static String TAG = UserCartFragment.class.getSimpleName();

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        Log.e(TAG, "个人中心页面的Fragment的UI被初始化了");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("个人中心");
        Log.e(TAG, "个人中心页面的Fragment的数据被初始化了");
    }

}