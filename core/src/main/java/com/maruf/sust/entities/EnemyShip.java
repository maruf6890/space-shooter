package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;
import com.badlogic.gdx.math.Rectangle;
import java.awt.*;

public abstract class EnemyShip {
    Main game;
    protected float x, y;
    protected float size;
    protected float speedX, speedY;
    protected float hp;
    protected Texture img;
    protected boolean isAlive;
    protected float damage;
    protected float durability;
    protected Rectangle bound;

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
    }

    // Update enemy position and behavior
    public abstract void update(float delta);

    // Render enemy on screen
    public void render() {
        if (isAlive) {
            // Rendering logic
        }
    }

    // Enemy takes damage
    public void takeDamage(float damage) {
        hp -= (damage - (damage * durability));
        if (hp <= 0) {
            isAlive = false;
            onDestroy();
        }
    }

    public void isCollided() {
        if (this.bound.overlaps(game.alphaShip.bound)) {
            takeDamage(20f);
            game.alphaShip.gettingDamage(20f);

        }
    }

    // Called when enemy is destroyed (Override for effects)
    protected void onDestroy() {
        // Effect logic
    }

    // Abstract method for firing bullets (Implemented by subclasses)
    public abstract void fireBullet();

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isOutOfScreen() {
        return x < -size || x > game.WIDTH + size || y < -size || y > game.HEIGHT + size;
    }
}
