package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.unam.jigm.nasa2.R;
import java.util.List;
import model.ModelItemFavorites;

/**
 * Created by Mario on 15/08/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelItemFavorites>
{
    public AdapterItemList(Context context, List<ModelItemFavorites> objects) {super(context, 0, objects);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String myURL;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_favoritesitem,parent,false);
        }
        TextView txtfavorites_camfull= (TextView) convertView.findViewById(R.id.item_favorites_camfull);
        TextView txtfavorites_landdate = (TextView) convertView.findViewById(R.id.item_favorites_landdate);
        ImageView imgfavorites_imgphoto = (ImageView) convertView.findViewById(R.id.item_favorites_imgphoto);
        TextView txtfavorites_camname = (TextView) convertView.findViewById(R.id.item_favorites_camname);
        TextView txtfavorites_rovname = (TextView) convertView.findViewById(R.id.item_favorites_rovname);
        ModelItemFavorites modelItemfavorites=getItem(position);


        myURL=modelItemfavorites.imgphoto;
        Picasso.with(getContext()).load(myURL).into(imgfavorites_imgphoto);
        txtfavorites_camfull.setText(modelItemfavorites.camfull);
        txtfavorites_landdate.setText(modelItemfavorites.landdate);
        txtfavorites_camname.setText(modelItemfavorites.camname);
        txtfavorites_rovname.setText(modelItemfavorites.rovname);
        return convertView;
    }
}
