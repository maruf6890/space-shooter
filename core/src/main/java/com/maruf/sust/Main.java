package com.maruf.sust;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maruf.sust.entities.PowerUps;
import com.maruf.sust.entities.SpaceShip;
import com.maruf.sust.screen.TitleScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    //game
    public SpriteBatch batch;
    public  final int WIDTH = 1280;
    public  final int HEIGHT = 720;
    public float speedMultiplayer=1;
    //todo  total cash collection and highest  score will be added save cash and highest score

    public float currentScore=0;
    public float currentCash=0;

    //game entity
    public PowerUps currentPowerUps;
    public  SpaceShip alphaShip;

    @Override
    public void create() {
        batch = new SpriteBatch();
        alphaShip= new SpaceShip(this,"Alpha Ship",300,0,120,this.WIDTH/2f-60,0,new Texture("image/ship/ship5.png"));
        this.setScreen(new TitleScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
