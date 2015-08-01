package com.example.anuradha.app1.GameModes;

import android.app.Activity;
import android.widget.Toast;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.Game;
import com.example.anuradha.app1.GameAI.Board;
import com.example.anuradha.app1.GameAI.ComputerPlayer;

/**
 * Created by anuradha on 7/31/15.
 */
public class HumanComputer extends AbstractGameMode {
    private char cpuChar, userChar;
    private int difficulty;
    private boolean playFirst;
    private ComputerPlayer cpuplayer;
    private boolean moved;

    public HumanComputer(boolean firstPlay, char cpuC, int diffLevel, UImanager uimgr, Board bd, Activity act) {
        //setting the initial conditions for the game
        moved = false;
        board = new Board();
        gameOver = false;
        uimanager = uimgr;
        runningActivity = act;
        cpuChar = cpuC;
        userChar = (cpuChar == 'X') ? 'O' : 'X';
        difficulty = diffLevel;
        playFirst = firstPlay;
        cpuplayer = new ComputerPlayer(board, cpuChar);
        if (!playFirst) {
            cpuplayer.play();
            updateAll();
        }

    }

    //making the moves by the user
    @Override
    public void move(int x, int y) {
        if (gameOver) {
            return;
        } else if (!board.setCell(x, y, userChar)) {
            updateAll();
            return;
        }
        if (board.isGameOver()) {
            gameOver = true;
        }
        if (!gameOver) {
            cpuplayer.play();
            updateAll();
        }
        if (board.getWinner().isWinner()) {
            if (board.getWinner().getWinnerChar() == cpuChar) {
                Toast.makeText(runningActivity, "Computer Won!!", Toast.LENGTH_SHORT).show();
                ((Game) runningActivity).setLoss();
            } else {
                Toast.makeText(runningActivity, "You Won!!", Toast.LENGTH_SHORT).show();
                ((Game) runningActivity).setWin();
            }
            blinkWinner();
            return;
        }

        if (board.isGameOver()) {
            updateAll();
            Toast.makeText(runningActivity, "Game Drawn!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startOver() {
        if (blinker != null)
            blinker.stopBlink();
        board = new Board();
        cpuplayer = new ComputerPlayer(board, cpuChar);
        if (!playFirst) {
            cpuplayer.play();
        }
        updateAll();
    }
}
