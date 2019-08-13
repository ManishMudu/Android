package com.example.trafficnewsandrules.ui.main;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.example.trafficnewsandrules.BLL.RegisterBLL;
import com.example.trafficnewsandrules.Interfaces.UserInterface;
import com.example.trafficnewsandrules.Models.User;
import com.example.trafficnewsandrules.R;
import com.example.trafficnewsandrules.StrictMode.StrictMod;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email,username,contact, citizenship, password;
    Button register1, login1;
    private boolean isSuccess = false;
    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor proximitysensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent.values[0] < proximitysensor.getMaximumRange()) {


                    Toast.makeText(RegisterActivity.this,"Application Closed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Far",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,proximitysensor,2*1000*1000);



        name = findViewById(R.id.regfullname);
        email = findViewById(R.id.regemail);
        username = findViewById(R.id.regusername);
        contact = findViewById(R.id.regcontact);
        citizenship = findViewById(R.id.regcitizenship);
        password = findViewById(R.id.regpassword);

        register1 = findViewById(R.id.btnregister);

        login1 = findViewById(R.id.btnlogin);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);

            }
        });

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMod.StrictMode();

                User registerBLL = new User(
                        name.getText().toString(),
                        email.getText().toString(),
                        username.getText().toString(),
                        contact.getText().toString(),
                        citizenship.getText().toString(),
                        password.getText().toString()
                 );

                RegisterBLL bll = new RegisterBLL();
                if(bll.registerUser(registerBLL)){
                    name.setText("");
                    email.setText("");
                    username.setText("");
                    contact.setText("");
                    citizenship.setText("");
                    password.setText("");
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });








//        Log.d("VAL", "BTNCLICKED ");
//
//        if (nullValidation()) {
//            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            UserInterface userInterface = retrofit.create(UserInterface.class);
//            final User user = new User(
//                    "",
//                    name.getText().toString().trim(),
//                    email.getText().toString().trim(),
//                    username.getText().toString().trim(),
//                    contact.getText().toString().trim(),
//                    citizenship.getText().toString().trim(),
//                    password.getText().toString().trim());
//            Call<ResponseBody> call = userInterface.userRegistration(user);
//
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                    Log.d("VAL", "success ");
//
//                    if(response.isSuccessful()) {
//                        Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
//
//                        Log.d("VAL", "success response ");
//
//                        name.setText("");
//                        email.setText("");
//                        username.setText("");
//                        contact.setText("");
//                        citizenship.setText("");
//                        password.setText("");
//                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                    }else {
//                        try{
//                            Log.d("VAL", response.toString());
//
//                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    Log.d("VAL", t.getLocalizedMessage());
//
//                }
//            });
//
// }

    }
    public boolean nullValidation(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(username.getText().toString())){
            username.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(contact.getText().toString())){
            contact.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(citizenship.getText().toString())){
            citizenship.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required Field");
            return false;
        }
        return true;
    }


}

