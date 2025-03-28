package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Image hpBox;
    public  float Score;
    Random rand = new Random();
    //player
    SpaceShip ship;
    Texture test;


    ArrayList<EnemyShip> enemy = new ArrayList<>();

    //Account
    Account playerAccount;
    public GameScreen(Main game,Account ac) {
        playerAccount= ac;
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
        hpBox= labelUtils.createImage(new Texture("image/gui/Main_UI/Health_Bar_Table.png"),game.WIDTH/2-50,650,20,100);
        stage.addActor(hpBox);
        this.Score=0;



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
                iterator.remove(); // ✅ Safe removal
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


        game.batch.begin();
        bg1.renderBg();
        planets.renderObject();
        dusts.renderObject();
        stars.renderObject();
        ship.renderShip(delta);
        for(EnemyShip v: enemy){
            v.render(delta);
            ship.destroyEnemy(v);


        }

        game.batch.end();
        stage.act(delta);
        stage.draw();
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
    }
}
