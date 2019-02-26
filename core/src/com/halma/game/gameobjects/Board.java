package com.halma.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;


public class Board extends GameObject {

    private BoardSpace[][] board;
    private BoardPiece[] redPieces, greenPieces, yellowPieces, bluePieces, purplePieces, skyBluePieces;

    public Board(Handler handler, int x, int y) {
        super(handler, x, y);
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
        redPieces = new BoardPiece[10];

        //Create the game board.
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[j].length; i++) {
                boolean isReal = !(b[j][i].contains("0"));

                board[j][i] = new BoardSpace(handler, i, j, isReal);

                if (b[j][i].contains("2")) {
                    for (int k = 0; k < redPieces.length; k++) {
                        if (redPieces[k] == null) {
                            redPieces[k] = new BoardPiece(handler, i, j, 2);
                            break;
                        }
                    }
                }
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
        for (BoardPiece p : redPieces) {
            if (p != null) p.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (BoardSpace[] b : board) {
            for (BoardSpace p : b) {
                if (p != null) p.render(batch);
            }
        }
        for (BoardPiece p : redPieces) {
            if (p != null) p.render(batch);
        }
    }

    @Override
    public void dispose() {
        if (board != null) {
            for (BoardSpace[] b : board) {
                for (BoardSpace bs : b) {
                    if (bs != null) bs.dispose();
                }
            }
        }
        if (redPieces != null) {
            for (BoardPiece p : redPieces) {
                if (p != null) p.dispose();
            }
        }
    }

    // Getters and Setters
    public BoardSpace[][] getBoard() {return board;}
    public BoardSpace getBoardSpace(int x, int y) {return board[y][x];}
    public BoardPiece[] getRedPieces() {return redPieces;}
    public BoardPiece[] getGreenPieces() {return greenPieces;}


    public void setBoard(int x, int y, BoardSpace p) {board[y][x] = p;}
    public void setRedPieces(int i, BoardPiece p) {redPieces[i] = p;}
    public void setGreenPieces(int i, BoardPiece p) {greenPieces[i] = p;}

}
