package com.example.trafficnewsandrules.ui.main;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.trafficnewsandrules.Adapter.SliderAdapter;
import com.example.trafficnewsandrules.Models.SliderModel;
import com.example.trafficnewsandrules.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private Fragment home;
    private ViewPager bannersliderviewpager;
    private List<SliderModel> sliderModelList;
    private int currentpage = 2;
    private Timer timer;
    private final long DELEY_TIME = 3000;
    private final long PERIOD_TIME = 3000;
    public static final String BASE_URL = "http://10.0.2.2:3000";
    LinearLayout dprofile, dviewevent, dnews, dlocation, dreport, daddevent;


    // product layout ....
    private RecyclerView recyclerView;

    //List<Product> productList = new ArrayList<>();

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        dviewevent = view.findViewById(R.id.dviewevent);
        dnews = view.findViewById(R.id.dnews);
        dlocation = view.findViewById(R.id.dlocation);
        dreport = view.findViewById(R.id.dreport);
        daddevent = view.findViewById(R.id.daddevent);


        dviewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Events.class);
                startActivity(intent);
            }
        });

        dnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsView.class);
                startActivity(intent);
            }
        });

        dlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        dreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReportView.class);
                startActivity(intent);
            }
        });

        daddevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEventActivity.class);
                startActivity(intent);
            }
        });



        //banner slider implementation....
        bannersliderviewpager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.slide3,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide1,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.silide2,"#8F00FF"));

        sliderModelList.add(new SliderModel(R.drawable.slide4,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide5,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide6,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.silide2,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide1,"#8F00FF"));

        sliderModelList.add(new SliderModel(R.drawable.slide3,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide4,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide5,"#8F00FF"));
        sliderModelList.add(new SliderModel(R.drawable.slide6,"#8F00FF"));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannersliderviewpager.setAdapter(sliderAdapter);
        bannersliderviewpager.setClipToPadding(false);
        bannersliderviewpager.setPageMargin(20);

        bannersliderviewpager.setCurrentItem(2);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentpage = i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE){
                    pageLoope();
                }


            }
        };
        bannersliderviewpager.addOnPageChangeListener(onPageChangeListener);
        startbannerslideshow();
        bannersliderviewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLoope();
                stopbannerSlideshow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    startbannerslideshow();
                }
                return false;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);



        return view;

    }
    private void pageLoope(){

        if (currentpage ==sliderModelList.size() -2  ){
            currentpage = 2;
            bannersliderviewpager.setCurrentItem(currentpage, false);
        }
        if (currentpage == 1   ){
            currentpage = sliderModelList.size()-3;
            bannersliderviewpager.setCurrentItem(currentpage, false);
        }

    }
    private void startbannerslideshow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentpage >= sliderModelList.size()){
                    currentpage = 1;
                }
                bannersliderviewpager.setCurrentItem(currentpage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELEY_TIME,PERIOD_TIME);
    }
    private void stopbannerSlideshow(){
        timer.cancel();

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}

