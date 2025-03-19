package com.maruf.sust;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maruf.sust.entities.SpaceShip;
import com.maruf.sust.screen.GameScreen;
import com.maruf.sust.screen.MenuScreen;
import com.maruf.sust.screen.TitleScreen;
import com.maruf.sust.utils.Account;
import com.maruf.sust.utils.Shop;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;


   public Account playerAccount= new Account();
   public Shop shop= new Shop(this,playerAccount);
   //SpaceShip
   public  SpaceShip alphaShip;


    @Override
    public void create() {
        batch = new SpriteBatch();  // ✅ Initialize batch
        alphaShip= new SpaceShip(this,"Alpha Ship",300,0,120,0,0,new Texture("image/ship/ship5.png"),0,true);


        this.setScreen(new GameScreen(this,playerAccount));
    }

    @Override
    public void render() {
        super.render(); // ✅ Corrected infinite recursion issue
    }

    @Override
    public void dispose() {
        batch.dispose(); // ✅ Removed unused image.dispose()
    }
}
