package com.halma.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;


public class Board extends GameObject {

    private BoardSpace[][] board;

    public Board(Handler handler, int x, int y) {
        super(handler, x, y);
        init();
    }

    public void init() {

        //Read file to create a 6 star board
        String[] a;
        String[][] b = new String[17][13];
        FileHandle file = Gdx.files.internal("6star_board.txt");
        a = file.readString().split("\n");

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].split(" ").length; j++) {
                b[i][j] = a[i].split(" ")[j];
            }
        }

        board = new BoardSpace[17][13];

        //Create the game board.
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[j].length; i++) {
                boolean isReal = (b[j][i].contains("1"));

                board[j][i] = new BoardSpace(handler, i, j, isReal);
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
