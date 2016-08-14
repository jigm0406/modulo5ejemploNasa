package login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.unam.jigm.nasa2.Mars_list;
import com.unam.jigm.nasa2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mario on 13/08/2016.
 */
public class FBLoginActivity extends AppCompatActivity  implements FacebookCallback<LoginResult>{
    CallbackManager callbackManager;
    @BindView(R.id.fb_login_button) LoginButton loginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fblogin);
        ButterKnife.bind(this);
        //cual va a ser el resultado que facebook nos regresa
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager,this);

        if(AccessToken.getCurrentAccessToken()!= null)
        {
            startActivity(new Intent(FBLoginActivity.this,Mars_list.class));
            Snackbar.make(findViewById(android.R.id.content),"Login",Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
startActivity(new Intent(FBLoginActivity.this,Mars_list.class));
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
