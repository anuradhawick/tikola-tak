package com.example.anuradha.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.anuradha.app1.DBConnection.DatabaseHandler;
import com.example.anuradha.app1.DBConnection.UserStat;


public class GameMode extends Activity implements View.OnClickListener {
    private ImageView solo;
    private ImageView people;
    private ImageView network;
    private ImageView back;
    private ImageView proceed;
    private ImageView user;
    private ImageView order;
    private TextView mode;
    private boolean x;
    private boolean one;
    private DatabaseHandler dbh;
    private UserStat stat;
    private int diffLevel;
    private ToggleButton diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        //initial game preferences
        x = true;
        diffLevel = 1;

        //linking UI
        solo = (ImageView) findViewById(R.id.solo);
        people = (ImageView) findViewById(R.id.people);
        network = (ImageView) findViewById(R.id.network);
        order = (ImageView) findViewById(R.id.order);
        user = (ImageView) findViewById(R.id.user_symbol);
        proceed = (ImageView) findViewById(R.id.fwd);
        back = (ImageView) findViewById(R.id.back);
        mode = (TextView) findViewById(R.id.mode);
        diff = (ToggleButton) findViewById(R.id.difflevel);

        //setting listeners
        back.setOnClickListener(this);
        solo.setOnClickListener(this);
        people.setOnClickListener(this);
        network.setOnClickListener(this);
        proceed.setOnClickListener(this);
        user.setOnClickListener(this);
        order.setOnClickListener(this);
        Game.type = 1;
        one = true;

        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diffLevel = (diffLevel == 1) ? 2 : 1;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.solo:
                Game.type = 1;
                mode.setText("Single Player");
                user.setVisibility(View.VISIBLE);
                order.setVisibility(View.VISIBLE);
                diff.setVisibility(View.VISIBLE);

                break;
            case R.id.people:
                Game.type = 2;
                mode.setText("Two Player");
                user.setVisibility(View.INVISIBLE);
                order.setVisibility(View.INVISIBLE);
                diff.setVisibility(View.INVISIBLE);
                break;
            case R.id.network:
                Game.type = 3;
                mode.setText("Network Play");
                user.setVisibility(View.INVISIBLE);
                order.setVisibility(View.INVISIBLE);
                diff.setVisibility(View.INVISIBLE);
                break;
            case R.id.fwd:
                Intent i;
                switch (Game.type) {
                    case 1:
                        i = new Intent(this, Game.class);
                        i.putExtra("type", 1);
                        i.putExtra("firstPlay", one);
                        i.putExtra("diff", diffLevel);
                        i.putExtra("cpu", x ? 'O' : 'X');
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(this, Game.class);
                        i.putExtra("type", 2);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(this, BTDeviceSelect.class);
                        startActivity(i);
                        break;
                }
                break;
            case R.id.back:
                this.finish();
                break;
            case R.id.user_symbol:
                if (x) {
                    user.setImageResource(R.drawable.user_o);
                } else {
                    user.setImageResource(R.drawable.user_x);
                }
                x = !x;
                break;
            case R.id.order:
                if (one) {
                    order.setImageResource(R.drawable.second);
                } else {
                    order.setImageResource(R.drawable.first);
                }
                one = !one;
                break;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Game.type == 1 && (Game.wins > 0 || Game.losses > 0)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter your name");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Game.m_Text = input.getText().toString();
                            if (!Game.m_Text.equals(null) || !Game.m_Text.equals("")) {
                                try {
                                    dbh = new DatabaseHandler(getApplicationContext());
                                    if (dbh.searchPlayer(Game.m_Text)) {

                                        dbh.updatewins(Game.m_Text, Game.wins);
                                        dbh.updatelosses(Game.m_Text, Game.losses);
                                    } else {

                                        stat = new UserStat(1, Game.m_Text, "Moderate", Game.wins, Game.losses);
                                        dbh.addUserStat(stat);
                                    }

                                    System.out.println("Saved " + Game.wins + " " + Game.losses);
                                    Game.wins = 0;
                                    Game.losses = 0;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
            );

            builder.show();
        }
    }
}
