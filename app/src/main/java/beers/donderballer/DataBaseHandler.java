package beers.donderballer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DataBaseHandler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected String getRowRanking(int row)  {

        return null;
    }

    protected Integer getRankingTableSize() {
        int size = 0;

        return size;
    }

    protected String getTeamA() {
        return "Tomás_Beers,Nand_Wallays,Nand_Broeckaert,Jesse_De_Weerdt";
    }

    protected String getTeamB() {
        return "Han_Depoortere,Dries_De_Bièvre,Alexander_Baumgartner,Cédric_Vranken";
    }

    protected String getCanOpener() {
        String canOpener = "Han_Depoortere";
        canOpener = canOpener.replaceAll("_", " ");
        return canOpener;
    }

    protected String getPeaCrusher()    {
        String peaCrusher = "Nand_Wallays";
        peaCrusher = peaCrusher.replaceAll("_", " ");
        return peaCrusher;
    }
}
