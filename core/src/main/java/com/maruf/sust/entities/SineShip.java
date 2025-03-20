package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;

public class SineShip extends EnemyShip{
    Main game;
    private float waveSpeed;
    private float waveAmplitude;
    private float time;
    private float axis;

    public SineShip(Main game){

        super(game,game.alphaShip.x,game.HEIGHT+100,50,20,0.3f,0.2f,new Texture("image/ship/ship2.png"));
        this.waveSpeed = 0.3f;
        this.waveAmplitude = 300f;
        this.time = 0;
        this.game= game;
        this.isAlive=true;
        this.axis= game.alphaShip.y;

    }
    @Override
    public void update(float delta) {
        time += delta;


        //this.x = (float) (this.axis- this.size  + waveAmplitude * Math.sin(time * waveSpeed));
        this.y -= speedY * delta * 60;

        if (this.y< -this.size) {
            isAlive = false;  // Destroy if out of bounds
        }
    }

    @Override
    public void render() {


        if(isAlive()) game.batch.draw(img, this.x, this.y, size, size);

    }

    @Override
    public void fireBullet() {

    }

}
