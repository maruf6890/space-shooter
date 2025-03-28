package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;
import com.maruf.sust.weapen.EnemyBullet;

public class SineShip extends EnemyShip{
    Main game;

    private float waveSpeed;
    private float waveAmplitude;
    private float time;
    private float axis;

    public SineShip(Main game){

        super(game,game.alphaShip.x,game.HEIGHT+100,50,20,0.3f,0.2f,new Texture("image/ship/ship_3.png"));
        this.waveSpeed = 0.3f;
        this.waveAmplitude = 300f;
        this.time = 0;
        this.game= game;
        this.isAlive=true;
        this.axis= game.alphaShip.y;
        setPoint(10.5f);
        setCash(1000);

    }
    @Override
    public void update(float delta) {
        time += delta;


        //this.x = (float) (this.axis- this.size  + waveAmplitude * Math.sin(time * waveSpeed));

        if(this.y>500){
            this.y -= speedY * delta * 60;
            this.bound.setPosition(this.x,this.y);
        }



        if (this.y< -this.size) {
            isAlive = false;
        }
        this.updateBulletsPosition(delta);
    }

    @Override
    public void render(float delta) {


        if(isAlive()) game.batch.draw(img, this.x, this.y, size, size);
        this.renderBullets(delta);

    }

    @Override
    public void fireBullet() {
        bullets.add(new EnemyBullet(game,this.x,this.y,80,50,new Texture("image/effect/laser/03.png")));
    }

}
