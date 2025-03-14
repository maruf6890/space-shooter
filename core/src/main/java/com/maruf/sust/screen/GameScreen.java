package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maruf.sust.Main;
import com.maruf.sust.utils.Account;

public class GameScreen implements Screen {
    private Main game;
    Background bg1;
    SpaceObjects planets,stars;
    SpaceObjects dusts;
    private Texture player;   // Player spaceship

    private Vector2 playerPosition;  // Player position
    private float playerSpeed = 300; // Speed of movement

    //Account
    Account playerAccount;
    public GameScreen(Main game,Account ac) {
        playerAccount= ac;
        this.game = game;
        bg1= new Background(game,"image/bg/space-2.png",100,0.2f);
        planets= new SpaceObjects(game,"planet",50,50);
        dusts= new SpaceObjects(game,"dust",30,200);
        stars =new SpaceObjects(game,"star",80,30);

        player = new Texture("image/ship/ship5.png");    // Load player texture

        // Initialize player position (center-bottom of screen)
        playerPosition = new Vector2(640 - player.getWidth() / 2, 50);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        handleInput(delta);
        bg1.controlBg(delta);
        planets.controlObjectRender(delta);
        dusts.controlObjectRender(delta);
        stars.controlObjectRender(delta);


        game.batch.begin();
        bg1.renderBg();
        planets.renderObject();
        dusts.renderObject();
        stars.renderObject();
        game.batch.draw(player, playerPosition.x, playerPosition.y,100,100); // Draw player
        game.batch.end();
    }

    private void handleInput(float delta) {
        // Move left
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
            playerPosition.x -= playerSpeed * delta;
        }
        // Move right
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {
            playerPosition.x += playerSpeed * delta;
        }
        // Move up
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
            playerPosition.y += playerSpeed * delta;
        }
        // Move down
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
            playerPosition.y -= playerSpeed * delta;
        }

        // Keep player within screen bounds
        playerPosition.x = Math.max(0, Math.min(1280 - 100, playerPosition.x));
        playerPosition.y = Math.max(0, Math.min(720 -100, playerPosition.y));
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

        player.dispose();
    }
}
