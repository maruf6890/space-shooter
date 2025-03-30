package com.maruf.sust.weapen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;

public class PlayerBullet extends Bullet {

    public PlayerBullet(Main game, float x, float y, float speedX, float speedY, float size,float damage, Texture img) {
        super(game, x, y, speedX, speedY, size,damage, img);
    }

    public PlayerBullet(Main game, float x, float y, float speedX, float speedY, float size,float damage, Animation<TextureRegion> bulletAnimation) {
        super(game, x, y, speedX, speedY, size,damage, bulletAnimation);
    }


    @Override
    public  boolean isHit(Rectangle targetBound) {
        return this.bound.overlaps(targetBound);

    }

    @Override
    public  void updatePosition(float delta) {
        this.y+= delta*speedY;
        this.bound.setPosition(this.x, this.y);
    }
}
