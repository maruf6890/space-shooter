package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.maruf.sust.Main;

public class BeastMode extends PowerUps{


    static int level=0;
    public BeastMode(Main game, Texture img){
        super(game,game.alphaShip,"Beast Mode",32,32, img);
    }


    @Override
  public   void onActive() {
        game.alphaShip.setHasBeastMode(true);

        System.out.println("Beast Mode is activated");

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                game.alphaShip.setHasBeastMode(false);
                game.currentPowerUps=null;
                System.out.println("Best Mode is Deactivated");
            }
        },10);

    }


}
