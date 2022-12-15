package com.soninha.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.acanthite.gdx.graphics.g2d.FreeTypeSkinLoader;

public class MyAssetManager {
    public final AssetManager manager = new AssetManager();

    public void queueAddSkin(){
        SkinParameter parameter = new SkinParameter(GameConstants.skinAtlas);

//        manager.setLoader(Skin.class, new FreeTypeSkinLoader(manager.getFileHandleResolver()));;

        manager.load(GameConstants.skin,Skin.class,parameter);
        manager.finishLoading();
    }
}