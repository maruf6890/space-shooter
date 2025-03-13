package com.maruf.sust.screen;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;

import java.util.ArrayList;
import java.util.Random;

class SpaceObject {
    float x, y;
    float proportion;
    float size;
    float speed;
    Texture img;
    private Random rand = new Random();

    SpaceObject(ArrayList<Texture> objTexture,float speed, float size) {
        this.x = rand.nextFloat() * 1080;  // Random x position
        this.y = 750;
        this.proportion = rand.nextFloat() * 0.5f + 0.5f; // Keep proportion reasonable
        this.img = objTexture.get(rand.nextInt(objTexture.size()));  // Random texture
        this.speed = proportion * speed; // Adjust speed scaling
        this.size = proportion * size; // Adjust size scaling
    }
}

public class SpaceObjects {
    int count;
    Main game;
    String name;

    ArrayList<Texture> objTexture = new ArrayList<>();
    ArrayList<SpaceObject> objectList = new ArrayList<>();
    boolean isDamagable;
    float damage;
    float speed, extraSpeed, speedRate;
    float size;
    private Random rand = new Random();

    SpaceObjects(Main game, String name,float speed, float size) {
        this.game = game;
        this.name = name;
        this.size= size;
        this.speed= speed;
        this.damage = 10;

        if (name.equals("planet")) {
            this.count = 4;
            objTexture.add(new Texture("image/object/Planet_01.png"));
            objTexture.add(new Texture("image/object/Planet_02.png"));
            objTexture.add(new Texture("image/object/Planet_03.png"));
            objTexture.add(new Texture("image/object/Planet_04.png"));
        }else if(name.equals("dust")){
            objTexture.add(new Texture("image/object/dust_1.png"));
            objTexture.add(new Texture("image/object/dust_2.png"));
        }
    }

    public void renderObject() {
        for (SpaceObject obj : objectList) {
            game.batch.draw(obj.img, obj.x, obj.y,obj.size,obj.size);
        }
    }

    public void controlObjectRender(float delta) {
        if (rand.nextInt(400) < 1) {  // ~3% chance per frame
            objectList.add(new SpaceObject(objTexture,this.speed,this.size));
        }

        for (int i = 0; i < objectList.size(); i++) {
            SpaceObject obj = objectList.get(i);
            obj.y -= obj.speed * delta;
            if (obj.y < 0) {
                objectList.remove(i);
                i--;
            }
        }
    }
}
