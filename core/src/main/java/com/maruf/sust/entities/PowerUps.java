package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;

import java.util.Random;

public  abstract class PowerUps {
    Main game;
    String name;
    int level;  // Not static so each power-up can have a unique level
    Texture img;
    float x, y;
    float speed;
    Random rand;
    float size;
    public  boolean isActive;
    SpaceShip ship;
    Rectangle bound;

    // Constructor
    PowerUps(Main game, SpaceShip ship, String name, float speed, float size,  Texture img) {
        this.game = game;
        this.ship = ship;
        this.name = name;
        this.x = new Random().nextFloat() * 1080;
        this.y = 800;
        this.speed = speed;
        this.img = img;
        this.size = size;
        this.level = 1;
        this.bound = new Rectangle(this.x, this.y, this.size, this.size);
        this.rand = new Random();
        isActive= true;
    }


    abstract void onActive();


    public void controlPowerUps(float delta) {
        isOverlap();


        this.y -= delta * speed;

        this.bound.setPosition(this.x,this.y);
        if (this.y + this.size < 0) {
            isActive=false;
            dispose();
        }

    }

    // Render the power-up
    public void renderPowerUps() {
       if(isActive) game.batch.draw(img, this.x, this.y,this.size,this.size);
    }

    public void isOverlap(){
        if(this.bound.overlaps(ship.bound)){
            if(isActive) onActive();
            isActive=false;
            dispose();


        }
    }

    // Dispose of the resources (texture)
    public void dispose() {
        if (img != null) {
            img.dispose();
        }
    }
}
