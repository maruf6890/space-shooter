package com.maruf.sust.screen;

import com.badlogic.gdx.graphics.Texture;
import com.maruf.sust.Main;

import java.util.ArrayList;
import java.util.Random;

class SpaceObject {
    float x, y,proportion,size,speed;
    final Texture img;
    private Random rand = new Random();

    SpaceObject(ArrayList<Texture> objTexture,float speed, float size) {
        this.x = rand.nextFloat() * 1080;
        this.y = 750;
        this.proportion = rand.nextFloat() * 0.5f + 0.5f;
        this.img = objTexture.get(rand.nextInt(objTexture.size()));
        this.speed = proportion * speed;
        this.size = proportion * size;
    }
}

public class SpaceObjects {
    int count;
    Main game;
    String name;

    ArrayList<Texture> objTexture = new ArrayList<>();
    ArrayList<SpaceObject> objectList = new ArrayList<>();

    float speed, extraSpeed, speedRate;
    float size;
    private Random rand = new Random();

    SpaceObjects(Main game, String name,float speed, float size) {
        this.game = game;
        this.name = name;
        this.size= size;
        this.speed= speed;
        selectObject(name);
    }

    public void selectObject(String name){
        if (name.equals("planet")) {
            this.count = 4;
            objTexture.add(new Texture("image/object/Planet_01.png"));
            objTexture.add(new Texture("image/object/Planet_02.png"));
            objTexture.add(new Texture("image/object/Planet_03.png"));
            objTexture.add(new Texture("image/object/Planet_04.png"));
        }else if(name.equals("dust")){
            objTexture.add(new Texture("image/object/dust_2.png"));
        }else if(name.equals("star")){
            objTexture.add(new Texture("image/object/star1.png"));
        }
    }

    public void renderObject() {
        for (SpaceObject obj : objectList) {
            game.batch.draw(obj.img, obj.x, obj.y,obj.size,obj.size);
        }
    }

    public void controlObjectRender(float delta) {
        if (rand.nextInt(1000) < 1) {
            objectList.add(new SpaceObject(objTexture,this.speed,this.size));
        }
        for (int i = 0; i < objectList.size(); i++) {
            SpaceObject obj = objectList.get(i);
            obj.y -= obj.speed * delta*game.speedMultiplayer;
            if (obj.y < 0) {
                objectList.remove(i);
                i--;
            }
        }
    }
}
