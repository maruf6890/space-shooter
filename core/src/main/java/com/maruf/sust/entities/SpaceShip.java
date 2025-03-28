package com.maruf.sust.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;
import com.maruf.sust.weapen.PlayerBullet;

import java.util.ArrayList;
import java.util.Iterator;


class Thruster {
     Main game;
    private Texture[] thrusterFrames;
    Animation<TextureRegion> thrusterAnimation;
    private float animationTime = 0f;
    private float x, y;

    public Thruster(Main game,float x, float y) {
        this.x = x;
        this.y = y;
        this.game= game;
        // Load separate images
        thrusterFrames = new Texture[]{
            new Texture(Gdx.files.internal("image/ship/thruster/1/1.png")),
            new Texture(Gdx.files.internal("image/ship/thruster/1/2.png")),
            new Texture(Gdx.files.internal("image/ship/thruster/1/3.png")),
            new Texture(Gdx.files.internal("image/ship/thruster/1/4.png")),
            new Texture(Gdx.files.internal("image/ship/thruster/1/5.png"))
        };


        TextureRegion[] frames = new TextureRegion[thrusterFrames.length];
        for (int i = 0; i < thrusterFrames.length; i++) {
            frames[i] = new TextureRegion(thrusterFrames[i]);
        }

        // Create the animation (0.1s per frame)
        thrusterAnimation = new Animation<>(0.1f, frames);
    }
     public void render() {
         TextureRegion currentFrame = thrusterAnimation.getKeyFrame(animationTime, true); // Loop animation
         game.batch.draw(currentFrame, x, y,60,60);
     }

    public void update(float delta) {
        animationTime += delta;
    }
    public void setThrusterPosition(float x, float y){
        this.x=x;
        this.y= y;
    }




    public void dispose() {

        for (Texture texture : thrusterFrames) {
            texture.dispose();
        }
    }
}




public class SpaceShip {
  public Texture img;
    Main game;
    // Ship Information
    private final String name;
    private static int activeShip;
    //Texture test = new Texture("image/ship/ship5.png");
    public float x, y, size, speed;
    public Rectangle bound;
    Thruster t1;
    public boolean isAlive;

    //Bullet
    ArrayList<PlayerBullet> bullets= new ArrayList<>();





    private boolean isUnlocked;
    private  int price;

    // Acceleration
    private boolean hasAcceleration;
    private float sonicSpeed;
    private float fuel;

    // Shield
    private float shieldStrength;
    private boolean hasShield;



    // Repair
    private boolean hasRecovery;
    private float repair;
    private float mechaHealth;

    // Beast Mode
    private boolean hasBeastMode;
    private float damage;

    // Weapon Level
    private int weaponLevel;

    // Constructor


    public SpaceShip(Main game,String name, int type, float speed, float size, float x, float y, Texture imgLocation,int price, boolean isUnlocked) {
        this.game= game;
        this.name = name;
        this.activeShip = type;
        this.speed = speed;
        this.fuel = 0;
        this.shieldStrength = 0;
        this.repair = 0;
        this.damage = 0;
        img= imgLocation;
        this.hasAcceleration = false;
        this.hasBeastMode = false;
        this.hasShield = false;
        this.hasRecovery = false;
        this.weaponLevel = 1;
        this.isAlive=true;
        this.mechaHealth=100;


        this.size = size;
        this.x = x;
        this.y = y;
        this.bound = new Rectangle(x, y, this.size, this.size);
        this.price= price;
        this.isUnlocked= isUnlocked;
        t1 = new Thruster(game,x+50,y);

    }


    public String getName() {
        return name;
    }

    public static int getActiveShip() {
        return activeShip;
    }

    public void setActiveShip(int activeShip) {
        SpaceShip.activeShip = activeShip;
    }


    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public float getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(float shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public boolean isHasShield() {
        return hasShield;
    }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public boolean isHasAcceleration() {
        return hasAcceleration;
    }

    public void setHasAcceleration(boolean hasAcceleration) {
        this.hasAcceleration = hasAcceleration;
    }

    public float getSonicSpeed() {
        return sonicSpeed;
    }

    public void setSonicSpeed(float sonicSpeed) {
        this.sonicSpeed = sonicSpeed;
    }

    public boolean isHasRecovery() {
        return hasRecovery;
    }

    public void setHasRecovery(boolean hasRecovery) {
        this.hasRecovery = hasRecovery;
    }

    public float getRepair() {
        return repair;
    }

    public void setRepair(float repair) {
        this.repair = repair;
    }

    public float getMechaHealth() {
        return mechaHealth;
    }

    public void setMechaHealth(int mechaHealth) {
        this.mechaHealth = mechaHealth;
    }

    public boolean isHasBeastMode() {
        return hasBeastMode;
    }

    public void setHasBeastMode(boolean hasBeastMode) {
        this.hasBeastMode = hasBeastMode;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
    }


    //control
    public void controlShip(float delta){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoot();
        }

        //update bullets
        updateBullets(delta);
        t1.update(delta);

        // Move left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.x -= 300* delta;
        }
        // Move right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.x += 300* delta;
        }
        // Move up
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.y += 300* delta;
        }
        // Move down
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.y -= 300* delta;
        }

        // Keep player within screen bounds
        this.x = Math.max(0, Math.min(1280 - 100, this.x));
        this.y = Math.max(0, Math.min(720 -100, this.y));
        t1.setThrusterPosition(this.x+this.size/2-30,this.y-40);
        this.bound.setPosition(this.x, this.y);
    }




    //getting damage
    public void gettingDamage(float damage) {
        if (this.hasShield) {
            this.mechaHealth -= (damage - (damage * (activeShip == 1 ? 0.1f : 0.2f)) - damage * this.shieldStrength);
        } else {
            this.mechaHealth -= (damage - (damage * (activeShip == 1 ? 0.1f : 0.2f)));
        }
        if(mechaHealth<0){
            this.isAlive=false;
            dispose();
        }
    }


    //
    //ceate animation
    Animation<TextureRegion> generateAnimation(String... locations) {
        Animation<TextureRegion> animationFrames;
        TextureRegion[] textureFrames = new TextureRegion[locations.length];
        int i=0;
        for (String location : locations) {
            textureFrames[i]=new TextureRegion(new Texture(location));
            i++;
        }
        animationFrames = new Animation<>(0.1f, textureFrames);
        return animationFrames;
    }
    public void shoot(){

        if(isAlive){
            if(hasBeastMode){
                bullets.add(new PlayerBullet(game,this.x+this.size/2,this.y+this.size/2,20,20,50,new Texture("image/effect/laser/4.png")));
            }else{

                bullets.add(new PlayerBullet(game,this.x+25,this.y+this.size,20,100,80,new Texture("image/effect/laser/01.png" ) ));
            }
        }

    }

    public  void renderBullets(float delta){

        for(PlayerBullet bullet: bullets){
            bullet.renderBullet(delta);
        }
    }

    public void updateBullets(float delta) {



        Iterator<PlayerBullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            PlayerBullet bullet = iterator.next();
            bullet.updatePosition(delta);
            if (!bullet.isActive()) {
                iterator.remove(); // Remove inactive bullets
            }
        }


    }
    public void destroyEnemy(EnemyShip e){


        for(PlayerBullet b: bullets){

           if(b.isHit(e.bound)) {
               b.deactivate();
                e.takeDamage(b.getDamageValue());


           }
        }
    }







    //render ship
    public void renderShip( float delta){
    renderBullets(delta);
    if(isAlive){
        game.batch.draw(img,this.x,this.y,this.size, this.size);
        t1.render();
    }

    }




    public  void dispose(){
        t1.dispose();
        this.img.dispose();
    }



}

    //
