package com.maruf.sust.weapen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;

public class EnemyBullet extends Bullet {
    private float directionX, directionY; // Direction towards player
    float shipX,shipY;
    public EnemyBullet(Main game, float x, float y, float speed, float size, Texture img) {
        super(game, x, y, 0, 0, size, img);
        this.shipX=game.alphaShip.x;
        this.shipY= game.alphaShip.y;
        calculateDirection(x, y,shipX,shipY, speed);
    }

    public EnemyBullet(Main game, float x, float y, float speed, float size, Animation<TextureRegion> bulletAnimation) {
        super(game, x, y, 0, 0, size, bulletAnimation);
        calculateDirection(x, y, game.alphaShip.x,game.alphaShip.y, speed);
    }

    // Calculate the direction vector towards the player's initial position
    private void calculateDirection(float startX, float startY, float playerX, float playerY, float speed) {
        float dx = playerX - startX;
        float dy = playerY - startY;
        float length = (float) Math.sqrt(dx * dx + dy * dy);

        if (length != 0) {
            directionX = (dx / length) * speed;
            directionY = (dy / length) * speed;
        }

        // Assign computed speed values
        this.speedX = directionX;
        this.speedY = directionY;
    }



    @Override
    public boolean isHit(Rectangle targetBound) {
        return this.bound.overlaps(targetBound);
    }

    @Override
    public void updatePosition(float delta) {
        x += speedX * delta;
        y += speedY * delta;
        isOutScreen();
        this.bound.setPosition(this.x, this.y);
    }

}
