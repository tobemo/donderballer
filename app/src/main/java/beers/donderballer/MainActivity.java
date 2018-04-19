package beers.donderballer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user != null) {
            setTitle(user.getDisplayName());
        }


    }

    //TODO: if there is no game running display "Start Game"
    //TODO: when starting game auto select present players who said they were coming, see AttendingActivity
    //TODO: in AttendingActivity: if no future game planned, plan one + facebook post

    public void ranking (View view) {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    public void game (View view)    {
        Intent intent = new Intent(this, LastGameActivity.class);
        startActivity(intent);
    }

    public void lastGame(View view) {
        Intent intent = new Intent(this, LastGameActivity.class);
        startActivity(intent);
    }

    public void attending (View view)   {
        Intent intent = new Intent(this, AttendingActivity.class);
        startActivity(intent);
    }

}
