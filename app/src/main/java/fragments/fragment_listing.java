package fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.unam.jigm.nasa2.BuildConfig;
import com.unam.jigm.nasa2.MainSecundario;
import com.unam.jigm.nasa2.Mars_list;
import com.unam.jigm.nasa2.R;

import RecyclerviewAPOD.NasaApodAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import data.ApodService;
import data.Data;
import model.ModelMarRoverPhotos;
import model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mario on 12/08/2016.
 */
public class fragment_listing extends Fragment {
    @BindView(R.id.mars_rover_listing)   RecyclerView recyclerView;
    String imageURL;
//para que se vea el menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter=new NasaApodAdapter();

        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Photo photo) {
                Intent intent= new Intent(getActivity(),MainSecundario.class);
                intent.putExtra("paramphoto", photo);
                startActivity(intent);
            }
        });

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        apodService.getMarRover(400, BuildConfig.API_KEY).enqueue(new Callback<ModelMarRoverPhotos>() {
            @Override
            public void onResponse(Call<ModelMarRoverPhotos> call, Response<ModelMarRoverPhotos> response) {
                //recyclerView.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                imageURL=response.body().getPhotos().get(0).getImgSrc().toString();
                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                recyclerView.setAdapter(nasaApodAdapter);
            }

            @Override
            public void onFailure(Call<ModelMarRoverPhotos> call, Throwable t) {

            }
        });
    }
//para atrapar el clic del meu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_rover_mars:
                shareText("Diplomado UNAM, mars rover:  " + imageURL);
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
        inflater.inflate(R.menu.rover_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
