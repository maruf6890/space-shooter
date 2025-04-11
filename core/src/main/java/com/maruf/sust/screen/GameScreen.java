package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maruf.sust.Main;
import com.maruf.sust.entities.*;
import com.maruf.sust.utils.Account;
import com.maruf.sust.utils.LabelUtils;
import com.maruf.sust.weapen.PlayerBullet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    private Main game;
    Background bg1;
    SpaceObjects planets,stars;
    SpaceObjects dusts;
    private Stage stage;
    private LabelUtils labelUtils;
    private Label scoreLabel;
    private Texture heartTexture;
    public  float Score;
    Random rand = new Random();

    boolean isPowerExhist=false;
    SpaceShip ship;
    Texture test;
    Music bgm;


    ArrayList<EnemyShip> enemy = new ArrayList<>();
    LineTrails lineTrails;
    //Account

    public GameScreen(Main game) {

        this.game = game;
        bg1= new Background(game,"image/bg/space-2.png",100,0.2f);
        planets= new SpaceObjects(game,"planet",50,50);
        dusts= new SpaceObjects(game,"dust",30,200);
        stars =new SpaceObjects(game,"star",80,30);
        ship= game.alphaShip;
        test = new Texture("image/ship/ship5.png");
        startEnemySpawner();
        stage= new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        labelUtils= new LabelUtils();
        scoreLabel= labelUtils.createLabel("Score"+ Float.toString(game.currentScore),50,650, Color.BROWN);
        stage.addActor(scoreLabel);
        heartTexture = new Texture("image/gui/Main_UI/heart.png");

        System.out.println(game.alphaShip.getMechaHealth());
        this.Score=0;
        createPower();
        lineTrails= new LineTrails();
        bgm= Gdx.audio.newMusic(Gdx.files.internal("Audio/game.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(1);
        bgm.play();



    }






    void startEnemySpawner() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spawnEnemies();
            }
        }, 0, 5); // Spawns enemies every second
    }

    void spawnEnemies() {
        int enemyOne = rand.nextInt(3);
        int enemyTwo = rand.nextInt(3);

        EnemyShip enemy1 = createEnemy(enemyOne);
        EnemyShip enemy2 = createEnemy(enemyTwo);


            enemy.add(enemy1);
            enemy.add(enemy2);

    }

    EnemyShip createEnemy(int type) {
        switch (type) {
            case 0: return new RandomShip(game);
            case 1: return new SineShip(game);
            default: return new ZigZagShip(game);
        }
    }

    void updateEnemies(float delta) {
        scoreLabel.setText("Score "+Float.toString(game.currentScore));
        Iterator<EnemyShip> iterator = enemy.iterator();
        while (iterator.hasNext()) {
            EnemyShip v = iterator.next();
            if (v.isAlive()) {
                v.update(delta);

            } else {
                iterator.remove();
            }
        }
    }



    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ship.controlShip(delta);
        bg1.controlBg(delta);
        updateEnemies(delta);
        planets.controlObjectRender(delta);
        dusts.controlObjectRender(delta);
        stars.controlObjectRender(delta);
        if(game.currentPowerUps!= null){
            game.currentPowerUps.controlPowerUps(delta);
        }



        game.batch.begin();
        bg1.renderBg();
        planets.renderObject();
        dusts.renderObject();
        stars.renderObject();
        ship.renderShip(delta);
        if(ship.isHasShield()){
            game.batch.draw(new Texture("image/ship/shield.png"),ship.x,ship.y,ship.size,ship.size);
        }
       for(EnemyShip v: enemy){
            v.render(delta);
            ship.destroyEnemy(v);


        }

       if(game.currentPowerUps!= null){
           game.currentPowerUps.renderPowerUps();
       }


        //game heart render
        if(game.alphaShip.getMechaHealth()>=0) game.batch.draw(heartTexture,game.WIDTH/2-80,660,24,24);
        if(game.alphaShip.getMechaHealth()>=20) game.batch.draw(heartTexture,game.WIDTH/2-46,660,24,24);
        if(game.alphaShip.getMechaHealth()>=40) game.batch.draw(heartTexture,game.WIDTH/2-12,660,24,24);
        if(game.alphaShip.getMechaHealth()>=60) game.batch.draw(heartTexture,game.WIDTH/2+22,660,24,24);
        if(game.alphaShip.getMechaHealth()>=80) game.batch.draw(heartTexture,game.WIDTH/2+56,660,24,24);


        game.batch.end();
        if(ship.isHasAcceleration())
        {
            lineTrails.updateAndRender();
        }
        stage.act(delta);
        stage.draw();

    }

    public void createPower(){
       Timer.schedule(new Timer.Task() {
           @Override
           public void run() {

               switch (rand.nextInt(4)){
                   case 0:
                       game.currentPowerUps= new TitanShield(game,new Texture("image/gui/Hangar/Armor_Icon.png")) ;
                       break;

                   case 1:
                       game.currentPowerUps= new Repair(game,new Texture("image/gui/Hangar/HP_Icon.png")) ;

                       break;
                   case 2:
                       game.currentPowerUps= new BeastMode(game,new Texture("image/gui/Hangar/Damage_Icon.png")) ;
                       break;
                   case 3:
                       game.currentPowerUps= new SonicSpeed(game,new Texture("image/gui/Hangar/Speed_Icon.png")) ;
                       lineTrails.createLine();
                       break;
               }


           }
       },5,30);


    }



    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
    test.dispose();
    stage.dispose();
    bgm.pause();
    bgm.dispose();
    heartTexture.dispose();
    }
}
