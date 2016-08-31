package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unam.jigm.nasa2.R;


import java.util.List;

//import RecycleviewFavorites;
import RecyclerviewFavorites.FavoritesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.ModelItemFavorites;
import sql.ItemDataSourceFavorites;

/**
 * Created by Mario on 19/08/2016.
 */
public class fragmentRFavorites extends Fragment{
    private ListView listaVista;
    private ItemDataSourceFavorites itemDataSourcefavorites;
    @BindView(R.id.favorite_recicle_list)
    RecyclerView favoritesReciclerList;

     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  itemDataSourcefavorites = new ItemDataSourceFavorites(getActivity());
         if(getArguments()!=null){}
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.favorites_list_main, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.VERTICAL);

        favoritesReciclerList.setLayoutManager(linearLayoutManager);
        final FavoritesAdapter favoriteadapter =new FavoritesAdapter();
        itemDataSourcefavorites=new ItemDataSourceFavorites(getActivity());
        List<ModelItemFavorites> favoriteModel = itemDataSourcefavorites.getAllItems();
        if (!favoriteModel.isEmpty())
        {
            favoriteadapter.setFavoritesList(favoriteModel);
            favoritesReciclerList.setAdapter(favoriteadapter);
            Log.d("tercero", "despues del adapter");
        }
        else
        {
            Snackbar.make(getView(),"No he guardado favoritos", Snackbar.LENGTH_SHORT).show();
        }
    }
}
