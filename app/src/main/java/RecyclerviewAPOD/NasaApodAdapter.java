package RecyclerviewAPOD;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.unam.jigm.nasa2.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import model.APOD;
import model.Photo;

/**
 * Created by Mario on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder>{
    //se crea una lista dl tipo de datos apod
     private List<Photo> marsPhotos;
    public NasaApodAdapter(List<Photo> marsPhotos){this.marsPhotos=marsPhotos;}

    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = marsPhotos.get(position);
       holder.itemApodText.setText(photo.getCamera().getFullName());
        Picasso.with(holder.itemApodImage.getContext())
                .load(photo.getImgSrc())
                .into(holder.itemApodImage);
    }

    @Override
    public int getItemCount() {
        return marsPhotos != null? marsPhotos.size():0;
    }
}
