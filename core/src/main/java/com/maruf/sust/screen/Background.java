package com.maruf.sust.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maruf.sust.Main;

public class Background {
    private Texture img,img2;
    private float baseSpeed;
    private float extraSpeed;
    private float speedPercent;
    private float y1, y2;
    private Main game;

    // Constructor
    public Background(Main game, String location, float speed, float speedPercent) {
        this.game = game;
        this.img = new Texture(location);
        this.img2 = new Texture("image/bg/space-1.png");
        this.baseSpeed = speed;
        this.speedPercent = speedPercent;

        this.y1 = 0;
        this.y2 = 720;
    }

    // Render both images for smooth scrolling
    public void renderBg() {
        game.batch.draw(img, 0, y1, 1280, 720);
        game.batch.draw(img2, 0, y2, 1280, 720);
    }

    // Move backgrounds down and reset when out of bounds
    public void controlBg(float delta) {
        float finalSpeed = baseSpeed * speedPercent*game.speedMultiplayer;

        y1 -= finalSpeed * delta;
        y2 -= finalSpeed * delta;


        if (y1 <=-720) {
            y1 = y2 +720;
        }
        if (y2 <-720) {
            y2 = y1 + 720;
        }
    }

    // Dispose texture when no longer needed
    public void dispose() {
        img.dispose();
    }
}
