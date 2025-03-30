package com.maruf.sust.entities;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;
import com.maruf.sust.weapen.EnemyBullet;

public class ZigZagShip extends EnemyShip {
    private float angle; // Used for circular movement
    private float radius; // Expands over time for spiral effect
    private float spiralSpeed;
    private float downwardSpeed;
    private float axis;

    public ZigZagShip(Main game) {
        super(game, game.alphaShip.x, game.HEIGHT + 100, 50, 20, 0.2f, 0.2f, new Texture("image/ship/ship_6.png"));

        this.angle = 0; // Start angle
        this.radius = 10; // Initial radius
        this.spiralSpeed = 0.2f; // Speed of rotation
        this.downwardSpeed = 0.2f; // Speed of moving downward
        this.game = game;
        this.axis= game.alphaShip.x;
        setPoint(8.5f);
        setCash(1000);
    }

    @Override
    public void update(float delta) {
        angle += spiralSpeed * delta; // Rotate gradually
        radius += 0.5f * delta * 60;  // Slowly increase radius (spiral effect)

        // Set position based on circular motion

        this.x = (float) (axis + radius * Math.cos(angle));
        this.y -= downwardSpeed * delta * 60; // Move downward
        this.bound.setPosition(this.x,this.y);
        // Remove the ship when it moves off-screen
        if (this.y <= -this.size) {
            isAlive = false;
        }
        this.updateBulletsPosition(delta);
    }

    @Override
    public void render(float delta) {

        if(this.isAlive())game.batch.draw(img, this.x, this.y, size, size);
        this.renderBullets(delta);
    }

    @Override
    public void fireBullet() {
        bullets.add(new EnemyBullet(game,this.x,this.y,80,50,5,new Texture("image/effect/laser/08.png")));

    }
}
