package com.example.trafficnewsandrules.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.trafficnewsandrules.R;

public class NumReport extends AppCompatActivity {
    EditText  sharec;
    Button sharebutton;
    TextView total;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String EventName = "nameKey";

    private NotificationManagerCompat notificationManagerCompat;
    private Context context;

    private static final String TAG = "MyActivity";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_report);

        total = findViewById(R.id.total);
        sharec = findViewById(R.id.shareeventmoney);

        sharebutton = findViewById(R.id.sharebutton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String previous = sharedpreferences.getString(EventName,"");
        total.setText(previous);

        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = sharec.getText().toString();

                String previousa = sharedpreferences.getString(EventName,"");
//
                int currentmoney = Integer.parseInt(name);
                int prevmoney = Integer.parseInt(previousa);
//
            int tot = currentmoney + prevmoney;

                String tota = String.valueOf(tot);



              //   int finalamount =  + prevmoney;
                //  String store = String.valueOf(finalamount);

         //
                //       -String tota = String.valueOf(currentmoney);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(EventName, tota);

                editor.commit();







            }
        });
    }



}

