package com.halma.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;
import com.halma.game.utils.Controls;

public class BoardPiece extends BoardSpace {

    enum BoardPiece_State {
        NOT_SELECTED,
        SELECTED
    }

    private Board board;
    private BoardPiece_State state;
    private String color;
    private Texture boardPieceImg;

    // Constructor
    public BoardPiece(Handler handler, int x, int y, int type) {
        super(handler, x, y, true);
        init(type);
    }

    // Main Method
    private void init(int type) {
        board = handler.getGameState().getBoard();
        board.getBoard()[y][x].setReal(false);
        if (state == null) {state = BoardPiece_State.NOT_SELECTED;}
        switch (type) {
            case 2: color = "Red"; boardPieceImg = Assets.boardPiece_Red;
            default: color = "None";
        }
        updateDrawCoord();
        circle = new Circle(drawX + boardPieceImg.getWidth() * 0.75f, drawY + boardPieceImg.getWidth() * 0.75f, boardPieceImg.getWidth()*0.75f);
    }

    @Override
    public void update(float dt) {
        select();
        move();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (state == BoardPiece_State.SELECTED) {
            batch.setColor(1, 1, 1, 0.5f);
        }
        batch.draw(boardPieceImg, drawX, drawY, boardPieceImg.getWidth()*1.5f, boardPieceImg.getHeight()*1.5f);
        batch.setColor(1, 1,1 ,1);
    }

    @Override
    public void dispose() {

    }

    // Other Methods
    public void select() {
        if (state == BoardPiece_State.NOT_SELECTED) {
            if (Gdx.input.justTouched() && circle.contains(Controls.x, Controls.y)) {
                state = BoardPiece_State.SELECTED;
                System.out.println("Changed state of piece.");
            }
        }
    }

    public void move() {
        if (state == BoardPiece_State.SELECTED && Gdx.input.justTouched()) {

                for (int j = 0; j < board.getBoard().length; j++) {
                    for (int i = 0; i < board.getBoard()[j].length; i++) {

                        if (board.getBoard()[j][i].isReal() && board.getBoard()[j][i].getCircle().contains(Controls.x, Controls.y)) {
                            board.getBoard()[y][x].setReal(true);
                            x = board.getBoard()[j][i].x;
                            y = board.getBoard()[j][i].y;
                            updateDrawCoord();
                            updateCircleCoord();
                            state = BoardPiece_State.NOT_SELECTED;
                            board.getBoard()[j][i].setReal(false);
                        }

                    }
                }

        }
    }

    // Getters and Setters

}
