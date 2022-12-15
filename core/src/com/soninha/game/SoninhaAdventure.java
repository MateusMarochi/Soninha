package com.soninha.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.soninha.game.cenas.Historia;
import com.soninha.game.cenas.Masmorra;
import com.soninha.game.cenas.Menu;

public class SoninhaAdventure extends Game {
    SpriteBatch batch;
    public Viewport screenPort;
    public MyAssetManager myAssetManager = new MyAssetManager();

    @Override
    public void create() {
        batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        screenPort = new ScreenViewport();

        this.setScreen(new Menu(this));
    }

    public void gotoMenu() {
        Menu menuCena = new Menu(this);
        setScreen(menuCena);
    }

    public void gotoMasmorra() {
        Masmorra masmorraCena = new Masmorra(this);
        setScreen(masmorraCena);
    }

    public void gotoHistoria() {
        Historia historiaCena = new Historia(this);
        setScreen(historiaCena);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
