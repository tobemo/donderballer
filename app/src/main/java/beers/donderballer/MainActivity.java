package beers.donderballer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView user;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (TextView)findViewById(R.id.main_texrt);
        info = (TextView)findViewById(R.id.main_texrt2);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            user.setText(currentUser.getDisplayName());
            info.setText(currentUser.getDisplayName() + "\n" + currentUser.getEmail() + "\n" + currentUser.getProviderId() + "\n" + currentUser.getPhoneNumber());
        }


    }
}
