package com.halma.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture boardSpace;

    public static void init() {
        boardSpace = new Texture(Gdx.files.internal("BoardSpace.png"));
    }

    public static void dispose() {

    }

}
