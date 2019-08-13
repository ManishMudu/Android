package com.example.trafficnewsandrules.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.trafficnewsandrules.R;

public class EventFragment extends Fragment {

    Button btnaddevents,btnviewevents,btnevent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.event_fragment, container, false);


        btnaddevents = root.findViewById(R.id.btnaddevents);
        btnviewevents = root.findViewById(R.id.btnviewevents);
        btnevent = root.findViewById(R.id.btnevents);

        btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEventActivity.class);
                startActivity(intent);
            }
        });


        btnaddevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsView.class);
                startActivity(intent);
            }
        });

        btnviewevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Events.class);
                startActivity(intent);
            }
        });

        return root;
    }
}



