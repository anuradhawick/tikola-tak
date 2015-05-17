package com.example.anuradha.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anuradha.app1.Effects.WinnerBlinker;
import com.example.anuradha.app1.GameAI.Board;
import com.example.anuradha.app1.GameAI.ComputerPlayer;

import java.util.Arrays;

public class Game extends Activity implements View.OnClickListener {
    private Board board;
    private ComputerPlayer computerPlayer;

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


        Intent i = getIntent();
        Bundle b = i.getExtras();
        cpuChar = b.getChar("cpuChar");
        playFirst = b.getBoolean("play first");
        playerChar = (cpuChar == 'X') ? 'O' : 'X';
        blinker = new WinnerBlinker(board, this);


        computerPlayer = new ComputerPlayer(board, cpuChar);
        gameOver = false;
        if (!playFirst) {
            computerPlayer.play();
        }
        updateAll();
    }


    @Override
    public void onClick(View v) {
        //ending the activty when user presses back button
        if (v.getId() == R.id.back) {
            this.finish();
            return;
        }
        //starting the game again when user ask for a new game
        else if (v.getId() == R.id.newGame) {
            gameOver = false;
            blinker.stopBlink();
            board = new Board();
            computerPlayer = new ComputerPlayer(board, cpuChar);
            //if the user wants to play first computer stays still
            if (!playFirst) {
                computerPlayer.play();
            }
            //update the UI to start
            updateAll();
            return;
        }
        //if the game has already eneded do no change
        else if (gameOver) {
            return;
        }
        //responses to buttons
        switch (v.getId()) {
            case R.id.bt00:
                if (!board.setCell(0, 0, playerChar)) {
                    return;
                }
                break;
            case R.id.bt01:
                if (!board.setCell(0, 1, playerChar)) {
                    return;
                }
                break;
            case R.id.bt02:
                if (!board.setCell(0, 2, playerChar)) {
                    return;
                }
                break;
            case R.id.bt10:
                if (!board.setCell(1, 0, playerChar)) {
                    return;
                }
                break;
            case R.id.bt11:
                if (!board.setCell(1, 1, playerChar)) {
                    return;
                }
                break;
            case R.id.bt12:
                if (!board.setCell(1, 2, playerChar)) {
                    return;
                }
                break;
            case R.id.bt20:
                if (!board.setCell(2, 0, playerChar)) {
                    return;
                }
                break;
            case R.id.bt21:
                if (!board.setCell(2, 1, playerChar)) {
                    return;
                }
                break;
            case R.id.bt22:
                if (!board.setCell(2, 2, playerChar)) {
                    return;
                }
                break;
        }
        updateAll();
        //check if the computer has won the game after a move made by computer
        if (!board.isGameOver()) {
            computerPlayer.play();
            updateAll();
        }
        if (board.isGameOver() || board.getWinner().isWinner()) {
            //check is the game is finished (Board is filled or Computer has won or the user has won)
            gameOver = true;
            if (board.getWinner().isWinner()) {
                updateAll();
                //if the winning character is user give him the toast
                if (board.getWinner().getWinnerChar() == playerChar) {
                    Toast.makeText(this, "You Won!!", Toast.LENGTH_SHORT).show();
                } else {
                    //else give the toast to computer
                    Toast.makeText(this, "Computer Won!!", Toast.LENGTH_SHORT).show();
                }
                //perform the blinking effect
                blinker = new WinnerBlinker(board, this);
                blinker.blink();
            } else {
                //give this toast if the game has a draw condition
                updateAll();
                Toast.makeText(this, "Game Drawn!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
    updating the entire UI
    used to update after a computers move
     */
    public void updateAll() {
        for (int i = 0; i < board.getBoard().length; i++)
            for (int j = 0; j < board.getBoard().length; j++) {
                updateUI(i, j, board.getBoard()[i][j], false);
            }
    }

    //update UI location by location
    public void updateUI(int i, int j, char in, boolean winner) {
        //if it is a blank space
        if (in == '-') {
            String s = i + "" + j;
            switch (s) {
                case "00":
                    btn00.setImageResource(R.drawable.rsz_spc);
                    break;
                case "01":
                    btn01.setImageResource(R.drawable.rsz_spc);
                    break;
                case "02":
                    btn02.setImageResource(R.drawable.rsz_spc);
                    break;
                case "10":
                    btn10.setImageResource(R.drawable.rsz_spc);
                    break;
                case "11":
                    btn11.setImageResource(R.drawable.rsz_spc);
                    break;
                case "12":
                    btn12.setImageResource(R.drawable.rsz_spc);
                    break;
                case "20":
                    btn20.setImageResource(R.drawable.rsz_spc);
                    break;
                case "21":
                    btn21.setImageResource(R.drawable.rsz_spc);
                    break;
                case "22":
                    btn22.setImageResource(R.drawable.rsz_spc);
                    break;
            }
            return;

            //if it is not a blank space
        } else {
            String s = i + "" + j;
            switch (s) {
                case "00":
                    if (winner) {
                        btn00.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn00.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "01":
                    if (winner) {
                        btn01.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn01.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "02":
                    if (winner) {
                        btn02.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn02.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "10":
                    if (winner) {
                        btn10.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn10.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "11":
                    if (winner) {
                        btn11.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn11.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "12":
                    if (winner) {
                        btn12.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn12.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "20":
                    if (winner) {
                        btn20.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn20.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "21":
                    if (winner) {
                        btn21.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn21.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "22":
                    if (winner) {
                        btn22.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn22.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
            }
        }

    }
}
