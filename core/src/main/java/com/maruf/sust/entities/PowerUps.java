package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;

import java.util.Random;

abstract class PowerUps {
    Main game;
    String name;
    int level;  // Not static so each power-up can have a unique level
    Texture img;
    float x, y;
    float speed;
    Random rand;
    float size;

    SpaceShip ship;
    Rectangle bound;

    // Constructor
    PowerUps(Main game, SpaceShip ship, String name, float speed, float size, String imgLocation) {
        this.game = game;
        this.ship = ship;
        this.name = name;
        this.x = new Random().nextFloat() * 1080;  // Random position within screen width
        this.y = 800;  // Initial Y position
        this.speed = speed;
        this.img = new Texture(imgLocation);
        this.size = size;
        this.level = 1;  // Each power-up can have its own level now
        this.bound = new Rectangle(this.x, this.y, this.size, this.size);
        this.rand = new Random();  // Initialize Random object
    }

    // Abstract method to define behavior when power-up is activated
    abstract void onActive();

    // Control power-ups (check for collision, move down)
    public void controlPowerUps(float delta) {
        if (this.bound.overlaps(ship.bound)) {
            onActive();
            dispose();
        }

        // Move power-up down the screen
        this.y -= delta * speed;

        // Remove the power-up if it goes off the screen
        if (this.y + this.size < 0) {
            dispose();  // Dispose of the texture to free up memory
        }
    }

    // Render the power-up
    public void renderPowerUps() {
        game.batch.draw(img, this.x, this.y);
    }

    // Dispose of the resources (texture)
    public void dispose() {
        if (img != null) {
            img.dispose();
        }
    }
}
