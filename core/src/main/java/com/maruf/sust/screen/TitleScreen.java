package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maruf.sust.Main;

public class TitleScreen implements Screen {
    Main game;
    private Texture bg;
    ImageButton playBtn;
    private Stage stage;
    double loading;
    boolean isLoading;
    private BitmapFont loadingText;
    private Music music; // Declare a Music object

    // Constructor
    public TitleScreen(Main game) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        bg = new Texture("image/title.png");

        // Load background music
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/intro.mp3")); // Set your music path here
        music.setLooping(true); // Set the music to loop
        music.play(); // Start playing the music

        // Create the button with the texture
        playBtn = new ImageButton(new TextureRegionDrawable(new Texture("image/gui/Main_Menu/Start_BTN.png")));
        playBtn.setHeight(playBtn.getHeight()*0.5f);
        playBtn.setWidth(playBtn.getWidth()*0.5f);
        loading = 0;
        isLoading = false;

        loadingText = new BitmapFont();

        // Set the button's position
        playBtn.setPosition(640 - playBtn.getWidth() / 2 + 10, 80);  // Center it on the screen

        // Add button to the stage
        stage.addActor(playBtn);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (isLoading) {
            loading += 10 * delta;

            if (loading >= 100) {
                isLoading = false;
                loading = 100;
                this.music.pause();
                game.setScreen(new GameScreen(game));
            }
        }

        game.batch.begin();
        // Draw the background
        game.batch.draw(bg, 0, 0, 1280, 720);

        // Draw loading text with the percentage
        loadingText.draw(game.batch, "Loading: " + (int) loading + "%", 600, 60); // Centered text

        game.batch.end();

        // Check if Enter key is pressed for starting the game
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ENTER)) {
            if (!isLoading) {
                isLoading = true;
                loading = 0;
            }
        }

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
        music.dispose();
    }
}
