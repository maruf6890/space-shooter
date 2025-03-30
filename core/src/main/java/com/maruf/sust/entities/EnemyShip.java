package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.Timer;
import com.maruf.sust.Main;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.weapen.EnemyBullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class EnemyShip {
    Main game;
    public float x, y;
    public float size;
    protected float speedX, speedY;
    protected float hp;
    private float cash;
    private float point;
    protected Texture img;
    protected boolean isAlive;
    protected float damage;
    protected float durability;
    protected Rectangle bound;
    public  ArrayList <EnemyBullet> bullets= new ArrayList<>();


    public EnemyShip(Main game, float x, float y, float size, float speedX, float speedY, float durability, Texture img) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speedX = speedX;
        this.speedY = speedY;
        this.hp = 100;
        this.img = img;
        this.isAlive = true;
        this.durability = durability;
        this.bound = new Rectangle((int) x, (int) y, (int) this.size, (int) this.size);
        startShooting();

    }
    public float getPoint(){return this.point;}
    public void setPoint(float p){this.point=p;}
    public float getCash(){return this.cash;}
    public void setCash(float c){this.cash=c;}
    // Update enemy position and behavior
    public abstract void update(float delta);

    // Render enemy on screen
    public void render(float delta) {

    }



    // Enemy takes damage
    public void takeDamage(float damage) {
        hp -= damage;

        if (hp <= 0) {
            isAlive = false;
            game.currentScore+=10;
            game.currentCash+= 1000;
            onDestroy();
            System.out.println("score" +game.currentScore);
        }
        System.out.println(this.hp);

    }


    public void isCollided() {
        if (this.bound.overlaps(game.alphaShip.bound)) {
            takeDamage(20f);
            game.alphaShip.gettingDamage(5);

        }
    }

    // Called when enemy is destroyed (Override for effects)
    protected void onDestroy() {
        this.isAlive=false;
        this.img.dispose();
    }




    public abstract void fireBullet();

    public void startShooting() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (isAlive) {
                    fireBullet();
                }
            }
        }, 0, 5);
    }
    public void updateBulletsPosition(float delta) {
        Iterator<EnemyBullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {

            EnemyBullet b = iterator.next();
            b.updatePosition(delta);

            if (b.isHit(game.alphaShip.bound)) {
                game.alphaShip.gettingDamage(b.getDamageValue());
                System.out.println(b.getDamageValue());
                iterator.remove();
                continue;

            }

            if (b.isOutOfScreen()) {
                iterator.remove();
            }
        }
    }

    public void renderBullets(float delta){
        for(EnemyBullet bullet: bullets){
            bullet.renderBullet(delta);
        }
    }


    public boolean isAlive() {
        return isAlive;
    }

    public boolean isOutOfScreen() {
        return x < -size || x > game.WIDTH + size || y < -size || y > game.HEIGHT + size;
    }
}
