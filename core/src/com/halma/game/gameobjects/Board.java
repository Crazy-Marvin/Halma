package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;

public class Board extends GameObject {

    private BoardSpace[][] board;

    public Board(Handler handler, int x, int y) {
        super(handler, x, y);
        init();
    }

    public void init() {
        board = new BoardSpace[17][13];
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[j].length; i++) {
                board[j][i] = new BoardSpace(handler, i, j);
            }
        }
    }

    @Override
    public void update(float dt) {
        for (BoardSpace[] b : board) {
            for (BoardSpace p : b) {
                if (p != null) p.update(dt);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (BoardSpace[] b : board) {
            for (BoardSpace p : b) {
                if (p != null) p.render(batch);
            }
        }
    }

    @Override
    public void dispose() {

    }

    // Getters and Setters
    public BoardSpace[][] getBoard() {return board;}

    public void setBoard(int x, int y, BoardSpace p) {board[y][x] = p;}

}
