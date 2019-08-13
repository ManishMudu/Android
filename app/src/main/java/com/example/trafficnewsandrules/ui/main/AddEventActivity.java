package com.example.trafficnewsandrules.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trafficnewsandrules.Interfaces.EventInterface;
import com.example.trafficnewsandrules.Models.Event;
import com.example.trafficnewsandrules.Models.ImageUploads;
import com.example.trafficnewsandrules.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEventActivity extends AppCompatActivity {

    public EditText name, eventtype, desc, location;
    ImageView imageView;
    public Button add, confirm;
    TextView imageName;
    Uri uri;
    Bitmap bitmap;
    Retrofit retrofit;
    EventInterface itemInterface;
    private static final int PICK_IMAGE = 1;

    public  String BASE_URL = "http://10.0.2.2:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        name = findViewById(R.id.itemName);
        eventtype = findViewById(R.id.itemPrice);
        imageView = findViewById(R.id.imgPhoto);
        desc = findViewById(R.id.itemDescription);
        location = findViewById(R.id.itemlocation);

        imageName = findViewById(R.id.tvImagename);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });


        confirm = findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(bitmap);
                Toast.makeText(AddEventActivity.this, "Image Confirmed", Toast.LENGTH_SHORT).show();
            }
        });

        add = findViewById(R.id.btnAddItem);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }

    private void BrowseImage() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
    }

    private void addImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(this.getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            RequestBody requestBody = RequestBody.
                    create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.
                    createFormData("imageFile", file.getName(), requestBody);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            itemInterface = retrofit.create(EventInterface.class);
            Call<ImageUploads> imgCall = itemInterface.uploadImage(body);
            imgCall.enqueue(new Callback<ImageUploads>() {
                @Override
                public void onResponse(Call<ImageUploads> call, Response<ImageUploads> response) {
                    imageName.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<ImageUploads> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap
                        (getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEvent(){
        if (nullValidation()) {

            String sname = name.getText().toString();
            String seventtype = eventtype.getText().toString();
            String simageName = imageName.getText().toString();
            String sdesc = desc.getText().toString();
            String slocation = location.getText().toString();

            Event event = new Event(sname,seventtype, simageName, sdesc, slocation);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EventInterface itemInterface = retrofit.create(EventInterface.class);

            Call<Void> call = (Call<Void>) itemInterface.addEvent(event);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("VAL", "success ");

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "EVENT ADDED", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

//                        name.setText("");
//                        price.setText("");
//                        description.setText("");
//                        imagename.setText("");
//                        imageView.setImageResource(R.drawable.noimage);
                        //             startActivity(new Intent(getApplicationContext(), ShowEventActivity.class));
                    }else {
                        try{
                            Log.d("VAL", response.toString());

                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }
    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(eventtype.getText().toString())){
            eventtype.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(desc.getText().toString())){

            desc.setError("Required Field");
            return false;
        }

        else if (TextUtils.isEmpty(location.getText().toString())){

            location.setError("Required Field");
            return false;
        }

        return true;
    }


}