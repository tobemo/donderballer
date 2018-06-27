package beers.donderballer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GameActivity extends AppCompatActivity {

    //toolbar etc
    Toolbar toolbarAttending;
    Spinner spinnerAttending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarAttending = findViewById(R.id.toolbarGame);
        spinnerAttending = findViewById(R.id.spinnerGame);

        ArrayAdapter<String> gameAdapter = new ArrayAdapter<String>(GameActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activities));
        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAttending.setAdapter(gameAdapter);
        spinnerAttending.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Idealy each case references an ellemnt of the strings.activities-string-array. Switchcases however don't support arrays.
                //Maybe one day...
                switch (spinnerAttending.getSelectedItem().toString())    {
                    case "Ranking":
                        Intent intent = new Intent(GameActivity.this, RankingActivity.class);
                        startActivity(intent);
                        break;
                    case "Attending":
                        Intent intent2 = new Intent(GameActivity.this, AttendingActivity.class);
                        startActivity(intent2);
                        break;
                    case "Game":
                        //nothing
                        break;
                    case "Last Game":
                        Intent intent3 = new Intent(GameActivity.this, LastGameActivity.class);
                        startActivity(intent3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
