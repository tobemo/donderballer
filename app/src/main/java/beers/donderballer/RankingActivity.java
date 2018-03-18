package beers.donderballer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RankingActivity extends AppCompatActivity {

    private TableLayout rankingTable;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addDummyRow (View view )    {
        addRow(Integer.toString(count), "Derp", "2", "55", "1234", Color.TRANSPARENT, Color.BLACK);
        count++;
    }




    private void initHeader() {

    }

    private void addRow(String rank, String name, String canOpener, String peaCrusher, String pariBlast, int backgroundColor, int textColor) {
        rankingTable = (TableLayout) findViewById(R.id.main_table);
        rankingTable.setColumnStretchable(1, Boolean.TRUE);

        TableRow tr_head = new TableRow(this);
        tr_head.setId(View.generateViewId());   //TODO: support API lower than 17
        tr_head.setBackgroundColor(backgroundColor);        // part1
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));

        TextView label_rk = new TextView(this);
        label_rk.setId(View.generateViewId());
        label_rk.setText(rank);
        label_rk.setTextColor(textColor);          // part2
        label_rk.setPadding(5, 5, 5, 5);
        tr_head.addView(label_rk);// add the column to the table row here

        TextView label_name = new TextView(this);    // part3
        label_name.setId(View.generateViewId());// define id that must be unique
        label_name.setText(name); // set the text for the header
        label_name.setTextColor(textColor); // set the color
        label_name.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_name); // add the column to the table row here

        TextView label_CO = new TextView(this);    // part3
        label_CO.setId(View.generateViewId());// define id that must be unique
        label_CO.setText(canOpener); // set the text for the header
        label_CO.setTextColor(textColor); // set the color
        label_CO.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_CO); // add the column to the table row here

        TextView label_PC = new TextView(this);    // part3
        label_PC.setId(View.generateViewId());// define id that must be unique
        label_PC.setText(peaCrusher); // set the text for the header
        label_PC.setTextColor(textColor); // set the color
        label_PC.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_PC); // add the column to the table row here

        TextView label_PB = new TextView(this);    // part3
        label_PB.setId(View.generateViewId());// define id that must be unique
        label_PB.setText(pariBlast); // set the text for the header
        label_PB.setTextColor(textColor); // set the color
        label_PB.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_PB); // add the column to the table row here

        rankingTable.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,                    //part4
                TableLayout.LayoutParams.MATCH_PARENT));
    }
}
