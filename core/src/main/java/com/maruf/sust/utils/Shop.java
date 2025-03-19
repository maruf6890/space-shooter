package com.maruf.sust.utils;

import com.maruf.sust.Main;
import com.maruf.sust.entities.SpaceShip;

import java.util.ArrayList;

public class Shop {
    Main game;
    Account playerAccount;
    ArrayList<SpaceShip> spaceShips ;

    public Shop(Main game, Account ac) {
        this.game = game;
        this.playerAccount = ac;



    }

}
