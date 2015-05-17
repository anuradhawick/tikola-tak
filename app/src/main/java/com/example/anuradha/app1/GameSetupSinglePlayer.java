package com.example.anuradha.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class GameSetupSinglePlayer extends Activity implements View.OnClickListener{
    private ImageView firstOrSecond;
    private boolean first;
    private boolean o;
    private ImageView xo;
    private ImageView back;
    private ImageView play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup_single_player);
        first = true;
        firstOrSecond = (ImageView)findViewById(R.id.firstorsecond);
        firstOrSecond.setOnClickListener(this);

        o = true;
        xo = (ImageView)findViewById(R.id.xo);
        back = (ImageView)findViewById(R.id.back);
        play = (ImageView)findViewById(R.id.play);
        back.setOnClickListener(this);
        play.setOnClickListener(this);
        xo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.xo:
                if(o){
                    xo.setImageResource(R.drawable.user_x);
                    o=false;
                }else{
                    xo.setImageResource(R.drawable.user_o);
                    o=true;
                }
                break;
            case R.id.firstorsecond:
                if(first){
                    firstOrSecond.setImageResource(R.drawable.second);
                    first = false;
                }else{
                    firstOrSecond.setImageResource(R.drawable.first);
                    first = true;
                }
                break;
            case R.id.back:
                this.finish();
                break;
            case R.id.play:
                char cpu = (o? 'X':'O');
                Bundle bundle = new Bundle();
                bundle.putBoolean("play first", first);
                bundle.putChar("cpuChar", cpu);
                Intent i = new Intent(this,Game.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
        }
    }
}
