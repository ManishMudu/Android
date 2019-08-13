package com.example.trafficnewsandrules.ui.main;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trafficnewsandrules.Interfaces.NewsInterface;
import com.example.trafficnewsandrules.Models.News;
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.url.Url;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView newsTitle, newsDescription;
    ImageView newsPhoto;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        newsTitle = findViewById(R.id.newstitle);
        newsDescription = findViewById(R.id.newsdesc);
        newsPhoto = findViewById(R.id.newsPhototest);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String id = bundle.getString("id");


            getNewsDetail(id);

        }
    }

    private void loadData(String id) {

    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void getNewsDetail (String id) {
        NewsInterface news = Url.getInstance().create(NewsInterface.class);

        Call<com.example.trafficnewsandrules.Models.News> listCall = news.getNewsDetail(Url.Cookie, id);

        listCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<com.example.trafficnewsandrules.Models.News> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(NewsDetailsActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                String imgUrl = "http://10.0.2.2:3000/uploads/"+ response.body().getImageName();

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try {
                    URL url = new URL(imgUrl);
                    newsPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                newsTitle.setText(response.body().getTitle());
                newsDescription.setText(response.body().getDesc());

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }

}
