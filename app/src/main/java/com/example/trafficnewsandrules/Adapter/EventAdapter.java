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
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.ui.main.EventDetailsActivity;
import com.example.trafficnewsandrules.url.Url;
;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> eventList;
    private Context context;

    public EventAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_view, viewGroup, false);
        return new ViewHolder(view);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Event events = eventList.get(i);
        String imgPath = Url.BASE_URL + "uploads/" + events.getImageName();
        StrictMode();
        try {
            URL url = new URL(imgPath);
            viewHolder.eventPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.eventName.setText(events.getName());
        viewHolder.eventDesc.setText(events.getDesc());

        viewHolder.eventPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EventDetailsActivity.class);
                Toast.makeText(context, "id " + events.get_id(), Toast.LENGTH_SHORT).show();
                Log.d("M act", "onClick: " + events.get_id());
                intent.putExtra("id",events.get_id());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView eventPhoto;
        TextView eventName, eventDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPhoto = itemView.findViewById(R.id.imgPhoto);
            eventName = itemView.findViewById(R.id.eventName);
            eventDesc = itemView.findViewById(R.id.eventDesc);
        }
    }
}
