package com.unam.jigm.nasa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import RecyclerviewAPOD.NasaApodAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.ApodService;
import data.Data;
import model.APOD;
import model.ModelMarRoverPhotos;
import model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.squareup.picasso.Picasso;


/**
 * Created by Mario on 05/08/2016.
 */
public class Mars_list extends AppCompatActivity  {

    @BindView(R.id.mars_rover_listing) RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_marroverphotos);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        apodService.getMarRover(400, BuildConfig.API_KEY).enqueue(new Callback<ModelMarRoverPhotos>() {
            @Override
            public void onResponse(Call<ModelMarRoverPhotos> call, Response<ModelMarRoverPhotos> response) {
                recyclerView.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
            }

            @Override
            public void onFailure(Call<ModelMarRoverPhotos> call, Throwable t) {

            }
        });

    }
}