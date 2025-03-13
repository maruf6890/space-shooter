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

public class TitleScreen implements Screen {
    Main game;
    private Texture bg,loadingplane;
    float planeX, planeY;
    ImageButton playBtn;
    private Stage stage;
    double loading;
    boolean isLoading;
    private BitmapFont loadingText;



    // Constructor
    public TitleScreen(Main game) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        bg = new Texture("image/title.png");
        loadingplane= new Texture("image/ship/LoadingShip.png");
        // Create the button with the texture
        playBtn = new ImageButton(new TextureRegionDrawable(new Texture("image/gui/Main_Menu/Start_BTN.png")));
        planeX=0;
        planeY=0;
        loading = 0;
        isLoading = false;

        // Set the button scale
        float scale = 0.5f;
        playBtn.setScale(scale);
        float width = playBtn.getWidth() * scale;
        float height = playBtn.getHeight() * scale;
        playBtn.setSize(width, height);

        loadingText = new BitmapFont();

        // Set the button's position
        playBtn.setPosition(640 - playBtn.getWidth() / 2 + 10, 80);  // Center it on the screen

        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!isLoading) {
                    isLoading = true;
                    loading = 0;
                }
                System.out.println("clicked");
            }
        });

        // Add button to the stage
        stage.addActor(playBtn);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (isLoading) {
            loading += 10 * delta;
            planeX+=2.2;

            if (loading >= 100) {
                isLoading = false;
                loading = 100;
                game.setScreen(new MenuScreen(game));
            }
        }

        game.batch.begin();
        // Draw the background
        game.batch.draw(bg, 0, 0, 1280, 720);


        game.batch.draw(loadingplane,planeX,planeY);

        // Draw loading text with the percentage
        loadingText.draw(game.batch, "Loading: " + (int) loading + "%", 600, 60); // Centered text

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
