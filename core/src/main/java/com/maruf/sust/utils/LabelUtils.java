package com.maruf.sust.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class LabelUtils implements Disposable {

    private BitmapFont font;

    public LabelUtils() {

        font = new BitmapFont();
        font.getData().setScale(2f);
    }


    public Label createLabel(String text, float x, float y, Color color) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = color != null ? color : Color.WHITE; // Default to white

        Label label = new Label(text, labelStyle);
        label.setPosition(x, y);

        return label;
    }


    public  Image createImage(Texture texture, float x, float y,float h,float w) {
        Image image = new Image(texture);
        image.setPosition(x, y);
        image.setSize(w,h);
        return image;
    }


    public  Label createPopupWithEffect(final Label label, float duration, float slideDistance) {
        label.addAction(Actions.sequence(
            Actions.moveBy(0, slideDistance, duration),
            Actions.fadeOut(duration)
        ));
        return label;
    }
    //public image button

    //

    @Override
    public void dispose() {
        font.dispose();
    }
}
