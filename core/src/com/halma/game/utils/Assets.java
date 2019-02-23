package com.halma.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture boardSpace;
    public static Texture boardPiece_Red;

    public static void init() {
        boardSpace = new Texture(Gdx.files.internal("BoardSpace.png"));
        boardPiece_Red = new Texture(Gdx.files.internal("BoardPiece_Red.png"));
    }

    public static void dispose() {
        boardSpace.dispose();
        boardPiece_Red.dispose();
    }

}
