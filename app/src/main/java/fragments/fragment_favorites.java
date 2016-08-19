package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unam.jigm.nasa2.R;
import adapters.AdapterItemList;
import model.ModelItemFavorites;
import sql.ItemDataSource;
import java.util.List;

/**
 * Created by Mario on 15/08/2016.
 */
public class fragment_favorites extends Fragment {
    private ListView listaVista;
   private ItemDataSource itemDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoriteslist, container, false);
        listaVista = (ListView) view.findViewById(R.id.listItemsfavorites);
        List<ModelItemFavorites> modelItemListfavorites = itemDataSource.getAllItems();
        listaVista.setAdapter(new AdapterItemList(getActivity(),modelItemListfavorites));
       return view;
    }
}