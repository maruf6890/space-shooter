package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;

public class ZigZagShip extends EnemyShip {
    private float angle; // Used for circular movement
    private float radius; // Expands over time for spiral effect
    private float spiralSpeed;
    private float downwardSpeed;

    public ZigZagShip(Main game) {
        super(game, game.alphaShip.x, game.HEIGHT + 100, 50, 20, 0.2f, 0.2f, new Texture("image/ship/ship2.png"));

        this.angle = 0; // Start angle
        this.radius = 10; // Initial radius
        this.spiralSpeed = 0.2f; // Speed of rotation
        this.downwardSpeed = 0.2f; // Speed of moving downward
        this.game = game;
    }

    @Override
    public void update(float delta) {
        angle += spiralSpeed * delta; // Rotate gradually
        radius += 0.5f * delta * 60;  // Slowly increase radius (spiral effect)

        // Set position based on circular motion
        this.x = (float) (game.alphaShip.x + radius * Math.cos(angle));
        this.y -= downwardSpeed * delta * 60; // Move downward

        // Remove the ship when it moves off-screen
        if (this.y <= -this.size) {
            isAlive = false;
        }
    }

    @Override
    public void render() {
        game.batch.draw(img, this.x, this.y, size, size);
    }

    @Override
    public void fireBullet() {
        // Implement bullet shooting logic if needed
    }
}
