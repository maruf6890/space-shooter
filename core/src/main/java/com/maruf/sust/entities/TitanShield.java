package com.maruf.sust.entities;

import com.badlogic.gdx.utils.Timer;
import com.maruf.sust.Main;

abstract class TitanShield extends PowerUps{


    static int level=0;
    TitanShield(Main game,SpaceShip ship, String name, float speed, float size, String imgLocation){
        super(game,ship,name,speed,size,imgLocation);
    }


    @Override
    void onActive() {
        ship.setHasShield(true);
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
                System.out.println("Shield is Deactivated");
            }
        },5);

    }

    @Override
    public void renderPowerUps() {
        super.renderPowerUps();
        //render shild image
    }
}
