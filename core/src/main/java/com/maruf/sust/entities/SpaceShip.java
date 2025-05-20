package com.maruf.sust.entities;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.maruf.sust.Main;
import com.maruf.sust.screen.GameOverScreen;
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
    public final String name;
    public float x, y, size, speed;
    public Rectangle bound;
    Thruster t1;
    public boolean isAlive;
    ArrayList<PlayerBullet> bullets= new ArrayList<>();
    private boolean hasAcceleration;
    private float sonicSpeed;

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

    //sound
    Sound shootSound_01,explode;
    public SpaceShip(Main game,String name, int type, float speed, float size, float x, float y, Texture imgLocation) {
        this.game= game;
        this.name = name;
        this.speed = speed;
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
        t1 = new Thruster(game,x+50,y);
        shootSound_01= Gdx.audio.newSound(Gdx.files.internal("Audio/pew.mp3"));
        explode= Gdx.audio.newSound(Gdx.files.internal("Audio/explo.mp3"));
    }


    public String getName() {
        return name;
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

    public void setMechaHealth(float mechaHealth) {
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
        this.mechaHealth -= ((isHasShield())? 0: damage);
        if(mechaHealth<0){
            explode.play(0.8f);
            this.isAlive=false;
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
    }

    public  void repairShip(float value){
        this.mechaHealth+= value;
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
            shootSound_01.play();
            if(hasBeastMode){
                bullets.add(new PlayerBullet(game,this.x+this.size/2-25,this.y+this.size/2,20,300,50,30,new Texture("image/effect/laser/14.png")));
            }else{

                bullets.add(new PlayerBullet(game,this.x+25,this.y+this.size,80,300,80,20,new Texture("image/effect/laser/01.png" ) ));
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
                e.takeDamage(b.getDamageValue()*((hasBeastMode)?2:1));

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
        explode.dispose();
    }



}

    //
