package beers.donderballer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LastGameActivity extends AppCompatActivity {

    DataBaseHandler database;

    //score textviews
    TextView scoreTeamA;
    TextView scoreTeamB;

    //Strings
    String teamA;
    String teamB;
    int teamASize;
    int teamBSize;
    String canOpener;
    String peaCrusher;

    String[] teamAArray;
    String[] teamBArray;

    TableLayout playersTeam;

    //style variables
    int textSize = 12;
    int textColor = Color.BLACK;
    int backgroundColor = Color.TRANSPARENT;

    //toolbar etc
    Toolbar toolbarLastGame;
    Spinner spinnerLastGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_game);

        toolbarLastGame = findViewById(R.id.toolbarLastGame);
        spinnerLastGame = findViewById(R.id.spinnerLastGame);

        ArrayAdapter<String> lastGameAdapter = new ArrayAdapter<String>(LastGameActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activities));
        lastGameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLastGame.setAdapter(lastGameAdapter);
        spinnerLastGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Idealy each case references an ellemnt of the strings.activities-string-array. Switchcases however don't support arrays.
                //Maybe one day...
                switch (spinnerLastGame.getSelectedItem().toString())    {
                    case "Ranking":
                        Intent intent3 = new Intent(LastGameActivity.this, RankingActivity.class);
                        startActivity(intent3);
                        break;
                    case "Attending":
                        Intent intent = new Intent(LastGameActivity.this, AttendingActivity.class);
                        startActivity(intent);
                        break;
                    case "Game":
                        Intent intent2 = new Intent(LastGameActivity.this, GameActivity.class);
                        startActivity(intent2);
                        break;
                    case "Last Game":
                        //nothing
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        scoreTeamA = (TextView) findViewById(R.id.scoreTeamA);
        scoreTeamB = (TextView) findViewById(R.id.scoreTeamB);

        setTitle(getString(R.string.lastGame) + ": " + getDate());   //TODO: date getten
        displayScore(getScoreTeamA(), getScoreTeamB());

        database = new DataBaseHandler();




          getTeamA();
          getTeamB();
          getCanOpener();
          getPeaCrusher();
          stringSplitter();
          getTeamASize();
          getTeamBSize();
          populateTable();


    }


    private Integer getScoreTeamA() {
        //TODO: get score
        return 12;
    }

    private Integer getScoreTeamB() {
        //TODO: get score
        return 13;
    }

    private String getDate()    {
        //TODO: get date
        return "21/03/2018";
    }

    private void displayScore(int scoreTeamA, int scoreTeamB) {
        this.scoreTeamA.setText(Integer.toString(scoreTeamA));  //TODO: toString nodig?
        this.scoreTeamB.setText(Integer.toString(scoreTeamB));

    }

    private void populateTable()    {
        if(teamASize == teamBSize) {
            for (int i = 0; i < teamASize; i++) {
                addRowTeam(teamAArray[i], teamBArray[i]);
            }
        }
//        else if(teamASize >= teamBSize)   {
//            for (int i = 0; i < teamASize; i++) {
//                addRowTeam(teamAArray[i]);
//                addRowTeam(teamBArray[i]);
//            }
//            for (int i = teamBSize; i < (teamASize-teamBSize); i++) {
//                addRowTeam(teamAArray[i]);
//            }
//        }   else if(teamBSize > teamASize)  {
//            for(int i = teamASize; i < (teamBSize-teamASize); i++)  {
//                addRowTeam(teamBArray[i]);
//            }
//        }
    }

    private void addRowTeam(String nameA, String nameB)    {
        playersTeam = (TableLayout) findViewById(R.id.playersTeam);
        playersTeam.setColumnStretchable(1, Boolean.TRUE);
        playersTeam.setColumnStretchable(2, Boolean.TRUE);
        playersTeam.setColumnShrinkable(0, Boolean.TRUE);
        playersTeam.setColumnShrinkable(3, Boolean.TRUE);
        playersTeam.setPadding(5,5,5,5);

        TableRow newRow = new TableRow(this);
        newRow.setId(View.generateViewId());   //TODO: support API lower than 17
        newRow.setBackgroundColor(backgroundColor);        // set background color as specified by variable backgroundColor
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT);
        newRow.setLayoutParams(params);

        TextView label_achievementA = new TextView(this);
        if(nameA.equals(canOpener) && nameA.equals(peaCrusher)) {
            label_achievementA.setText("PB");
        }   else if (nameA.equals(canOpener))   {
            label_achievementA.setText("CO");
        }   else if (nameA.equals(peaCrusher))  {
            label_achievementA.setText("PC");
        }   else    {
            label_achievementA.setText("");    //two spaces to occupy two chars
        }
        label_achievementA.setId(View.generateViewId());
        styling(label_achievementA);
        label_achievementA.setGravity(Gravity.START);
        newRow.addView(label_achievementA);// add the column to the table row here

        TextView label_nameA = new TextView(this);
        label_nameA.setText(nameA);
        label_nameA.setId(View.generateViewId());
        styling(label_nameA);  //style textview using method 'styling'
        label_nameA.setGravity(Gravity.START);
        label_nameA.setPadding(5,0,0,0);
        newRow.addView(label_nameA);// add the column to the table row here



        TextView label_nameB = new TextView(this);
        label_nameB.setText(nameB);
        label_nameB.setId(View.generateViewId());
        styling(label_nameB);  //style textview using method 'styling'
        label_nameB.setGravity(Gravity.END);
        label_nameB.setPadding(0,0,5,0);

        newRow.addView(label_nameB);// add the column to the table row here

        TextView label_achievementB = new TextView(this);
        if(nameB.equals(canOpener) && nameB.equals(peaCrusher)) {
            label_achievementB.setText("PB");
        }   else if (nameB.equals(canOpener))   {
            label_achievementB.setText("CO");
        }   else if (nameB.equals(peaCrusher))  {
            label_achievementB.setText("PC");
        }   else    {
            label_achievementB.setText("");    //two spaces to occupy two chars
        }
        label_achievementB.setId(View.generateViewId());
        styling(label_achievementB);
        label_achievementB.setGravity(Gravity.END);
        TableRow.LayoutParams weightParam = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0F
        );
        label_achievementB.setLayoutParams(weightParam);
        newRow.addView(label_achievementB);// add the column to the table row here

        playersTeam.addView(newRow, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));

    }

    private void stringSplitter()    {
        if(teamA.contains(",")) {
            teamAArray = teamA.replaceAll("_", " ")
                                  .split(",");
        }   else {
            throw new IllegalArgumentException("String " + teamA + " does not contain ,");
        }

        if(teamB.contains(",")) {
            teamBArray = teamB.replaceAll("_", " ")
                    .split(",");
        }   else {
            throw new IllegalArgumentException("String " + teamB + " does not contain ,");
        }

    }




    private void getCanOpener() {
        canOpener = database.getCanOpener();
    }

    /**
     * This method gets the peacrusher and replaces the '_' with a space
     */
    private void getPeaCrusher()    {
        peaCrusher = database.getPeaCrusher();
    }

    /**
     * This method does the styling of the textviews used in the ranking table.
     * @param input
     */
    private void styling(TextView input) {
        input.setTextColor(textColor);
        input.setTextSize(textSize);
    }

    public void getTeamA() {
        teamA = database.getTeamA();
    }

    public void getTeamB() {
        teamB = database.getTeamB();
    }

    public Integer getTeamASize() {
        teamASize = 0;
        teamASize = teamAArray.length;
        return teamASize;
    }

    public Integer getTeamBSize() {
        teamBSize = 0;
        teamBSize = teamBArray.length;
        return teamBSize;
    }
}
