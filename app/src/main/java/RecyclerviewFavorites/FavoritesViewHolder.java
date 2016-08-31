package RecyclerviewFavorites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.unam.jigm.nasa2.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mario on 19/08/2016.
 */
public class FavoritesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_favorites_imgphoto) SimpleDraweeView itemfavorites_imgphoto;
    @BindView(R.id.item_favorites_camfull) TextView itemfavorites_camfull;
    @BindView(R.id.item_favorites_landdate) TextView itemfavorites_landdate;
    @BindView(R.id.item_favorites_camname) TextView itemfavorites_camname;
    @BindView(R.id.item_favorites_rovname) TextView itemfavorites_rovname;

    public FavoritesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
