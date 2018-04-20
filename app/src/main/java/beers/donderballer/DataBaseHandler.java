package beers.donderballer;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

//    //indien directe connectie met mysql
//    private void connectMySql()  {
//        try
//        {
//            // create a mysql database connection
//            String url = "jdbc:mysql://studev.groept.be:3306/a17_sd420";
//            Connection conn = DriverManager.getConnection(url, "a17_sd420", "");
//            //drivemanager nog establiseren
//
//            // the mysql insert statement
//            String queryInsert = " INSERT INTO Quiz (name, place, date, hour, price)" + "values (?, ?, ?, ?, ?)";
//
//            // create the mysql insert preparedstatement
//            PreparedStatement preparedStmt = conn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
////            preparedStmt.setString (1, name);
////            preparedStmt.setString(2, place);
////            preparedStmt.setString(3, date);
////            preparedStmt.setString(4, hour);
////            preparedStmt.setInt(5, price);
//
//            // execute the preparedstatement
//            int affectedRows = preparedStmt.executeUpdate();
//
//            if (affectedRows == 0) {
//                throw new SQLException("Creating quiz failed, no rows affected.");
//            }
//
//            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    //pk = generatedKeys.getInt(1);
//                } else {
//                    throw new SQLException("Creating quiz failed, no pk obtained.");
//                }
//            }
//
//
//            conn.close();
//        }
//
//
//
//        catch (Exception e)
//        {
//            System.err.println("Got an exception while making quiz!");
//            System.err.println(e.getMessage());
//        }
//
//    }
//
//    //indien via php
//    private void connectPHP()   {
//        String link ="";
//        try {
//            URL url = new URL(link);
//            HttpClient client = new DefaultHttpClient();
//            HttpGet request = new HttpGet();
//            requesst.setURI(new URI(link));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//    }


}
