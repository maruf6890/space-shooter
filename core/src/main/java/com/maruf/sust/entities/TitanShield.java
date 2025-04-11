package com.maruf.sust.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.maruf.sust.Main;

public class TitanShield extends PowerUps{
    Music shieldMusic;

    static int level=0;
    public TitanShield(Main game,Texture img){
        super(game,game.alphaShip,"Titan Shield",32,32, img);
        shieldMusic= Gdx.audio.newMusic(Gdx.files.internal("Audio/Spark.mp3"));

    }


    @Override
  public   void onActive() {
        ship.setHasShield(true);
        shieldMusic.play();
        if(level==1){
            ship.setShieldStrength(0.3f);
        }else if(level==2){
            ship.setShieldStrength(0.4f);
        }else{
            ship.setShieldStrength(0.5f);
        }
        System.out.println("Shield is activated");

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                ship.setHasShield(false);
                ship.setShieldStrength(0);
                shieldMusic.pause();
                shieldMusic.dispose();
                game.currentPowerUps=null;
                System.out.println("Shield is Deactivated");
            }
        },15);

    }


}
