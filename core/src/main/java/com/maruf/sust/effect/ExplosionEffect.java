package com.maruf.sust.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ExplosionEffect {
    private static final int FRAME_COUNT = 24;
    private static final float FRAME_DURATION = 1f / 24f; // 24 fps
    private static Animation<TextureRegion> explosionAnimation;

    private float elapsedTime = 0f;
    private Vector2 position;
    private boolean finished = false;

    public ExplosionEffect(float x, float y) {
        this.position = new Vector2(x, y);

        if (explosionAnimation == null) {
            loadAnimation();
        }
    }

    private void loadAnimation() {
        TextureRegion[] frames = new TextureRegion[FRAME_COUNT];

        for (int i = 0; i < FRAME_COUNT; i++) {
            String fileName = String.format("image/effect/exp/%04d.png", i + 1);
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            frames[i] = new TextureRegion(texture);
        }

        explosionAnimation = new Animation<>(FRAME_DURATION, frames);
        explosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public void update(float delta) {
        elapsedTime += delta;
        if (explosionAnimation.isAnimationFinished(elapsedTime)) {
            finished = true;
        }
    }

    public void render(Batch batch) {
        if (!finished) {
            TextureRegion currentFrame = explosionAnimation.getKeyFrame(elapsedTime);
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
