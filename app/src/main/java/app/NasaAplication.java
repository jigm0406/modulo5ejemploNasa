package app;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Mario on 06/08/2016.
 */
public class NasaAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(this);
    }
}
