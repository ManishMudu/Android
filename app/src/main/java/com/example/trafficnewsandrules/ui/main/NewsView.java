package com.example.trafficnewsandrules.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trafficnewsandrules.Adapter.NewsAdapter;
import com.example.trafficnewsandrules.Interfaces.NewsInterface;
import com.example.trafficnewsandrules.Models.News;
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsView extends AppCompatActivity {

    private RecyclerView recyclerView;
    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);

        getNews();

    }

    private void getNews() {
        NewsInterface news = Url.getInstance().create(NewsInterface.class);

        Call<List<News>> listCall = news.getNews(Url.Cookie);

        listCall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(NewsView.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<News> NewsList = response.body();
                NewsAdapter contactsAdapter = new NewsAdapter(NewsList, NewsView.this);
                recyclerView.setAdapter(contactsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(NewsView.this));

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(NewsView.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
