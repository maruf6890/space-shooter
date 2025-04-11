package com.maruf.sust.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.maruf.sust.Main;

public class SonicSpeed extends PowerUps{


    Music speedMusic;

    static int level=0;
    public SonicSpeed(Main game, Texture img){
        super(game,game.alphaShip,"Sonic Speed",32,32, img);
        speedMusic= Gdx.audio.newMusic(Gdx.files.internal("Audio/loop.mp3"));
        speedMusic.setLooping(false);


    }


    @Override
  public   void onActive() {
       game.alphaShip.setHasAcceleration(true);
        speedMusic.play();
       game.speedMultiplayer=15;
        System.out.println("Sonic is activated");

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                game.alphaShip.setHasAcceleration(false);
                game.currentPowerUps=null;
                game.speedMultiplayer=1;

                speedMusic.pause();
                speedMusic.dispose();
                System.out.println("Sonic is Deactivated");
            }
        },15);

    }




}
