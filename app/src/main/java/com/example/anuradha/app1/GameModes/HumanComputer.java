package com.example.anuradha.app1.GameModes;

import android.app.Activity;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.GameAI.Board;

/**
 * Created by anuradha on 7/31/15.
 */
public class HumanComputer extends AbstractGameMode {
    private char cpuChar;
    int difficulty;

    public HumanComputer(char cpuC, int diffLevel, UImanager uimgr, Board bd, Activity act) {
        gameOver = false;
        uimanager = uimgr;
        runningActivity = act;
        cpuChar = cpuC;
        difficulty = diffLevel;
    }

//    public void gameLogic(){
//        //check if the computer has won the game after a move made by computer
//        if (!board.isGameOver()) {
//            computerPlayer.play();
//            updateAll();
//        }
//        if (board.isGameOver() || board.getWinner().isWinner()) {
//            //check is the game is finished (Board is filled or Computer has won or the user has won)
//            gameOver = true;
//            if (board.getWinner().isWinner()) {
//                updateAll();
//                //if the winning character is user give him the toast
//                if (board.getWinner().getWinnerChar() == playerChar) {
//                    Toast.makeText(this, "You Won!!", Toast.LENGTH_SHORT).show();
//                } else {
//                    //else give the toast to computer
//                    Toast.makeText(this, "Computer Won!!", Toast.LENGTH_SHORT).show();
//                }
//                //perform the blinking effect
//                blinker = new WinnerBlinker(board, this.uimanager);
//                blinker.blink();
//            } else {
//                //give this toast if the game has a draw condition
//                updateAll();
//                Toast.makeText(this, "Game Drawn!!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
