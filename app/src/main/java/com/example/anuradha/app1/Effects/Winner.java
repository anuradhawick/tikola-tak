package com.example.anuradha.app1.Effects;

/**
 * Created by anuradha on 03/05/15.
 */
public class Winner {
    private int[][] locations;
    private char winnerChar;
    private boolean Winner;

    public Winner(int[][] locations, char winnerChar, boolean Winner) {
        this.winnerChar = winnerChar;
        this.locations = locations;
        this.Winner = Winner;
    }

    public int[][] getLocations() {
        return locations;
    }

    public void setLocations(int[][] locations) {
        this.locations = locations;
    }

    public char getWinnerChar() {
        return winnerChar;
    }

    public void setWinnerChar(char winnerChar) {
        this.winnerChar = winnerChar;
    }

    public boolean isWinner() {
        return Winner;
    }

    public void setWinner(boolean winner) {
        this.Winner = winner;
    }

}
