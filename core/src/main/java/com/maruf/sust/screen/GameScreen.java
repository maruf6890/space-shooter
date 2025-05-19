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
import com.maruf.sust.effect.ExplosionEffect;
import com.maruf.sust.entities.*;
import com.maruf.sust.utils.LabelUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {

    private final Main game;
    Random rand = new Random();

    //background element
    Background background;
    SpaceObjects planets,stars;
    SpaceObjects dusts;
    LineTrails lineTrails;

    //ui
    private final Stage stage;
    private  LabelUtils labelUtils;
    private final Label scoreLabel;
    private final Texture heartTexture;
    public  float Score;

    //bgm and sound fx
    Music bgm;

    //entity
    ArrayList<EnemyShip> enemy = new ArrayList<>();
    ArrayList<ExplosionEffect> explotions= new ArrayList<>();


    public GameScreen(Main game) {
        this.game = game;
        //background elements
        background= new Background(game,"image/bg/space-2.png",100);
        planets= new SpaceObjects(game,"planet",50,50);
        dusts= new SpaceObjects(game,"dust",30,200);
        stars =new SpaceObjects(game,"star",80,30);
        lineTrails= new LineTrails();

        //ui
        stage= new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        labelUtils= new LabelUtils();
        scoreLabel= labelUtils.createLabel("Score"+ Float.toString(game.currentScore),50,650, Color.BROWN);
        stage.addActor(scoreLabel);
        heartTexture = new Texture("image/gui/Main_UI/heart.png");

        this.Score=0;

        //generate
        startEnemySpawner();
        createPower();

        //bgm
        bgm= Gdx.audio.newMusic(Gdx.files.internal("Audio/game.mp3"));
        bgm.setLooping(true);
        bgm.setVolume(1);
        bgm.play();
    }


    EnemyShip createEnemy(int type) {
        switch (type) {
            case 0: return new RandomShip(game);
            case 1: return new SineShip(game);
            default: return new ZigZagShip(game);
        }
    }
    void spawnEnemies() {
        int enemyOne = rand.nextInt(2);
        int enemyTwo = rand.nextInt(2);
        EnemyShip enemy1 = createEnemy(enemyOne);
        EnemyShip enemy2 = createEnemy(enemyTwo);
        enemy.add(enemy1);
        enemy.add(enemy2);
    }
    void startEnemySpawner() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spawnEnemies();
            }
        }, 3, 10);
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
        //control
        game.alphaShip.controlShip(delta);
        background.controlBg(delta);
        updateEnemies(delta);
        planets.controlObjectRender(delta);
        dusts.controlObjectRender(delta);
        stars.controlObjectRender(delta);
        if(game.currentPowerUps!= null){
            game.currentPowerUps.controlPowerUps(delta);
        }
        //render
        game.batch.begin();
        background.renderBg();
        planets.renderObject();
        dusts.renderObject();
        stars.renderObject();
        game.alphaShip.renderShip(delta);
        if(game.alphaShip.isHasShield()){
            game.batch.draw(new Texture("image/ship/shield.png"),game.alphaShip.x,game.alphaShip.y,game.alphaShip.size,game.alphaShip.size);
        }
       for(EnemyShip v: enemy){
            v.render(delta);
            game.alphaShip.destroyEnemy(v);
            if(!v.isAlive()){
               explotions.add(new ExplosionEffect(v.x,v.y)) ;
            }
        }
        for(ExplosionEffect fx: explotions){
            if(!fx.isFinished()){
                fx.render(game.batch);
            }
        }
        for (Iterator<ExplosionEffect> iterator = explotions.iterator(); iterator.hasNext(); ) {
            ExplosionEffect explosion = iterator.next();

            explosion.update(delta);
            if (explosion.isFinished()) {
                iterator.remove();
            }
        }


       if(game.currentPowerUps!= null){
           game.currentPowerUps.renderPowerUps();
       }
        //game heart render
        if(game.alphaShip.getMechaHealth()>=0) game.batch.draw(heartTexture,game.WIDTH/2f-80,660,24,24);
        if(game.alphaShip.getMechaHealth()>=20) game.batch.draw(heartTexture,game.WIDTH/2f-46,660,24,24);
        if(game.alphaShip.getMechaHealth()>=40) game.batch.draw(heartTexture,game.WIDTH/2f-12,660,24,24);
        if(game.alphaShip.getMechaHealth()>=60) game.batch.draw(heartTexture,game.WIDTH/2f+22,660,24,24);
        if(game.alphaShip.getMechaHealth()>=80) game.batch.draw(heartTexture,game.WIDTH/2f+56,660,24,24);
        game.batch.end();
        if(game.alphaShip.isHasAcceleration())
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
    stage.dispose();
    background.dispose();
    bgm.pause();
    bgm.dispose();
    heartTexture.dispose();
    }
}
