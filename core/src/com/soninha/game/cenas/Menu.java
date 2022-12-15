package com.soninha.game.cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.soninha.game.GameConstants;
import com.soninha.game.SoninhaAdventure;

public class Menu implements Screen {
    final SoninhaAdventure game;
    private Skin mySkin;
    private Stage stage;

    public Menu(final SoninhaAdventure game) {
        this.game = game;
        game.myAssetManager.queueAddSkin();
        game.myAssetManager.manager.finishLoading();
        mySkin = game.myAssetManager.manager.get(GameConstants.skin);
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);


        Label titulo = new Label("Soninha Adventure", mySkin, "big-black");
        titulo.setSize(GameConstants.col_width * 3, GameConstants.row_height * 2);
        titulo.setPosition(GameConstants.centerX - titulo.getWidth() / 2, GameConstants.centerY + GameConstants.row_height);
        titulo.setAlignment(Align.center);

        Button historiaBtn = new TextButton("HISTORIA", mySkin, "small");
        historiaBtn.setSize(GameConstants.col_width * 3, GameConstants.row_height);
        historiaBtn.setPosition(GameConstants.centerX - historiaBtn.getWidth() / 2, GameConstants.centerY);
        historiaBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.gotoHistoria();
            }
        });

        Button masmorraBtn = new TextButton("MASMORRA", mySkin, "small");
        masmorraBtn.setSize(GameConstants.col_width * 3, GameConstants.row_height);
        masmorraBtn.setPosition(GameConstants.centerX - masmorraBtn.getWidth() / 2, historiaBtn.getY() - GameConstants.row_height - 15);
        masmorraBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.gotoMasmorra();
            }
        });


        stage.addActor(titulo);
        stage.addActor(historiaBtn);
        stage.addActor(masmorraBtn);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.screenPort.update(width, height);

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
        /*badlogic.dispose();*/
        /*game.batch.dispose();*/
        mySkin.dispose();
        stage.dispose();

    }
}
