package beers.donderballer;


import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * LoginActivity logs in users using their facebook account.
 * This account can later on be used for eg. making facebook polls.
 *
 * @author tobemo
 * @version 1
 */

public class LoginActivity extends AppCompatActivity {

    //layout variables
    private TextView info;  //used for debugging
    private LoginButton loginButton;

    //called when login to Facebook attempted
    private CallbackManager callbackManager;

    //current Firebase instance
    private FirebaseAuth mAuth;

    //for debugging
    private String TAG = "LoginActivity";

    /**
     * onStart checks if a user is already logged in.
     * If so the app goes to the next activity without welcoming the user.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            nextActivity();
        }
    }

    /**
     * Attempts to login in Facebook and sets some layout elements.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //for facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();

        //for layout
        setContentView(R.layout.activity_login);
        info = findViewById(R.id.info);
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");

        //attempts to login in Facebook
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());    //first handle succes
                nextActivity();     //then go to next activity
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d(TAG, "facebook:onError", e);
                Toast.makeText(LoginActivity.this, "Something went wrong.",
                        Toast.LENGTH_SHORT).show();
            }

        });

    }

    /**
     * Handles Firebase login with credentials coming from Facebook.
     */
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();     //The user is now known and remebered, from Facebook, via Firebase.

                            welcomeToast(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            welcomeToast(null);
                        }

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * A toast welcoming the user: Welcome + user's name
     * @param user Firebase user
     */
    private void welcomeToast(FirebaseUser user)    {
        Log.d(TAG,"welcome toast");

        if(user != null) {
            Toast.makeText(LoginActivity.this, R.string.welcome_message + user.getDisplayName()+ ".", Toast.LENGTH_SHORT).show();
        }   else    {
            Toast.makeText(LoginActivity.this, R.string.error_retrieving_name, Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * This method is called when the app wants to go to the next activity.
     */
    private void nextActivity()    {
        Log.d(TAG,"going to next ativity");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}

