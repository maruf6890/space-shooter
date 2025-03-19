package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maruf.sust.Main;
import com.maruf.sust.entities.SpaceShip;
import com.maruf.sust.utils.Account;

public class GameScreen implements Screen {
    private Main game;
    Background bg1;
    SpaceObjects planets,stars;
    SpaceObjects dusts;

    //player
    SpaceShip ship;
    Texture test;


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


    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ship.controlShip(delta);
        bg1.controlBg(delta);
        planets.controlObjectRender(delta);
        dusts.controlObjectRender(delta);
        stars.controlObjectRender(delta);


        game.batch.begin();
        bg1.renderBg();
        planets.renderObject();
        dusts.renderObject();
        stars.renderObject();
        ship.renderShip();
        game.batch.end();
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

    }
}
