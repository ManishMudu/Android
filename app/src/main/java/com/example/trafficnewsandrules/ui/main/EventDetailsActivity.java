package com.example.trafficnewsandrules.ui.main;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficnewsandrules.Interfaces.EventInterface;
import com.example.trafficnewsandrules.Models.Event;
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.url.Url;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity {

    TextView eventName, eventType, eventDescription, eventLocation;
    ImageView eventPhoto;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventName = findViewById(R.id.eventName);
        eventType = findViewById(R.id.eventType);
        eventDescription = findViewById(R.id.eventDescription);
        eventLocation = findViewById(R.id.eventLocation);
        eventPhoto = findViewById(R.id.eventPhototest);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String id = bundle.getString("id");


            getEventsDetail(id);

        }
    }

    private void loadData(String id) {

    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void getEventsDetail (String id) {
        EventInterface events = Url.getInstance().create(EventInterface.class);

        Call<Event> listCall = events.getEventsDetail(Url.Cookie, id);

        listCall.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(EventDetailsActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                String imgUrl = "http://10.0.2.2:3000/uploads/"+ response.body().getImageName();

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try {
                    URL url = new URL(imgUrl);
                    eventPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                eventName.setText(response.body().getName());
                eventType.setText(response.body().getEventtype());
                eventDescription.setText(response.body().getDesc());
                eventLocation.setText(response.body().getLocation());
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });

    }

}
