package com.unam.jigm.nasa2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.ApodService;
import data.Data;
import model.APOD;
import model.ModelMarRoverPhotos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    //TextView Title,Date,copyRigth,media_type,service_version,explanation ;
    //ImageView image;
    @BindView(R.id.img_hdurl) ImageView image;
    @BindView(R.id.txt_title) TextView Title;
    @BindView(R.id.txt_date) TextView Date;
    @BindView(R.id.txt_explanation) TextView explanation;
    @BindView(R.id.id_btn) TextView btn;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //image=(ImageView) findViewById(R.id.img_hdurl);
        //Title =(TextView) findViewById(R.id.txt_title);
        //Date  =(TextView) findViewById(R.id.txt_date);
         //explanation = (TextView) findViewById(R.id.txt_explanation);

        //btn.setText("Hello!");
        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<ModelMarRoverPhotos> callApodService = apodService.getMarRover(1000,"igfnGhta3Lxhd5imEDht3CWV28QPs7tJPRcnVQRa");

        callApodService.enqueue(new Callback<ModelMarRoverPhotos>() {
            @Override
            public void onResponse(Call<ModelMarRoverPhotos> call, Response<ModelMarRoverPhotos> response) {
               //Title.setText(response.body().getTitle().toString());
               //Date.setText(response.body().getDate().toString());
               //explanation.setText(response.body().getExplanation().toString());
                Picasso.with(getApplicationContext()).load(response.body().getPhotos().get(0).getImgSrc().toString()).into(image);
                Log.d("photo: ",response.body().getPhotos().get(0).getImgSrc().toString());
              }
            @Override
            public void onFailure(Call<ModelMarRoverPhotos> call, Throwable t) {
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Mars_list.class));
            }
        });
    }

}
