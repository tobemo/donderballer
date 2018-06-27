package beers.donderballer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RankingActivity extends AppCompatActivity {

    private TableLayout rankingTable;
    private int count;

    //style variables
    int textSize = 18;
    int textColor = Color.BLACK;
    int backgroundColor = Color.TRANSPARENT;
    int padding = 25;    //20

    //header
    TextView rankTitle;
    TextView nameTitle;
    TextView canOpenerTitle;
    TextView peaCrusherTitle;
    TextView pariBlastTitle;

    //headerWidth
    int rankWidth;
    int nameWidth;
    int canOpenerWidth;
    int peaCrusherWidth;
    int pariBlastWidth;

    //toolbar etc
    Toolbar toolbarRanking;
    Spinner spinnerRanking;

    private DataBaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarRanking = findViewById(R.id.toolbarRanking);
        spinnerRanking = findViewById(R.id.spinnerRanking);

        ArrayAdapter<String> rankingAdapter = new ArrayAdapter<String>(RankingActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activities));
        rankingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRanking.setAdapter(rankingAdapter);
        spinnerRanking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long l) {
                //Idealy each case references an ellemnt of the strings.activities-string-array. Switchcases however don't support arrays.
                //Maybe one day...
                final Intent intent;
                switch (spinnerRanking.getSelectedItem().toString())    {
                    case "Ranking":
                        //nothing
                        break;
                    case "Attending":
                        spinnerRanking.setSelection(getIndex(spinnerRanking,"Attending"));
                        intent = new Intent(RankingActivity.this, AttendingActivity.class);
                        startActivity(intent);
                        break;
                    case "Game":
                        spinnerRanking.setSelection(getIndex(spinnerRanking,"Game"));
                        intent = new Intent(RankingActivity.this, GameActivity.class);
                        startActivity(intent);
                        break;
                    case "Last Game":
                        spinnerRanking.setSelection(getIndex(spinnerRanking,"Last Gale"));
                        intent = new Intent(RankingActivity.this, LastGameActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        rankTitle = (TextView)findViewById(R.id.rankTitle);
        nameTitle = (TextView)findViewById(R.id.nameTitle);
        canOpenerTitle = (TextView)findViewById(R.id.canOpenerTitle);
        peaCrusherTitle = (TextView)findViewById(R.id.peaCrusherTitle);
        pariBlastTitle = (TextView)findViewById(R.id.pariBlastTitle);

        measureHeader();

        //updateRanking();

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

    public void addDummyRow (View view )    {
        addRow(Integer.toString(count), "Derp", "2", "55", "1234");
        count++;
    }

    /**
     * Display every row of the database table 'ranking' in the app table 'ranking'.
     */
    private void updateRanking()    {
        String rank = "99";
        String name = "John Doe";
        String co = "0";
        String pc = "0";
        String pb = "99";

        for (int row = 0; row < database.getRankingTableSize(); row++)  {
            //TODO: extract data
            database.getRowRanking(row);

            addRow(rank, name, co, pc, pb);
        }
    }


    /**
     * This method is used to add rows to the ranking table containing a player with its stats.
     * @param rank
     * @param name
     * @param canOpener
     * @param peaCrusher
     * @param pariBlast
     */
    private void addRow(String rank, String name, String canOpener, String peaCrusher, String pariBlast) {

        if(canOpener.length() > 3) {canOpener = "MX";}
        if(peaCrusher.length() > 3) {peaCrusher = "MX";}
        if(pariBlast.length() > 3) {pariBlast = "MX";}

        rankingTable = (TableLayout) findViewById(R.id.main_table);
        rankingTable.setColumnStretchable(1, Boolean.TRUE);

        TableRow newRow = new TableRow(this);
        newRow.setId(View.generateViewId());   //TODO: support API lower than 17
        newRow.setBackgroundColor(backgroundColor);        // set background color as specified by variable backgroundColor
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.MATCH_PARENT);
        newRow.setLayoutParams(params);

        TextView label_rk = new TextView(this);
        label_rk.setText(rank); // set the text for the header
        label_rk.setId(View.generateViewId());
        styling(label_rk);  //style textview using method 'styling'
        label_rk.setWidth(rankWidth);  //set width of this textview the sma eas the width of the textview-header
        newRow.addView(label_rk);// add the column to the table row here

        TextView label_name = new TextView(this);
        label_name.setId(View.generateViewId());
        label_name.setText(name);
        styling(label_name);
        label_name.setWidth(nameWidth);
        newRow.addView(label_name);

        TextView label_CO = new TextView(this);
        label_CO.setId(View.generateViewId());
        label_CO.setText(canOpener);
        styling(label_CO);
        label_CO.setWidth(canOpenerWidth);
        newRow.addView(label_CO);

        TextView label_PC = new TextView(this);
        label_PC.setId(View.generateViewId());
        label_PC.setText(peaCrusher);
        styling(label_PC);
        label_PC.setWidth(peaCrusherWidth);
        newRow.addView(label_PC);

        TextView label_PB = new TextView(this);
        label_PB.setId(View.generateViewId());
        label_PB.setText(pariBlast);
        styling(label_PB);
        label_PB.setWidth(pariBlastWidth);
        newRow.addView(label_PB);

        rankingTable.addView(newRow, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * This method does the styiling of the textviews used in the ranking table.
     * @param input
     */
    private void styling(TextView input) {
        input.setTextColor(textColor);
        input.setTextSize(textSize);
        input.setPadding(padding, padding, 0, 0);
        //input.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    /**
     * Gets the width's of the textviews in the header.
     */
    private void measureHeader()    {
        rankWidth = measure(rankTitle);
        nameWidth = measure(nameTitle);
        canOpenerWidth = measure(canOpenerTitle);
        peaCrusherWidth = measure(peaCrusherTitle);
        pariBlastWidth = measure(pariBlastTitle);
    }

    /**
     * Measures the width of the textviews in the header. This is used by addRow to set the width of each textview in a row.
     * @param textview
     * @return width of a textview
     */
    private int measure(TextView textview)   {
        textview.measure(0,0);
        return textview.getMeasuredWidth();
    }
}
