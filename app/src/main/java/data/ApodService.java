package data;

/**
 * Created by Mario on 30/07/2016.
 */
import model.APOD;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApodService {
    @GET("planetary/apod?api_key=igfnGhta3Lxhd5imEDht3CWV28QPs7tJPRcnVQRa")
    Call<APOD> getTodayAPOD();

    @GET("planetary/APOD")
    Call<APOD> getTodayApodWithQuery(@Query("api_key") String apikey);
}
