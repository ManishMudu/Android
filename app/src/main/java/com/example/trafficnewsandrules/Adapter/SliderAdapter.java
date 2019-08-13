package com.example.trafficnewsandrules.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.trafficnewsandrules.Models.SliderModel;
import com.example.trafficnewsandrules.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {


    private List<SliderModel> sliderModleList;

    public SliderAdapter(List<SliderModel> sliderModleList) {
        this.sliderModleList = sliderModleList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.sliderlayout, container, false);
        ConstraintLayout bannercontainer = view.findViewById(R.id.banner_container);
        bannercontainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModleList
                .get(position).getBackgroundcolor())));
        ImageView banner = view.findViewById(R.id.banner_slide);
        banner.setImageResource(sliderModleList.get(position).getBanner());
        container.addView(view,0);
        return view;
    }



    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return sliderModleList.size();
    }
}

