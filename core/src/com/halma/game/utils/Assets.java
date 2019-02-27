package com.halma.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture boardSpace;
    public static Texture boardPiece_Red;
    public static Texture boardPiece_Green;
    public static Texture hintPiece;

    public static void init() {
        boardSpace = new Texture(Gdx.files.internal("BoardSpace.png"));
        boardPiece_Red = new Texture(Gdx.files.internal("BoardPiece_Red.png"));
        boardPiece_Green = new Texture((Gdx.files.internal("BoardPiece_Green.png")));
        hintPiece = new Texture(Gdx.files.internal("HintPiece.png"));
    }

    public static void dispose() {
        boardSpace.dispose();
        boardPiece_Red.dispose();
        boardPiece_Green.dispose();
        hintPiece.dispose();
    }

}
