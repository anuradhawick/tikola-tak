package com.example.anuradha.app1.GameAI;

public class ComputerPlayer {

    private char[][] gameBoard;
    private char cpuChar;
    private char userChar;
    private char currPlayer;
    private Board board;
    int diffLevel;

    public ComputerPlayer(Board gameBoard, char cpuChar, int diffLevel) {
        this.gameBoard = gameBoard.getBoard();
        this.cpuChar = cpuChar;
        this.currPlayer = cpuChar;
        this.userChar = (cpuChar == 'X') ? 'O' : 'X';
        this.board = gameBoard;
        this.diffLevel = diffLevel;
    }

    public boolean play() {
        //if the player has already won, no move, just quit
        if(board.getWinner().isWinner()){
            return false;
        }
        //check for a possibility of win and fill the gap
        else if (this.isEmpty()) {
            int i = (int) (Math.random() * 3);
            int j = (int) (Math.random() * 3);
            gameBoard[i][j] = cpuChar;
            return false;
        } else if (this.checkWin()) {
            System.out.println("Computer Won!!");
            return true;
        } else if (this.checkOpponentWin()) {
            System.out.println("Blocked the opponent");
            return false;
        } else {
            DumbMove dm = new DumbMove(gameBoard, cpuChar);
            dm.move(diffLevel);
            return false;
        }
    }

    private boolean isEmpty() {
        for (char[] arr : gameBoard) {
            for (char i : arr) {
                if (!(i == '-')) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        //horizontal check
        int cpuCharCount = 0, blankCount = 0;
        for (char[] arr : gameBoard) {
            for (char i : arr) {
                if (i == '-') {
                    blankCount++;
                } else if (i == cpuChar) {
                    cpuCharCount++;
                }
            }
            if (cpuCharCount == 2 && blankCount == 1) {
                for (int itr = 0; itr < 3; itr++) {
                    arr[itr] = cpuChar;
                }
                return true;
            }
            cpuCharCount = 0;
            blankCount = 0;
        }
        //vertical check
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][j] == '-') {
                    blankCount++;
                } else if (gameBoard[i][j] == cpuChar) {
                    cpuCharCount++;
                }
            }
            if (cpuCharCount == 2 && blankCount == 1) {
                for (int itr = 0; itr < 3; itr++) {
                    gameBoard[itr][j] = cpuChar;
                }
                return true;
            }
            cpuCharCount = 0;
            blankCount = 0;
        }
        //diagonal check
        //0,0 to 2,2
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][i] == '-') {
                blankCount++;
            } else if (gameBoard[i][i] == cpuChar) {
                cpuCharCount++;
            }
        }
        if (cpuCharCount == 2 && blankCount == 1) {
            for (int itr = 0; itr < 3; itr++) {
                gameBoard[itr][itr] = cpuChar;
            }
            return true;
        }
        cpuCharCount = 0;
        blankCount = 0;
        //diagonal check
        //0,2 to 2,0
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][Math.abs(i - 2)] == '-') {
                blankCount++;
            } else if (gameBoard[i][Math.abs(i - 2)] == cpuChar) {
                cpuCharCount++;
            }
        }
        if (cpuCharCount == 2 && blankCount == 1) {
            for (int itr = 0; itr < 3; itr++) {
                gameBoard[itr][Math.abs(itr - 2)] = cpuChar;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpponentWin() {
        //horizontal check
        int userCharCount = 0, blankCount = 0;
        for (char[] arr : gameBoard) {
            for (char i : arr) {
                if (i == '-') {
                    blankCount++;
                } else if (i == userChar) {
                    userCharCount++;
                }
            }
            if (userCharCount == 2 && blankCount == 1) {
                for (int itr = 0; itr < 3; itr++) {
                    arr[itr] = arr[itr] == '-' ? cpuChar : arr[itr];
                }
                return true;
            }
            userCharCount = 0;
            blankCount = 0;
        }
        //vertical check
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][j] == '-') {
                    blankCount++;
                } else if (gameBoard[i][j] == userChar) {
                    userCharCount++;
                }
            }
            if (userCharCount == 2 && blankCount == 1) {
                for (int itr = 0; itr < 3; itr++) {
                    gameBoard[itr][j] = (gameBoard[itr][j] == '-') ? cpuChar : gameBoard[itr][j];
                }
                return true;
            }
            userCharCount = 0;
            blankCount = 0;
        }
        //diagonal check
        //0,0 to 2,2
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][i] == '-') {
                blankCount++;
            } else if (gameBoard[i][i] == userChar) {
                userCharCount++;
            }
        }
        if (userCharCount == 2 && blankCount == 1) {
            for (int itr = 0; itr < 3; itr++) {
                gameBoard[itr][itr] = (gameBoard[itr][itr] == '-') ? cpuChar : gameBoard[itr][itr];
            }
            return true;
        }
        userCharCount = 0;
        blankCount = 0;
        //diagonal check
        //0,2 to 2,0
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][Math.abs(i - 2)] == '-') {
                blankCount++;
            } else if (gameBoard[i][Math.abs(i - 2)] == userChar) {
                userCharCount++;
            }
        }
        if (userCharCount == 2 && blankCount == 1) {
            for (int itr = 0; itr < 3; itr++) {
                gameBoard[itr][Math.abs(itr - 2)] = gameBoard[itr][Math.abs(itr - 2)] == '-' ? cpuChar : gameBoard[itr][Math.abs(itr - 2)];
            }
            return true;
        } else {
            return false;
        }
    }
}
