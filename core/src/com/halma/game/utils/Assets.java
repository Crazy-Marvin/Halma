package com.halma.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture playButton;
    public static Texture exitButton;

    public static Texture boardSpace;
    public static Texture boardPiece_Red;
    public static Texture boardPiece_Green;
    public static Texture boardPiece_Blue;
    public static Texture boardPiece_Purple;
    public static Texture boardPiece_SkyBlue;
    public static Texture boardPiece_Yellow;
    public static Texture hintPiece;

    public static void init() {
        playButton = new Texture(Gdx.files.internal("PlayButton.png"));
        exitButton = new Texture(Gdx.files.internal("ExitButton.png"));

        boardSpace = new Texture(Gdx.files.internal("BoardSpace.png"));
        boardPiece_Red = new Texture(Gdx.files.internal("BoardPiece_Red.png"));
        boardPiece_Green = new Texture((Gdx.files.internal("BoardPiece_Green.png")));
        boardPiece_Blue = new Texture((Gdx.files.internal("BoardPiece_Blue.png")));
        boardPiece_Purple = new Texture((Gdx.files.internal("BoardPiece_Purple.png")));
        boardPiece_SkyBlue = new Texture((Gdx.files.internal("BoardPiece_SkyBlue.png")));
        boardPiece_Yellow = new Texture((Gdx.files.internal("BoardPiece_Yellow.png")));
        hintPiece = new Texture(Gdx.files.internal("HintPiece.png"));
    }

    public static void dispose() {
        playButton.dispose();
        exitButton.dispose();

        boardSpace.dispose();
        boardPiece_Red.dispose();
        boardPiece_Green.dispose();
        boardPiece_Blue.dispose();
        boardPiece_Purple.dispose();
        boardPiece_SkyBlue.dispose();
        boardPiece_Yellow.dispose();
        hintPiece.dispose();
    }

}
