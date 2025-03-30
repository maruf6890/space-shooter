package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.maruf.sust.Main;

public class Repair extends PowerUps{


    static int level=0;
    public Repair(Main game, Texture img){
        super(game,game.alphaShip,"Titan Shield",32,32, img);
    }


    @Override
  public   void onActive() {
        game.alphaShip.setMechaHealth(game.alphaShip.getMechaHealth()+20f);
        System.out.println(game.alphaShip.getMechaHealth());
        //note might be updated
    }


}
