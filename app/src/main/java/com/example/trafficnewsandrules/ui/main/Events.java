package com.example.trafficnewsandrules.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficnewsandrules.Adapter.EventAdapter;
import com.example.trafficnewsandrules.Interfaces.EventInterface;
import com.example.trafficnewsandrules.Models.Event;
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events extends AppCompatActivity {

    private RecyclerView recyclerView;
    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.recyclerView);

        getEvents();

    }

    private void getEvents() {
        EventInterface events = Url.getInstance().create(EventInterface.class);

        Call<List<Event>> listCall = events.getEvents(Url.Cookie);

        listCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Events.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Event> EventsList = response.body();
                EventAdapter contactsAdapter = new EventAdapter(EventsList, Events.this);
                recyclerView.setAdapter(contactsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Events.this));

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(Events.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}


