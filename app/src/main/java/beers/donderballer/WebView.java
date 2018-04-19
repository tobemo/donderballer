package beers.donderballer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WebView extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    // This url contains the content of the article excluding web page's
    // header, footer, title, comments
    private static String url = "https://api.androidhive.info/facebook/firebase_analytics.html";
    private static String url2 = "https://touch.facebook.com/groups/620104731366208?_rdr";

    private android.webkit.WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(mAuth.getCurrentUser() != null) {
            Snackbar mySnackbar = Snackbar.make(getWindow().getDecorView().getRootView(),
                    user.getDisplayName(), Snackbar.LENGTH_LONG);
            mySnackbar.show();
        }

        webView = (android.webkit.WebView) findViewById(R.id.web_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // launching facebook comments activity
                Intent intent = new Intent(WebView.this, FbCommentsActivity.class);

                // passing the article url
                intent.putExtra("url", "https://www.androidhive.info/2016/06/android-firebase-integrate-analytics/");
                startActivity(intent);
            }
        });

        // loading web page
        webView.loadUrl(url);
    }





}
