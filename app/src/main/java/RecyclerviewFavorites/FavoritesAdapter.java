package RecyclerviewFavorites;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.unam.jigm.nasa2.R;
import java.util.List;
import model.ModelItemFavorites;

/**
 * Created by Mario on 19/08/2016.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder>
{
    private List<ModelItemFavorites> favoritesList;
    public FavoritesAdapter(){}
    public FavoritesAdapter(List<ModelItemFavorites> favoritesList)
    {
        this.favoritesList=favoritesList;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item_main,parent,false));

    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holderfavorites, int position) {
        ModelItemFavorites modelitemfavorites = favoritesList.get(position);
        holderfavorites.itemfavorites_camfull.setText(modelitemfavorites.camfull);
        holderfavorites.itemfavorites_landdate.setText(modelitemfavorites.landdate);
        holderfavorites.itemfavorites_camname.setText(modelitemfavorites.camname);
        holderfavorites.itemfavorites_rovname.setText(modelitemfavorites.rovname);
        holderfavorites.itemfavorites_imgphoto.setImageURI(modelitemfavorites.imgphoto);
      }

    public void setFavoritesList(List<ModelItemFavorites> favoritesList)
    {
        this.favoritesList=favoritesList;
    }

    @Override
    public int getItemCount() {
        return favoritesList != null? favoritesList.size():0;
    }
}