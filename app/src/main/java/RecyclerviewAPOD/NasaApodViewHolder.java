package RecyclerviewAPOD;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.unam.jigm.nasa2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Photo;

/**
 * Created by Mario on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_apod_image)
   // ImageView itemApodImage;
            //por lo de fresco
     SimpleDraweeView itemApodImage;
    private NasaApodAdapter.OnItemClickListener onItemClickListener;
    private Photo photo;
    @BindView(R.id.item_apod_text)
    TextView itemApodText;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setItemClick(Photo photo,NasaApodAdapter.OnItemClickListener onItemClickListener){
        this.photo=photo;
        this.onItemClickListener=onItemClickListener;
    }

    @OnClick(R.id.item_apod_image)
    public void onViewClick(View view){
        if(onItemClickListener != null)
            onItemClickListener.onItemClick(photo);
    }
}
