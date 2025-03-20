package com.maruf.sust.entities;


import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;

public class Boss extends EnemyShip {
    private float standTime = 5f; // Time in seconds that the boss will stand still
    private float moveTime = 25f; // Time in seconds before moving backward
    private float reappearTime = 5f; // Time in seconds before reappearing after moving backward
    private float timePassed = 0f; // Time counter for the various states
    private boolean isStanding = true; // To check if the boss is standing or moving
    private boolean isMovingBackward = false; // To check if the boss is moving backward

    public Boss(Main game, float x, float y, float size, float speedX, float speedY, float durability, Texture img) {
        super(game, x, y, size, speedX, speedY, durability, img);
        this.x = game.WIDTH / 2 - size / 2;  // Start in the center of the screen
        this.y = game.HEIGHT - size;  // At the bottom of the screen
        this.speedX = 0;  // Initially not moving horizontally
        this.speedY = 0;  // Not moving vertically at the start
    }

    @Override
    public void update(float delta) {
        timePassed += delta;

        if (isStanding) {
            if (timePassed >= standTime) {
                isStanding = false;  // Start moving after standing for some time
                timePassed = 0f;  // Reset the timer
            }
        } else if (!isMovingBackward) {
            if (timePassed >= moveTime) {
                isMovingBackward = true;  // Start moving backward
                timePassed = 0f;  // Reset the timer
                speedX = -2f;  // Move backward (negative x direction)
            }
        } else {
            if (timePassed >= reappearTime) {
                isMovingBackward = false;  // Stop moving backward
                isStanding = true;  // Stand again after reappearing
                timePassed = 0f;  // Reset the timer
                speedX = 0f;  // Stop horizontal movement
            }
        }

        x += speedX * delta * 60;  // Apply horizontal movement

        if (timePassed >= 5f) {  // Shoot every 5 seconds
            fireBullet();
            timePassed = 0f;  // Reset the shooting timer
        }
    }

    @Override
    public void render() {
        game.batch.draw(img, this.x, this.y, this.size, this.size);  // Render the boss
    }

    @Override
    public void fireBullet() {
   }
}
