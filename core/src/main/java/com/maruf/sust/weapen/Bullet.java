package com.maruf.sust.weapen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;

abstract class Bullet {
    protected Main game;
    protected float x, y, speedX, speedY, size;
    private boolean isAnimated = false;
    public Rectangle bound;
    private Texture img;
    private Animation<TextureRegion> bulletAnimation;
    private float stateTime = 0;
    private boolean isActive;
    private float damage;

    public Bullet(Main game, float x, float y, float speedX, float speedY, float size) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.size = size;
        this.game = game;
        this.isActive = true;
        this.bound = new Rectangle(x, y, size, size);
        this.damage=20;
    }

    public Bullet(Main game, float x, float y, float speedX, float speedY, float size, Texture img) {
        this(game, x, y, speedX, speedY, size);
        this.img = img;
    }

    public Bullet(Main game, float x, float y, float speedX, float speedY, float size, Animation<TextureRegion> bulletAnimation) {
        this(game, x, y, speedX, speedY, size);
        this.bulletAnimation = bulletAnimation;
        isAnimated = true;
    }

    public void isOutScreen(){
        if(this.x<-this.size || this.y<-this.size || this.x>game.WIDTH+ this.size || this.y> game.HEIGHT+this.size)
        {deactivate();
        this.img.dispose();
        }

    };
    public boolean isOutOfScreen(){
        return (this.x<-this.size || this.y<-this.size || this.x>game.WIDTH+ this.size || this.y> game.HEIGHT+this.size);
    }
    public float getDamageValue(){
        return this.damage;
    }

    abstract boolean isHit(Rectangle targetBound);

    public void renderBullet( float deltaTime) {
        if (!isActive) return;

        if (isAnimated && bulletAnimation != null) {
            stateTime += deltaTime;
            TextureRegion currentFrame = bulletAnimation.getKeyFrame(stateTime, true);
            game.batch.draw(currentFrame, x, y, size, size);
        } else if (img != null) {
            game.batch.draw(img, x, y, size, size);
        }


    }

    abstract void updatePosition(float delta);

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void deactivate() {
        isActive = false;
        this.img.dispose();
    }







}
