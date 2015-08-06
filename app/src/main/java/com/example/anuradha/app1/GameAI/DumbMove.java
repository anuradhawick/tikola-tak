package com.example.anuradha.app1.GameAI;

import java.util.ArrayList;

public class DumbMove {

    private char[][] gameBoard;
    private char cpuChar;

    public DumbMove(char[][] gameBoard, char cpuChar) {
        this.gameBoard = gameBoard;
        this.cpuChar = cpuChar;
    }

    public boolean move(int i) {
        switch (i) {
            case 1:
                return this.defensiveMove();
            case 2:
                return this.blindmove();
        }
        return false;
    }

    public boolean defensiveMove() {
        //Avoiding  possibility of intelligent move by user
        /*
         [XO-]
         [O--]
         [---]
         in the above if O put in 1,1 he gets 2 chances to win, thus avoid it!
         this will simply fill the cell with highest number of neighbour opponents
         this itself is dumb, there could be better and smarter moves
         but still the user may never win this game
         */
        int fillRow = 0;
        int fillCol = 0;
        int neighbourCount = 0;
        ArrayList<String> neighbours = new ArrayList<String>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                //if there is a opponent move near fill this
                if (!(gameBoard[i][j] == '-')) {
                    continue;
                } else if (this.getNeighbourCount(i, j) > neighbourCount) {
                    neighbours = new ArrayList<String>();
                    neighbourCount = getNeighbourCount(i, j);
                    fillRow = i;
                    fillCol = j;
                } else if(this.getNeighbourCount(i, j) == neighbourCount) {
                    neighbours.add(i + " " + j);
                }
            }
        }
        //if there are many locations with the same number of neighbours select a location randomly
        //else proceed with the only location that is available
        int size = neighbours.size();
        int rand = (int)(Math.random()*size);
        if(size != 0) {
            String[] coordinateString = neighbours.get(rand).split(" ");
            gameBoard[Integer.parseInt(coordinateString[0])][Integer.parseInt(coordinateString[1])] = cpuChar;
            return true;
        }
        gameBoard[fillRow][fillCol] = cpuChar;
        return true;
    }

    private int getNeighbourCount(int i, int j) {
        char userChar = (cpuChar == 'X') ? 'O' : 'X';
        //padding the board
        char[][] padded = new char[gameBoard.length + 2][gameBoard.length + 2];
        for (int k = 0; k < padded.length; k++) {
            for (int l = 0; l < padded.length; l++) {
                padded[k][l] = '-';

            }
        }
        //filling with real items
        for (int k = 0; k < gameBoard.length; k++) {
            for (int l = 0; l < gameBoard.length; l++) {
                padded[k + 1][l + 1] = gameBoard[k][l];
            }
        }
        int count = 0;
        i++;
        j++;
        for (int k = i - 1; k < i + gameBoard.length - 1; k++) {
            for (int l = j - 1; l < j + gameBoard.length - 1; l++) {
                if (k == i && l == j) {
                    //do nothing
                } else {
                    if (padded[k][l] == userChar) {
                        count++;
                    }
                }
            }
        }
        return count;
        //return the number of neighbouring opponents
    }

    public boolean blindmove() {
        for (int k = 0; k < gameBoard.length; k++) {
            for (int l = 1; l < gameBoard.length; l++) {
                if (gameBoard[k][l] == '-') {
                    gameBoard[k][l] = cpuChar;
                    return true;
                }
            }
        }
        return false;
    }
}
