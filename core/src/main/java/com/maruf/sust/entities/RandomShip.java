package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;
import com.maruf.sust.weapen.EnemyBullet;

import java.util.Random;

public class RandomShip extends EnemyShip {
    private Random random;
    private Main game;
    private float stateTime = 0f;
    private boolean isStanding = true;
    private static final float STAND_TIME = 5f;
    private static final float MOVE_TIME = 5f;

    public RandomShip(Main game) {
        super(game,0,0,40,0.5f,0.5f,0.1f,new Texture("image/ship/ship_1.png"));
        this.game = game;
        this.random = new Random();
        spawnOutsideScreen();
        setPoint(15.5f);
        setCash(1500);
    }

    private void spawnOutsideScreen() {
        int side = random.nextInt(3);

        switch (side) {
            case 0:
                x = random.nextInt(game.WIDTH);
                y = game.HEIGHT + 50;
                speedX = random.nextInt(3) - 1;
                speedY = -(random.nextInt(3) + 1);
                break;
            case 1:
                x = -50;
                y = random.nextInt(game.HEIGHT) + 200;
                speedX = random.nextInt(3) + 1;
                speedY = random.nextInt(3) - 1;
                break;
            case 2:
                x = game.WIDTH + 50;
                y = random.nextInt(game.HEIGHT) + 200;
                speedX = -(random.nextInt(3) + 1);
                speedY = random.nextInt(3) - 1;
                break;
        }
    }

    @Override
    public void update(float delta) {
        stateTime += delta;

        if (isStanding) {
            if (y <= game.alphaShip.y || stateTime >= STAND_TIME) {
                isStanding = false;
                stateTime = 0;
            }
        } else {
            this.x += speedX * delta * 60;
            this.y += speedY * delta * 60;

            if (stateTime >= MOVE_TIME) {
                stateTime = 0;
                isStanding = true;
                fireBullet();
            }
        }

        if (x < -100 || x > game.WIDTH + 100 || y < -100 || y > game.HEIGHT + 100) {
            isAlive = false;
        }
        this.bound.setPosition(this.x,this.y);

        this.updateBulletsPosition(delta);
    }

    @Override
    public void render(float delta) {
        if(isAlive){
            game.batch.draw(img, this.x, this.y, this.size, this.size);
        }
        this.renderBullets(delta);

    }

    @Override
    public void fireBullet() {
        bullets.add(new EnemyBullet(game,this.x,this.y,80,50,new Texture("image/effect/laser/05.png")));

    }
}
