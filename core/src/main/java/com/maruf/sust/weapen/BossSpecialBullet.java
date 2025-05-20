package com.maruf.sust.weapen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;

public class BossSpecialBullet extends EnemyBullet {

    public BossSpecialBullet(Main game, float x, float y, float speed, float size, float damage, float angleDegrees, Texture texture) {
        // Call EnemyBullet constructor with dummy values (speed = 0), will override it next
        super(game, x, y, 0, size, damage, texture);
        setDirection(speed, angleDegrees);
    }

    public BossSpecialBullet(Main game, float x, float y, float speed, float size, float damage, float angleDegrees, Animation<TextureRegion> bulletAnimation) {
        // Call EnemyBullet constructor with dummy values (speed = 0), will override it next
        super(game, x, y, 0, size, damage, bulletAnimation);
        setDirection(speed, angleDegrees);
    }

    private void setDirection(float speed, float angleDegrees) {
        float radians = (float) Math.toRadians(angleDegrees);
        this.speedX = MathUtils.cos(radians) * speed;
        this.speedY = MathUtils.sin(radians) * speed;
    }

    @Override
    public void updatePosition(float delta) {
        x += speedX * delta;
        y += speedY * delta;
        isOutScreen();
        this.bound.setPosition(this.x, this.y);
    }

    @Override
    public boolean isHit(Rectangle targetBound) {
        return this.bound.overlaps(targetBound);
    }
}
