package com.example.anuradha.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class GameMode extends Activity implements View.OnClickListener{
    private ImageView singlePlayer;
    private ImageView multiPlayer;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        singlePlayer = (ImageView)findViewById(R.id.singleplayer);
        multiPlayer = (ImageView)findViewById(R.id.multiplayer);
        back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(this);
        singlePlayer.setOnClickListener(this);
        multiPlayer.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.singleplayer:
                Intent i = new Intent(this, Game.class);
                startActivity(i);
                break;
            case R.id.multiplayer:
                break;
            case R.id.back:
                this.finish();
                break;
        }
    }
}
