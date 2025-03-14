package com.maruf.sust.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class SpaceShip {
    public final String name;
    static  int activeShip;
    private float speed; // basesSpeed to forward and backward
    private float fuel; // for Acchellarion




    //manage shilding

    private float shieldStrength;
    private boolean hasShield=false;
    private float repair;
    private int damage; //own damage or heart
    private boolean isRunning;
    private  int weaponLevel;
    Texture shipimg;
    float x, y,size;
    Rectangle bound;
    // add wapen and power weapon here


    // Constructor
    public SpaceShip(String name, int type, float speed,float x, float y,String imgLocation) {
        this.name = name;
        this.activeShip = type; //db
        this.speed = speed;
        this.fuel = 0;
        this.shieldStrength=0  ;
        this.repair = (activeShip==1)?0.5f:(activeShip==2)? 0.6f:0.7f;
        this.damage = 0;
        this.isRunning = false;
        this.weaponLevel=1;
        shipimg= new Texture(imgLocation);
        this.size=100;
        bound= new Rectangle(x,y,this.size, this.size);

    }

    // Getters
    public String getName() { return name; }

    public static int getActiveShip() {
        return activeShip;
    }

    public float getSpeed() { return speed; }
    public float getFuel() { return fuel; }
    public float getShieldStrength() { return shieldStrength; }

    public void setShieldStrength(float shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public int getDamage() { return damage; }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public boolean isRunning() { return isRunning; }






    //special power

    // Accelerate (Consumes Fuel)
    public void accelerate(float delta){
        if(fuel>0){
            fuel-=5;
            //accessalaration
            //add bg speed
        }

    }




    public void takeDamage(int amount) {
        if (hasShield) {
            damage+= amount * (0.90f)*this.shieldStrength;
        }
        damage += amount ;
       if(damage==100){
           //explode+ game over
       }
    }

    // Repair the ship
    public void repair(int repairAmount) {
        damage -= repairAmount;
        if (damage < 0) damage = 0;
    }


    public void refuel(float amount) {
        fuel += amount;
    }




    // attack




    // Ship Status
    public void getStatus() {

    }
}

