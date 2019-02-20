package com.halma.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.gameobjects.Board;
import com.halma.game.gameobjects.Piece;

public class GameState extends State {

    private Texture background;
    private Texture redPieceUpImg;
    private Texture redPieceDownImg;
    private Texture bluePieceImg;
    private Texture greenPieceImg;
    private Texture yellowPieceImg;

    private Board board;
    private Piece redPiece;

    //Constructor
    public GameState(Handler handler) {
        super(handler);
    }

    public void init() {
        background = new Texture("tri_board.png");
        redPieceUpImg = new Texture("RedPieceUp.png");
        redPieceDownImg = new Texture("RedPieceDown.png");
        bluePieceImg = new Texture("BluePiece.png");
        greenPieceImg = new Texture("GreenPiece.png");
        yellowPieceImg = new Texture("YellowPiece.png");

        board = new Board(handler, 0 ,0);

        //redPiece = new Piece(handler, 11, 15, "Red", redPieceUpImg);
        //board.setBoard(redPiece.getX(), redPiece.getY(),redPiece);
    }

    // Main methods
    @Override
    public void update(float dt) {
        //redPiece.update(dt);
        board.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(background, 0 ,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //redPiece.render(batch);
        board.render(batch);
    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();
    }

    // Other methods


    // Getters and Setters
    public Board getBoard() {return board;}
    public Texture getRedPieceUpImg() {return redPieceUpImg;}
    public Texture getRedPieceDownImg() {return redPieceDownImg;}
}
