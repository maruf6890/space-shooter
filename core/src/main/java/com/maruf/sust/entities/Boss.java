package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;
import com.maruf.sust.weapen.BossSpecialBullet;
import com.maruf.sust.weapen.EnemyBullet;

public class Boss extends EnemyShip{
    Main game;
    public static  boolean notSeenBefore=true;


    public Boss(Main game){

        super(game,game.WIDTH/2-100,game.HEIGHT+100,100,20,0.3f,0.5f,new Texture("image/ship/ship8.png"));

        this.game= game;
        this.isAlive=true;
        setPoint(10.5f);
        setCash(1000);

    }
    @Override
    public void update(float delta) {




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
        bullets.add(new EnemyBullet(game,this.x,this.y,80,50,8,new Texture("image/effect/laser/12.png")));
        fireSpecial();
    }
    public void fireSpecial() {
        bullets.add(new BossSpecialBullet(game, x, y, 400, 32, 2, -90, new Texture("image/effect/laser/31.png")));
        bullets.add(new BossSpecialBullet(game, x, y, 400, 32, 2, -75, new Texture("image/effect/laser/31.png")));
        bullets.add(new BossSpecialBullet(game, x, y, 400, 32, 2, -105, new Texture("image/effect/laser/31.png")));
    }

}
