package com.example.trafficnewsandrules.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.trafficnewsandrules.Interfaces.UserInterface;
import com.example.trafficnewsandrules.Models.User;
import com.example.trafficnewsandrules.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Profile extends Fragment {
    TextView name1, email1, contact1;
    EditText name, email, username, contact, citizenship, password;
    Bundle bundle = getArguments();
    Button update;
    public String BASE_URL = "http://10.0.2.2:3000/";
    String uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profile, container, false);

        name1 = v.findViewById(R.id.name1);
        contact1 = v.findViewById(R.id.contact1);
        email1 = v.findViewById(R.id.email1);
        name = v.findViewById(R.id.name2);
        email = v.findViewById(R.id.email2);
        username = v.findViewById(R.id.username2);
        contact = v.findViewById(R.id.contact);
        citizenship = v.findViewById(R.id.citizenship);
        password = v.findViewById(R.id.password);
        update = v.findViewById(R.id.btnupdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        uid = getArguments().getString("userid");

        loadProfile();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (bundle != null) {
            contact = getView().findViewById(R.id.contact);
            contact.setText(bundle.getString("contact", "err"));
        }
    }
    private void loadProfile(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        Call<User> userCall = userInterface.getUserProfile(uid);


        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                name1.setText(response.body().getName());
                contact1.setText(response.body().getContact());
                email1.setText(response.body().getEmail());
                name.setText(response.body().getName());
                email.setText(response.body().getEmail());
                username.setText(response.body().getUsername());
                contact.setText(response.body().getContact());
                citizenship.setText(response.body().getCitizenship());
                password.setText(response.body().getPassword());
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        }

        private void updateUser(){
        }


    }



