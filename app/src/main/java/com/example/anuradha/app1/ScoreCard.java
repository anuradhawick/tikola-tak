package com.example.anuradha.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anuradha.app1.DBConnection.DatabaseHandler;
import com.example.anuradha.app1.DBConnection.UserStat;

import java.util.List;

public class ScoreCard extends Activity implements View.OnClickListener {

    private ImageView back;
    private ListView scores;
    private ArrayAdapter<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        arr = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        back = (ImageView) findViewById(R.id.exit);
        scores = (ListView) findViewById(R.id.scores);
        back.setOnClickListener(this);
        scores.setAdapter(arr);
        DatabaseHandler dbh = new DatabaseHandler(this);
        List<UserStat> stats = dbh.getUserStatWins(1);
        for (UserStat s : stats) {
            try {
                arr.add(String.valueOf(s.getPlayer() + " " + s.getWins()));
                System.out.println(s.getPlayer() + " from DB had wins " + s.getWins());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
