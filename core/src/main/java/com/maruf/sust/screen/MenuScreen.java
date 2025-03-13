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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.maruf.sust.Main;

public class MenuScreen implements Screen {

    Main game;
    private Texture bg;
    private Stage stage;

    // Buttons
    ImageButton playButton;
    ImageButton optionsButton;
    ImageButton exitButton;

    // Constructor
    public MenuScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        bg = new Texture("image/bg/menu.png");

        // Play Button
        playButton = createButton("image/gui/Main_Menu/Start_BTN.png", 640, 400);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play button clicked");
                // Transition to Game Screen
               // game.setScreen(new GameScreen(game));  // Assuming you have GameScreen
            }
        });

        // Options Button
        optionsButton = createButton("image/gui/Main_Menu/Start_BTN.png", 640, 300);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Options button clicked");
                // Transition to Options Screen
                //game.setScreen(new OptionsScreen(game));  // Assuming you have OptionsScreen
            }
        });

        // Exit Button
        exitButton = createButton("image/gui/Main_Menu/Start_BTN.png", 640, 200);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit button clicked");
                Gdx.app.exit();  // Exit the game
            }
        });

        // Add buttons to the stage
        stage.addActor(playButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);
    }

    // Helper method to create a button
    private ImageButton createButton(String texturePath, float x, float y) {
        ImageButton button = new ImageButton(new TextureRegionDrawable(new Texture(texturePath)));
        button.setPosition(x - button.getWidth() / 2, y - button.getHeight() / 2);
        return button;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(bg, 0, 0, 1280, 720);  // Draw background image
        game.batch.end();

        // Draw the stage (buttons)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));  // Ensure updates don't run faster than 30 FPS
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
    }
}
