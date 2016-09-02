package fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.unam.jigm.nasa2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.ApodService;
import data.Data;
import model.APOD;
import model.ModelMarRoverPhotos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.unam.jigm.nasa2.R.menu.apod_menu;

/**
 * Created by Mario on 12/08/2016.
 */
public class fragment_today  extends Fragment {
    @BindView(R.id.img_hdurl) ImageView image;
    @BindView(R.id.txt_title) TextView Title;
    @BindView(R.id.txt_date) TextView Date;
    @BindView(R.id.txt_explanation) TextView explanation;
    String imageURL;
    //para lo de la escala
    Matrix matrix = new Matrix();
    Float scale = 1f;
    ScaleGestureDetector sgd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //para que se muestre el menu
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todayxml,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<APOD> callApodService = apodService.getTodayAPOD();

        callApodService.enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
                Title.setText(response.body().getTitle().toString());
                Date.setText(response.body().getDate().toString());
                explanation.setText(response.body().getExplanation().toString());
                Picasso.with(getActivity()).load(response.body().getHdurl()).into(image);
                //para enviar lo que se va a compartir
                imageURL=response.body().getHdurl().toString();

            }
            @Override
            public void onFailure(Call<APOD> call, Throwable t) {
            }
        });
    }

    //para atrapar el clic del meu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_today_apod:
                shareText("Diplomado UNAM, today:  " + imageURL);
                //Log.d("hola","como mars rover");
                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }

    //para compartir
    private void shareText(String text){
        Intent shareIntent= new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(shareIntent.createChooser(shareIntent,"Compartir"));
    }

    //para el menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
