package data;

import com.unam.jigm.nasa2.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mario on 30/07/2016.
 */
public class Data {
    public static Retrofit getRetrofitInstance(){

    return new Retrofit.Builder ().baseUrl(BuildConfig.URL)
        .addConverterFactory(GsonConverterFactory.create())

    .build();
    }
}
