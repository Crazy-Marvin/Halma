package com.halma.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.gameobjects.Board;
import com.halma.game.gameobjects.BoardPiece;
import com.halma.game.gameobjects.Piece;

public class GameState extends State {

    private Texture background;
    private Board board;
    private BoardPiece redPiece;

    //Constructor
    public GameState(Handler handler) {
        super(handler);
    }

    public void init() {
        background = new Texture("tri_board.png");

        board = new Board(handler, 0 ,0);
        redPiece = new BoardPiece(handler ,8 , 10, 2);
    }

    // Main methods
    @Override
    public void update(float dt) {
        board.update(dt);
        redPiece.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(background, 0 ,20, Gdx.graphics.getWidth(), 600);
        board.render(batch);
        redPiece.render(batch);
    }

    @Override
    public void dispose() {
        if (background != null) {
            background.dispose();
            redPiece.dispose();
        }
    }

    // Other methods


    // Getters and Setters
    public Board getBoard() {return board;}
}
