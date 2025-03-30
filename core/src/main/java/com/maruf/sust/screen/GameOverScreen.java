package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maruf.sust.Main;

public class GameOverScreen implements Screen {
    Main game;
    private Texture bg,loadingplane;
    float planeX, planeY;
    ImageButton playBtn;
    private Stage stage;
    double loading;
    boolean isLoading;
    private BitmapFont loadingText;



    // Constructor
    public GameOverScreen(Main game) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        bg = new Texture("image/title.png");

        // Create the button with the texture







        // Add button to the stage

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {


        game.batch.begin();
        // Draw the background
        game.batch.draw(bg, 0, 0, 1280, 720);




        // Draw loading text with the percentage
        // Centered text

        game.batch.end();

        // Draw the stage (including the button)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Ensure that updates don't run faster than 30 FPS
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        bg.dispose();
        stage.dispose();
        loadingText.dispose();
    }
}
