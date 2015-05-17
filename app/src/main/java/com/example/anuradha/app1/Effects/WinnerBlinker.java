package com.example.anuradha.app1.Effects;

import android.app.Activity;
import android.os.CountDownTimer;

import com.example.anuradha.app1.Game;
import com.example.anuradha.app1.GameAI.Board;

/**
 * Created by anuradha on 03/05/15.
 */
public class WinnerBlinker extends Activity {
    private char winner;
    private Board gameBoard;
    private Game game;
    CountDownTimer blinkTimer;
    public WinnerBlinker(Board gameBoard, Game game) {
        this.gameBoard = gameBoard;
        this.game = game;
    }


    public void blink() {
        final TimeKeeper keeper = new TimeKeeper();
        keeper.setTag('A');
        blinkTimer = new CountDownTimer((6 + 1) * 300, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                Winner winner = gameBoard.getWinner();
                if (winner.isWinner()) {
                    char winnerChar = winner.getWinnerChar();
                    int[][] locations = winner.getLocations();

                    if (keeper.getTag() == 'A') {
                        for (int[] arr : locations) {
                            game.updateUI(arr[0], arr[1], '-', false);
                        }
                        keeper.setTag('B');
                    } else if (keeper.getTag() == 'B') {
                        for (int[] arr : locations) {
                            game.updateUI(arr[0], arr[1], winnerChar, true);
                        }
                        keeper.setTag('A');
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };
        blinkTimer.start();
    }
    public void stopBlink(){
        try{
            blinkTimer.cancel();
        } catch (Exception ex){
            return;
        }
    }

    class TimeKeeper {
        private char tag;

        public char getTag() {
            return tag;
        }

        public void setTag(char tag) {
            this.tag = tag;
        }
    }

}
