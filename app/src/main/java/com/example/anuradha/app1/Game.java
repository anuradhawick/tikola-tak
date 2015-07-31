package com.example.anuradha.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.Effects.WinnerBlinker;
import com.example.anuradha.app1.GameAI.Board;
import com.example.anuradha.app1.GameAI.ComputerPlayer;
import com.example.anuradha.app1.GameModes.HumanHuman;


public class Game extends Activity implements View.OnClickListener {
    private Board board;
    private ComputerPlayer computerPlayer;
    private HumanHuman human_vs_human_game;

    private ImageButton btn00;
    private ImageButton btn01;
    private ImageButton btn02;
    private ImageButton btn10;
    private ImageButton btn11;
    private ImageButton btn12;
    private ImageButton btn20;
    private ImageButton btn21;
    private ImageButton btn22;

    private ImageView btnNewGame;
    private ImageView btnBack;
    private boolean gameOver;

    private WinnerBlinker blinker;

    private boolean playFirst;
    private char cpuChar;
    private char playerChar;

    private UImanager uimanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamewindow);

        /*
        Generating the game board at the beginig to start a new game
         */
        board = new Board();

        btn00 = (ImageButton) findViewById(R.id.bt00);
        btn01 = (ImageButton) findViewById(R.id.bt01);
        btn02 = (ImageButton) findViewById(R.id.bt02);
        btn10 = (ImageButton) findViewById(R.id.bt10);
        btn11 = (ImageButton) findViewById(R.id.bt11);
        btn12 = (ImageButton) findViewById(R.id.bt12);
        btn20 = (ImageButton) findViewById(R.id.bt20);
        btn21 = (ImageButton) findViewById(R.id.bt21);
        btn22 = (ImageButton) findViewById(R.id.bt22);

        btn00.setOnClickListener(this);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);

        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);

        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);

        btnNewGame = (ImageView) findViewById(R.id.newGame);
        btnBack = (ImageView) findViewById(R.id.back);

        btnNewGame.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        uimanager = new UImanager(this);
//        Intent i = getIntent();
//        Bundle b = i.getExtras();
//        cpuChar = b.getChar("cpuChar");
//        playFirst = b.getBoolean("play first");
//        playerChar = (cpuChar == 'X') ? 'O' : 'X';


//        computerPlayer = new ComputerPlayer(board, cpuChar);
//        gameOver = false;
//        if (!playFirst) {
//            computerPlayer.play();
//        }

        human_vs_human_game = new HumanHuman(uimanager, board, this);
        human_vs_human_game.updateAll();
    }

    /*
    * ActionListener for Button clicks
    **/
    @Override
    public void onClick(View v) {
        //ending the activity when user presses back button
        if (v.getId() == R.id.back) {
            this.finish();
            return;
        }
        //starting the game again when user ask for a new game
        else if (v.getId() == R.id.newGame) {
            human_vs_human_game.startOver();
//            computerPlayer = new ComputerPlayer(board, cpuChar);
            //if the user wants to play first computer stays still
//            if (!playFirst) {
//                computerPlayer.play();
//            }
            //update the UI to start
            //updateAll();
            return;
        }
        //if the game has already eneded do no change
//        else if (gameOver) {
//            return;
//        }
        //responses to buttons
        switch (v.getId()) {
            case R.id.bt00:
                human_vs_human_game.move(0, 0);
                break;
            case R.id.bt01:
                human_vs_human_game.move(0, 1);
                break;
            case R.id.bt02:
                human_vs_human_game.move(0, 2);
                break;
            case R.id.bt10:
                human_vs_human_game.move(1, 0);
                break;
            case R.id.bt11:
                human_vs_human_game.move(1, 1);
                break;
            case R.id.bt12:
                human_vs_human_game.move(1, 2);
                break;
            case R.id.bt20:
                human_vs_human_game.move(2, 0);
                break;
            case R.id.bt21:
                human_vs_human_game.move(2, 1);
                break;
            case R.id.bt22:
                human_vs_human_game.move(2, 2);
                break;
        }
    }
}
