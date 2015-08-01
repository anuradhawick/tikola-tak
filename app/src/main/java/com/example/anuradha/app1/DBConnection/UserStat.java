package com.example.anuradha.app1.DBConnection;

/**
 * Created by Ravidu on 8/1/2015.
 */
public class UserStat {

    /*
    * This class is used to store the user statistics id is the primary key,
    * mode is the one whether multiplayer, single player or over network assign any integer value for it
    * player variable store the player's name
    * difficulty level store the difficulty level
    * wins store the no. of wins
    * losses store the no. of losses
    * */
    private int id;
    private int mode;
    private String player;
    private String difficulty;
    private int wins;
    private int losses;

    public UserStat(int mode, String player, String difficulty, int wins, int losses) {
        this.setMode(mode);
        this.setPlayer(player);
        this.setDifficulty(difficulty);
        this.setWins(wins);
        this.setLosses(losses);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

}