package com.unam.jigm.nasa2;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Photo;

/**
 * Created by Mario on 07/08/2016.
 */
public class MainSecundario extends AppCompatActivity {
    @BindView(R.id.item_imagephoto) SimpleDraweeView image;
    @BindView(R.id.item_cameranamefull) TextView Title;
    @BindView(R.id.item_apod_cameraname) TextView CName;
    @BindView(R.id.item_apod_rovername) TextView RName;
    @BindView(R.id.item_apod_landdate) TextView LName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_secundario);
        ButterKnife.bind(this);
        Photo ophoto = (Photo) getIntent().getExtras().getSerializable("paramphoto");
        Uri uri = Uri.parse(ophoto.getImgSrc());
        image.setImageURI(uri);
        LName.setText(ophoto.getRover().getLandingDate());
        CName.setText(ophoto.getCamera().getName());
        RName.setText(ophoto.getRover().getName());
        Title.setText(ophoto.getCamera().getFullName());
    }
}
