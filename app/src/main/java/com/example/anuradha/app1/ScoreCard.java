package com.example.anuradha.app1;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.anuradha.app1.DBConnection.DatabaseHandler;
import com.example.anuradha.app1.DBConnection.UserStat;

import java.util.List;
import java.util.logging.Handler;

public class ScoreCard extends Activity implements View.OnClickListener {

    private ImageView back;
    private ListView scores;
    private ArrayAdapter<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        back = (ImageView) findViewById(R.id.exit);
        back.setOnClickListener(this);
        TableLayout tl = (TableLayout) findViewById(R.id.tablelayout1);
        DatabaseHandler dbh = new DatabaseHandler(this);
        List<UserStat> list = dbh.getUserStatWins(1);
        TableRow row = new TableRow(getApplicationContext());

        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        Handler handler = null;

        TextView tv0 = new TextView(getApplicationContext());
        tv0.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv0.setTextColor(Color.BLACK);
        tv0.setTextSize(30);
        tv0.setBackgroundColor(Color.CYAN);
        tv0.setTypeface(null, Typeface.BOLD);
        tv0.setText(String.format("%7s", "Player"));


        TextView tv1 = new TextView(getApplicationContext());
        tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv1.setTextColor(Color.BLACK);
        tv1.setTextSize(30);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setBackgroundColor(Color.CYAN);
        tv1.setText(String.format("%7s", "Wins"));

        TextView tv2 = new TextView(getApplicationContext());
        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv2.setTextColor(Color.BLACK);
        tv2.setTextSize(30);
        tv2.setBackgroundColor(Color.CYAN);
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setText(String.format("%7s", "Losses"));

        row.addView(tv0);
        row.addView(tv1);
        row.addView(tv2);

        tl.addView(row);
        if (list.size() != 0) {


            for (UserStat s : list) {
                TableRow row1 = new TableRow(getApplicationContext());
                row1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                Handler handler1 = null;

                TextView tv3 = new TextView(getApplicationContext());
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv3.setTextColor(Color.BLACK);

                tv3.setTextSize(25);
                tv3.setText(String.format("%7s", s.getPlayer()));


                TextView tv4 = new TextView(getApplicationContext());
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setTextColor(Color.BLACK);
                tv4.setTextSize(25);
                tv4.setText(String.format("%7s", s.getWins() + ""));

                TextView tv5 = new TextView(getApplicationContext());
                tv5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv5.setTextColor(Color.BLACK);
                tv5.setTextSize(25);
                tv5.setText(String.format("%7s", s.getLosses() + ""));

                row1.addView(tv3);
                row1.addView(tv4);
                row1.addView(tv5);

                tl.addView(row1);

            }
        }

    }


    @Override
    public void onClick(View view) {
        this.finish();
    }
}
