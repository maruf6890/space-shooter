package com.maruf.sust;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    //account
    Account playerAccount= new Account();
    public Shop shop= new Shop(playerAccount);



    @Override
    public void create() {
        batch = new SpriteBatch();  // ✅ Initialize batch
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
