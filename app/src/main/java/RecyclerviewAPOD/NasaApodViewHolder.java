package RecyclerviewAPOD;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unam.jigm.nasa2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mario on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_apod_image)
    ImageView itemApodImage;
    @BindView(R.id.item_apod_text)
    TextView itemApodText;


    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
