package com.unam.jigm.nasa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import data.ApodService;
import data.Data;
import model.APOD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    TextView Title,Date,copyRigth,media_type,service_version,explanation ;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView) findViewById(R.id.img_hdurl);
        Title =(TextView) findViewById(R.id.txt_title);
        Date  =(TextView) findViewById(R.id.txt_date);
         explanation = (TextView) findViewById(R.id.txt_explanation);


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<APOD> callApodService = apodService.getTodayAPOD();

        callApodService.enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
               Title.setText(response.body().getTitle().toString());
               Date.setText(response.body().getDate().toString());
               explanation.setText(response.body().getExplanation().toString());
                Picasso.with(getApplicationContext()).load(response.body().getUrl()).into(image);
                Log.d("Titulo: ",response.body().getTitle());
              }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {

            }
        });

    }
}
