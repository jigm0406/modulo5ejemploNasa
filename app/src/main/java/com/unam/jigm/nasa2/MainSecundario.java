package com.unam.jigm.nasa2;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.ModelItemFavorites;
import model.Photo;
import sql.ItemDataSource;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Mario on 07/08/2016.
 */
public class MainSecundario extends AppCompatActivity {
    @BindView(R.id.item_imagephoto) SimpleDraweeView image;
    @BindView(R.id.item_cameranamefull) TextView Title;
    @BindView(R.id.item_apod_cameraname) TextView CName;
    @BindView(R.id.item_apod_rovername) TextView RName;
    @BindView(R.id.item_apod_landdate) TextView LName;
    @BindView(R.id.listing_toolbar)    Toolbar toolbarsec;
    String imgStr;
    ItemDataSource itemDataSource;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_secundario);
        ButterKnife.bind(this);
        itemDataSource = new ItemDataSource(getApplicationContext());
        setSupportActionBar(toolbarsec);
        Photo ophoto = (Photo) getIntent().getExtras().getSerializable("paramphoto");
        Uri uri = Uri.parse(ophoto.getImgSrc());
        imgStr=ophoto.getImgSrc().toString();
        image.setImageURI(uri);
        LName.setText(ophoto.getRover().getLandingDate());
        CName.setText(ophoto.getCamera().getName());
        RName.setText(ophoto.getRover().getName());
        Title.setText(ophoto.getCamera().getFullName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_rover_favorites:
              //procedimiento guardar
                String sfullnamee=Title.getText().toString();
                String slandname=LName.getText().toString();
                String scamname=CName.getText().toString();
                String srovname=RName.getText().toString();
                Log.d("secundario","prueba agregar favoritos");
                ModelItemFavorites itemFavorites = new ModelItemFavorites( imgStr, sfullnamee,  slandname, scamname, srovname);;
                itemFavorites.imgphoto=imgStr;
                itemFavorites.camfull=sfullnamee;
                itemFavorites.landdate=slandname;
                itemFavorites.camname=scamname;
                itemFavorites.rovname=srovname;
                itemDataSource.saveItemFavorites(itemFavorites);
                Snackbar.make(findViewById(android.R.id.content), "Se agrego a favoritos", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.detalle_menu, menu);
       return true;
    }
}
