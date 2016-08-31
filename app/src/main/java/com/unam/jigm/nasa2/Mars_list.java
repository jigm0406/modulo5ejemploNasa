package com.unam.jigm.nasa2;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.fragmentRFavorites;
import fragments.fragment_listing;
import fragments.fragment_today;
import sql.ItemDataSource;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Mario on 05/08/2016.
 */
public class Mars_list extends AppCompatActivity  {
    @BindView(R.id.listing_toolbar) Toolbar toolbar;
    //@BindView(R.id.mars_rover_listing) RecyclerView recyclerView;
    @BindView(R.id.list_navigation_view) NavigationView navigationview;
    @BindView(R.id.listing_navigation_drawer) DrawerLayout drawerLayout;
    ItemDataSource itemDataSource;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenedor);
        ButterKnife.bind(this);
       /* try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.unam.jigm.nasa2",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
               md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
               }
          } catch (PackageManager.NameNotFoundException e) {
            } catch (NoSuchAlgorithmException e) {
        }*/
        setSupportActionBar(toolbar);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.mars_apod_item:
                        Snackbar.make(findViewById(android.R.id.content), "Today Apod", Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new fragment_today()).commit();
                        return true;
                    case R.id.mars_rover_item:
                        Snackbar.make(findViewById(android.R.id.content), "Mars Rover", Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new fragment_listing()).commit();
                        return true;
                    case R.id.favorite_item:
                        Snackbar.make(findViewById(android.R.id.content), "Favorite", Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new fragmentRFavorites()).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });

        getFBUserInfo();
    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name) {
    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
    }
};
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }



    private void getFBUserInfo() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback()
        {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try
                {
                    SimpleDraweeView userImage = (SimpleDraweeView) findViewById(R.id.item_imagephoto);
                    userImage.setImageURI("http://graph.facebook.com/"+ object.getString("id") +"/picture?type=large");
                    Log.d("facebook id : ", object.getString("id"));
                    TextView userName= (TextView) findViewById(R.id.item_cameranamefull);
                    userName.setText(object.getString("name"));
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }


}