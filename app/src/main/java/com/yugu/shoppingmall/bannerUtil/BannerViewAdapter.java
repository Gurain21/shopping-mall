package com.yugu.shoppingmall.bannerUtil;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


public class BannerViewAdapter extends BannerAdapter<InfoBean, BannerViewAdapter.BannerViewHolder> {
    private Fragment fragment;

    public BannerViewAdapter(List<InfoBean> banners, Fragment fragment) {
        super(banners);
        this.fragment = fragment;
    }

    @Override
    public BannerViewAdapter.BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        //setScaleType对图片进行大小处理 CENTER_CROP对原图居中显示后进行等比放缩处理，使最小边等于ImageView的相应边
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return new BannerViewHolder(imageView);

    }
    //用于绑定图片资源文件
    @Override
    public void onBindView(BannerViewAdapter.BannerViewHolder holder, InfoBean data, int position, int size) {
        holder.imageView.setImageResource(data.picture);
    }
    //ViewHolder主要用于容纳view视图
    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}