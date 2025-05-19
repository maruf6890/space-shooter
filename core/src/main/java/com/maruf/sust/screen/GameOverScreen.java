package com.maruf.sust.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maruf.sust.Main;
import com.maruf.sust.entities.SpaceShip;

public class GameOverScreen implements Screen {
    Main game;
    private Texture bg;
    private Stage stage;
    private Music music;
    private BitmapFont gameOverText;

    public GameOverScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        bg = new Texture("image/bg/gameover.jpg");

        // Load music
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/intro.mp3"));
        music.setLooping(true);
        music.play();

        // Load font for Game Over text
        gameOverText = new BitmapFont();

        // Create Restart Button
        ImageButton restartButton = new ImageButton(new TextureRegionDrawable(new Texture("image/gui/Replay_BTN.png")));
        restartButton.setSize(restartButton.getWidth() * 0.5f, restartButton.getHeight() * 0.5f);

        restartButton.setHeight(restartButton.getHeight()*0.5f);
        restartButton.setWidth(restartButton.getWidth()*0.5f);
        restartButton.setPosition(game.WIDTH/2-10, 250);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.alphaShip= new SpaceShip(game,"Alpha Ship",300,0,120,0,0,new Texture("image/ship/ship5.png"));

                game.setScreen(new GameScreen(game)); // Switch to new game screen
                dispose();
            }
        });

        stage.addActor(restartButton);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            Gdx.app.exit(); // Exit game if ESC is pressed
        }

        game.batch.begin();
        game.batch.draw(bg, 0, 0, 1280, 720);
        gameOverText.draw(game.batch, "Press Esc To exhist", 560, 220);
        gameOverText.getData().setScale(1.5f);
        game.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        music.stop();
        music.dispose();
        bg.dispose();
        stage.dispose();
        gameOverText.dispose();
    }
}
