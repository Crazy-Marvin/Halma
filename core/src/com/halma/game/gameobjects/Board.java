package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;

public class Board extends GameObject {

    private Piece[][] board;

    public Board(Handler handler, int x, int y) {
        super(handler, x, y);
        init();
    }

    public void init() {
        board = new Piece[16][23];
    }

    @Override
    public void update(float dt) {
        for (Piece[] b : board) {
            for (Piece p : b) {
                if (p != null) p.update(dt);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Piece[] b : board) {
            for (Piece p : b) {
                if (p != null) p.render(batch);
            }
        }
    }

    @Override
    public void dispose() {

    }

    // Getters and Setters
    public Piece[][] getBoard() {return board;}

    public void setBoard(int x, int y, Piece p) {board[y][x] = p;}

}
