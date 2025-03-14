package com.maruf.sust.utils;


import com.maruf.sust.entities.SpaceShip;

public class Account {
    private int score;
    private int highScore;
    private int balance;

    public SpaceShip currentShip;


    // Constructor (Optional, for initialization)
    public Account() {
        this.score = 0;
        this.highScore = 0;
        this.balance = 0;
    }

    // Getter methods
    public int getHighScore() {
        return highScore;
    }

    public int getScore() {
        return score;
    }

    public int getBalance() {
        return balance;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }


}

