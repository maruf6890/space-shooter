package com.maruf.sust.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;

class LineTrail {
    public float x, y, speed, height, width;
    Random rand = new Random();
    Color randColor;

    public LineTrail() {
        this.x = MathUtils.random(0, 1280);
        this.y = 800;
        this.speed = MathUtils.random(20,50);
        this.height = MathUtils.random(50, 100);
        this.width = 1;
        randColor= getRandomColor();
    }

    public Color getRandomColor() {
        int colorIndex = rand.nextInt(6);
        switch (colorIndex) {
            case 0:
                return Color.WHITE;
            case 1:
                return new Color(0.2f, 0.2f, 0.6f, 0.5f); // Blue
            case 2:
                return Color.WHITE; // White
            case 3:
                return Color.WHITE;
            case 4:
                return new Color(0.0f, 1.0f, 1.0f, 0.6f);
            case 5:
                return new Color(0.8f, 0.1f, 0.1f, 0.6f);
            default:
                return Color.WHITE;
        }
    }


    public void renderLineTrail(ShapeRenderer sp) {
        sp.setColor(randColor);
        sp.rect(this.x, this.y, this.width, this.height);
    }
}

public class LineTrails {
    ArrayList<LineTrail> lines = new ArrayList<>();
    ShapeRenderer shapeRenderer;

    public LineTrails() {
        shapeRenderer = new ShapeRenderer();
        for (int i = 0; i < 10; i++) {
            createLine();
        }
    }

    public void createLine() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                lines.add(new LineTrail());
            }
        }, 2, 0.5f);
    }
    public void updateAndRender() {

        for (int i = 0; i < lines.size(); i++) {
            LineTrail line = lines.get(i);
            line.y -= line.speed;
            if (line.y + line.height < 0) {
                lines.remove(i);
                i--;
            }
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (LineTrail line : lines) {
            line.renderLineTrail(shapeRenderer);
        }
        shapeRenderer.end();
    }
}
