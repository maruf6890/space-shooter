package com.maruf.sust;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maruf.sust.entities.PowerUps;
import com.maruf.sust.entities.SpaceShip;
import com.maruf.sust.screen.GameOverScreen;
import com.maruf.sust.screen.GameScreen;
import com.maruf.sust.screen.TitleScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public float currentScore=0;
    public float currentCash=0;
    public float totalCash=0;
    public float HighestScore=0;
    public float speedMultiplayer=1;
   //public TitanShield titanShield = new TitanShield(this);
    public PowerUps currentPowerUps;

   //SpaceShip
   public  SpaceShip alphaShip;



    @Override
    public void create() {
        batch = new SpriteBatch();  //
        alphaShip= new SpaceShip(this,"Alpha Ship",300,0,120,0,0,new Texture("image/ship/ship5.png"),0,true);


        this.setScreen(new TitleScreen(this));
    }

    @Override
    public void render() {
        super.render(); // ✅ Corrected infinite recursion issue
    }

    @Override
    public void dispose() {
        batch.dispose(); // ✅ Removed unused image.dispose()
    }
}
