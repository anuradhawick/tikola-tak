package com.example.anuradha.app1.GameModes;

import android.app.Activity;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.Effects.WinnerBlinker;
import com.example.anuradha.app1.GameAI.Board;

/**
 * Created by anuradha on 7/31/15.
 */
public abstract class AbstractGameMode {
    protected UImanager uimanager;
    protected boolean gameOver;
    protected Board board;
    protected Activity runningActivity;
    protected WinnerBlinker blinker;

    /*
    * Animates the winner's winning grid
    * */
    protected void blinkWinner() {
        blinker = new WinnerBlinker(board, uimanager);
        blinker.blink();
    }

    /*
    * This starts a new game session
    * */
    public void startOver() {
        gameOver = false;
        if (blinker != null)
            blinker.stopBlink();
        board = new Board();
        updateAll();
    }


    /*
    updating the entire UI
    used to update after a computers move
     */
    public void updateAll() {
        for (int i = 0; i < board.getBoard().length; i++)
            for (int j = 0; j < board.getBoard().length; j++) {
                uimanager.updateUI(i, j, board.getBoard()[i][j], false);
            }
    }

    /*
    * This method should be overriden by all the GameMode sub classes
    * */
    public abstract void move(int x, int y);
}
