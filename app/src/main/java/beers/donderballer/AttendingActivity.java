package beers.donderballer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AttendingActivity extends AppCompatActivity {

    //toolbar etc
    Toolbar toolbarAttending;
    Spinner spinnerAttending;

    private String TAG = "AttendingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atending);

        toolbarAttending = findViewById(R.id.toolbarAttending);
        spinnerAttending = findViewById(R.id.spinnerAttending);

        getIndex(spinnerAttending,"Attending");

        ArrayAdapter<String> attendingAdapter = new ArrayAdapter<String>(AttendingActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activities));
        Log.d(TAG,"attending adapter:" + attendingAdapter);
        attendingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAttending.setAdapter(attendingAdapter);
        spinnerAttending.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Idealy each case references an ellemnt of the strings.activities-string-array. Switchcases however don't support arrays.
                //Maybe one day...
                switch (spinnerAttending.getSelectedItem().toString())    {
                    case "Ranking":
                        Intent intent = new Intent(AttendingActivity.this, RankingActivity.class);
                        startActivity(intent);
                        break;
                    case "Attending":
                        //nothing
                        break;
                    case "Game":
                        Intent intent2 = new Intent(AttendingActivity.this, GameActivity.class);
                        startActivity(intent2);
                        break;
                    case "Last Game":
                        Intent intent3 = new Intent(AttendingActivity.this, LastGameActivity.class);
                        startActivity(intent3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}
