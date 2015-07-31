package com.example.anuradha.app1.GameModes;

import android.app.Activity;
import android.widget.Toast;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.GameAI.Board;

/**
 * Created by anuradha on 7/31/15.
 */
public class HumanHuman extends AbstractGameMode {

    private int turn;

    public HumanHuman(UImanager uimgr, Board bd, Activity act) {

        turn = 0;
        uimanager = uimgr;
        gameOver = false;
        board = bd;
        runningActivity = act;
    }

    public void move(int x, int y) {
        //make the move intended
        if (gameOver) {
            return;
        }
        if (turn % 2 == 0) {
            if (!board.setCell(x, y, 'X')) {
                return;
            }
            uimanager.updateUI(x, y, 'X', false);
            turn++;
        } else {
            if (!board.setCell(x, y, 'O')) {
                return;
            }
            uimanager.updateUI(x, y, 'O', false);
            turn++;
        }
        if (board.isGameOver()) {
            gameOver = true;
        }
        if (board.getWinner().isWinner()) {
            gameOver = true;
            if (board.getWinner().getWinnerChar() == 'X') {
                Toast.makeText(runningActivity, "Player 1 won!!", Toast.LENGTH_SHORT).show();
                blinkWinner();
            } else {
                Toast.makeText(runningActivity, "Player 2 won!!", Toast.LENGTH_SHORT).show();
                blinkWinner();
            }
            return;
        } else if (gameOver) {
            Toast.makeText(runningActivity, "Game was drawn!!", Toast.LENGTH_SHORT).show();
        }

    }
}
