package com.example.trafficnewsandrules.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficnewsandrules.Models.Event;
import com.example.trafficnewsandrules.Models.News;
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.ui.main.EventDetailsActivity;
import com.example.trafficnewsandrules.ui.main.NewsDetailsActivity;
import com.example.trafficnewsandrules.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;
    private Context context;

    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_view, viewGroup, false);
        return new ViewHolder(view);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final News news = newsList.get(i);
        String imgPath = Url.BASE_URL + "uploads/" + news.getImageName();
        StrictMode();
        try {
            URL url = new URL(imgPath);
            viewHolder.newsPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.newsName.setText(news.getTitle());
        viewHolder.newsDesc.setText(news.getDesc());

        viewHolder.newsPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, NewsDetailsActivity.class);
                Toast.makeText(context, "id " + news.get_id(), Toast.LENGTH_SHORT).show();
                Log.d("M act", "onClick: " + news.get_id());
                intent.putExtra("id",news.get_id());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsPhoto;
        TextView newsName, newsDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsPhoto = itemView.findViewById(R.id.imgPhoto);
            newsName = itemView.findViewById(R.id.newsName);
            newsDesc = itemView.findViewById(R.id.newsDesc);
        }
    }
}
