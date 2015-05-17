package com.example.anuradha.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainMenu extends Activity implements View.OnClickListener {
    private ImageButton play;
    private ImageButton highscore;
    private ImageButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        play = (ImageButton) findViewById(R.id.start);
        highscore = (ImageButton) findViewById(R.id.highscore);
        exit = (ImageButton) findViewById(R.id.close);

        play.setOnClickListener(this);
        highscore.setOnClickListener(this);
        exit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                Intent i;
                i = new Intent(this, GameMode.class);
                startActivity(i);
                break;
            case R.id.highscore:
                break;
            case R.id.close:
                this.finish();
                break;
            case R.id.aboutus:
                break;
        }
    }
}
