package com.soninha.game.cenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.soninha.game.Entidade;
import com.soninha.game.Magia;
import com.soninha.game.GameConstants;
import com.soninha.game.SoninhaAdventure;

import java.util.Random;

public class Masmorra implements Screen {
    final SoninhaAdventure game;
    private Texture minionTexture;
    private Skin mySkin;
    private Stage stage;
    private Image[][] barrasVida;
    private Image[][] barrasMana;
    private Button[] magias;
    private Entidade m1, m2, sona;
    private Image minion1, minion2, sonaImg;

    private Magia magiaSelecionada;

    Image image1;

    public Masmorra(final SoninhaAdventure game) {
        this.game = game;
        game.myAssetManager.queueAddSkin();
        game.myAssetManager.manager.finishLoading();

        mySkin = game.myAssetManager.manager.get(GameConstants.skin);
        minionTexture = new Texture(Gdx.files.internal("minions.png"));

        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);

        Image fundo = new Image(new Texture(Gdx.files.internal("backgroud.png")));
        fundo.setPosition(0, 0);
        stage.addActor(fundo);

        sona = new Entidade("Sona", 18);
        TextureRegion sona_tx = new TextureRegion(minionTexture);
        sona_tx.setRegion(0, 387, 460, 280);
        sonaImg = new Image(sona_tx);
        sonaImg.setPosition(10, 193);
        sonaImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (magiaSelecionada != null) {
                    if (sona.vida > 0)
                        magiaSelecionada.conjurar(sona, sona);
                    if (m1.getVida() > 0)
                        m1.magias[0].conjurar(m1, sona);
                    if (m2.getVida() > 0)
                        m2.magias[0].conjurar(m2, sona);
                }
            }
        });
        stage.addActor(sonaImg);

        inicializarBarras(2);


        m1 = getMinion();
        minion1 = getMinionImage(m1);
        minion1.setPosition(710, 300);

        m2 = getMinion();
        minion2 = getMinionImage(m2);
        minion2.setPosition(460, 300);


        minion1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (magiaSelecionada != null) {
                    if (sona.vida > 0)
                        magiaSelecionada.conjurar(sona, m1);
                    if (m1.getVida() > 0)
                        m1.magias[0].conjurar(m1, sona);
                    if (m2.getVida() > 0)
                        m2.magias[0].conjurar(m2, sona);
                }
            }
        });

        minion2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (magiaSelecionada != null) {
                    if (sona.vida > 0)
                        magiaSelecionada.conjurar(sona, m2);
                    if (m1.getVida() > 0)
                        m1.magias[0].conjurar(m1, sona);
                    if (m2.getVida() > 0)
                        m2.magias[0].conjurar(m2, sona);
                }
            }
        });

        stage.addActor(minion1);
        stage.addActor(minion2);

        final Label custoMana = new Label("", mySkin, "black");
        custoMana.setPosition(670, 110);
        stage.addActor(custoMana);

        int x_pos[] = {20, 321, 20, 321};
        int y_pos[] = {100, 100, 20, 20};
        magias = new Button[4];

        for (int i = 0; i < 4; i++) {
            magias[i] = new TextButton(sona.magias[i].nome, mySkin, "small");
            magias[i].setSize(296, 74);
            magias[i].setPosition(x_pos[i], y_pos[i]);
            final int finalI = i;
            magias[i].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    magiaSelecionada = sona.magias[finalI];
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    custoMana.setText("Mana: " + sona.magias[0].custoMana +
                            "\nMagico: " + (int) (sona.magias[finalI].danoMagico + sona.getDanoMagico() * sona.magias[finalI].bonusMagico) +
                            "\nFisico: " + (int) (sona.magias[finalI].danoFisico + sona.getDanoFisico() * sona.magias[finalI].bonusFisico));
                }
            });
            stage.addActor(magias[i]);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        barrasVida[0][0].setSize((sona.vida / sona.vidaMax) * 250, 10);
        barrasMana[0][0].setSize((sona.mana / sona.manaMax) * 250, 10);
        barrasVida[1][0].setSize((m1.vida / m1.vidaMax) * 200, 10);
        barrasVida[2][0].setSize((m2.vida / m2.vidaMax) * 200, 10);

        if (m1.vida < 0) {
            barrasVida[1][0].remove();
            barrasVida[1][1].remove();
            minion1.remove();
        }

        if (m2.vida < 0) {
            barrasVida[2][0].remove();
            barrasVida[2][1].remove();
            minion2.remove();
        }

        if (sona.vida < 0) {
            barrasVida[0][0].remove();
            barrasVida[0][1].remove();
            barrasMana[0][0].remove();
            barrasMana[0][1].remove();
            sonaImg.remove();
        }

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
        mySkin.dispose();
        stage.dispose();
    }

    private Entidade getMinion() {
        Random rand = new Random();
        String tipos[] = {"Mago", "Guerreiro"};

        Entidade minion = new Entidade(tipos[rand.nextInt(tipos.length)], rand.nextInt(6));
        return minion;
    }

    private Image getMinionImage(Entidade minion) {
        TextureRegion textureRegion = new TextureRegion(minionTexture);
        if (minion.nome == "Mago")
            textureRegion.setRegion(0 + 140 * (int) (minion.level / 2), 0, 140, 163);
        if (minion.nome == "Guerreiro")
            textureRegion.setRegion(0 + 253 * (int) (minion.level / 2), 166, 253, 218);
        return new Image(textureRegion);
    }

    private void inicializarBarras(int numMinions) {
        barrasVida = new Image[1 + numMinions][2];
        barrasMana = new Image[1][2];

        int x_pos[] = {100, 710, 460};
        int y_pos[] = {510, 530, 530};
        int sizes[] = {250, 200, 200};
        Random rand = new Random();

        Pixmap pixmap_green = createProceduralPixmap(0, 1, 0);
//        Pixmap pixmap_red = createProceduralPixmap(1, 0, 0);
        Pixmap pixmap_blue = createProceduralPixmap(0, 0, 1);
        Pixmap pixmap_gray = createProceduralPixmap(0.5f, 0.5f, 0.5f);

        int i = 0;
        for (Image[] img : barrasVida) {

            img[0] = new Image(new Texture(pixmap_green));
            img[0].setPosition(x_pos[i], y_pos[i]);
            img[0].setSize(rand.nextInt(sizes[i]), 10);

            img[1] = new Image(new Texture(pixmap_gray));
            img[1].setPosition(x_pos[i], y_pos[i]);
            img[1].setSize(sizes[i], 10);

            stage.addActor(img[1]);
            stage.addActor(img[0]);
            i++;
        }

        i = 0;
        for (Image[] img : barrasMana) {

            img[0] = new Image(new Texture(pixmap_blue));
            img[0].setPosition(x_pos[i], y_pos[i] - 10);
            img[0].setSize(rand.nextInt(sizes[i]), 10);

            img[1] = new Image(new Texture(pixmap_gray));
            img[1].setPosition(x_pos[i], y_pos[i] - 10);
            img[1].setSize(sizes[i], 10);

            stage.addActor(img[1]);
            stage.addActor(img[0]);
            i++;
        }
    }

    private Pixmap createProceduralPixmap(float r, float g, float b) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

        pixmap.setColor(r, g, b, 1);
        pixmap.fill();

        return pixmap;
    }
}
