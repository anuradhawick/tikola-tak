package com.example.anuradha.app1.GameAI;

import com.example.anuradha.app1.Effects.Winner;

public class Board {

    private char[][] gameBoard;

    public Board() {
        gameBoard = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    }

    public char[][] getBoard() {
        return this.gameBoard;
    }

    public boolean setCell(int i, int j, char c) {
        if (gameBoard[i][j] != '-') {
            return false;
        }
        gameBoard[i][j] = c;
        return true;
    }

    public boolean isGameOver() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public Winner getWinner() {
        //horizontal check
        int[][] locations;
        int x_count = 0, o_count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == 'X') {
                    x_count++;
                } else if (gameBoard[i][j] == 'O') {
                    o_count++;
                }
            }
            if (o_count == 3) {
                locations = new int[][]{{i, 0}, {i, 1}, {i, 2}};
                Winner winner = new Winner(locations, 'O', true);
                return winner;
            } else if (x_count == 3) {
                locations = new int[][]{{i, 0}, {i, 1}, {i, 2}};
                Winner winner = new Winner(locations, 'X', true);
                return winner;
            } else {
                x_count = 0;
                o_count = 0;
            }
        }
        //vertical check
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][j] == 'O') {
                    o_count++;
                } else if (gameBoard[i][j] == 'X') {
                    x_count++;
                }
            }
            if (o_count == 3) {
                locations = new int[][]{{0, j}, {1, j}, {2, j}};
                Winner winner = new Winner(locations, 'O', true);
                return winner;
            } else if (x_count == 3) {
                locations = new int[][]{{0, j}, {1, j}, {2, j}};
                Winner winner = new Winner(locations, 'X', true);
                return winner;
            } else {
                x_count = 0;
                o_count = 0;
            }
        }
        //diagonal check
        //0,0 to 2,2
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][i] == 'X') {
                x_count++;
            } else if (gameBoard[i][i] == 'O') {
                o_count++;
            }
        }
        if (o_count == 3) {
            locations = new int[][]{{0, 0}, {1, 1}, {2, 2}};
            Winner winner = new Winner(locations, 'O', true);
            return winner;
        } else if (x_count == 3) {
            locations = new int[][]{{0, 0}, {1, 1}, {2, 2}};
            Winner winner = new Winner(locations, 'X', true);
            return winner;
        } else {
            x_count = 0;
            o_count = 0;
        }
        //diagonal check
        //0,2 to 2,0
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][Math.abs(i - 2)] == 'X') {
                x_count++;
            } else if (gameBoard[i][Math.abs(i - 2)] == 'O') {
                o_count++;
            }
        }
        if (o_count == 3) {
            locations = new int[][]{{0, 2}, {1, 1}, {2, 0}};
            Winner winner = new Winner(locations, 'O', true);
            return winner;
        } else if (x_count == 3) {
            locations = new int[][]{{0, 2}, {1, 1}, {2, 0}};
            Winner winner = new Winner(locations, 'X', true);
            return winner;
        } else {
            //There is no winner at all
            Winner winner = new Winner(null, ' ', false);
            return winner;
        }
    }

}
