package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.utils.Controls;

public class Piece extends GameObject {

    enum PieceState {
        SELECTED,
        NOT_SELECTED
    }

    private Board board;
    private PieceState state;
    private String color;
    private boolean isDown;
    private Sprite sprite;
    private Texture texture;

    public Piece(Handler handler, int x, int y, String color, Texture texture) {
        super(handler, x, y);
        this.color = color;
        this.texture = texture;
        init();
    }

    // Main Methods
    public void init() {
        float scale = 1.3f;
        board = handler.getGameState().getBoard();
        state = PieceState.NOT_SELECTED;
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth()*scale, sprite.getHeight()*scale);
        isDown = false;
        //System.out.println("System: " + sprite.getBoundingRectangle().x + ", " + sprite.getBoundingRectangle().y + ", " + (sprite.getBoundingRectangle().width + sprite.getBoundingRectangle().x) + ", " + (sprite.getBoundingRectangle().height + sprite.getBoundingRectangle().y));
    }

    @Override
    public void update(float dt) {
        sprite.setX(x*21.24f+95);
        sprite.setY(y*36.7f+6);
        select();
        move();
        if (isDown) {sprite.setTexture(handler.getGameState().getRedPieceDownImg());}
        else {sprite.setTexture(handler.getGameState().getRedPieceUpImg());}
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    //Other methods
    public void select() {
        //System.out.println("System: " + sprite.getBoundingRectangle().x + ", " + sprite.getBoundingRectangle().y + ", " + sprite.getBoundingRectangle().width + ", " + sprite.getBoundingRectangle().height);
        if (state == PieceState.NOT_SELECTED && sprite.getBoundingRectangle().contains(Controls.x, Controls.y)) {
            state = PieceState.SELECTED;
            System.out.println("Piece changed to selected state.");
        }
    }

    public void move() {
        if (state == PieceState.SELECTED) {
            //move right
            if (sprite.getBoundingRectangle().contains(Controls.x-sprite.getBoundingRectangle().width, Controls.y)) {
                state = PieceState.NOT_SELECTED;
                board.setBoard(x, y, null);
                x++;
                board.setBoard(x, y, this);
                isDown = !isDown;
                sprite.setTexture(handler.getGameState().getRedPieceDownImg());
                System.out.println("Piece changed to selected state 111.");
            }
            //move left
            if (sprite.getBoundingRectangle().contains(Controls.x+sprite.getBoundingRectangle().width, Controls.y)) {
                state = PieceState.NOT_SELECTED;
                board.setBoard(x, y, null);
                x--;
                board.setBoard(x, y, this);
                isDown = !isDown;
                System.out.println("Piece changed to selected state 111.");
            }
            //move up
            if (sprite.getBoundingRectangle().contains(Controls.x, Controls.y-sprite.getBoundingRectangle().height)) {
                state = PieceState.NOT_SELECTED;
                board.setBoard(x, y, null);
                y++;
                board.setBoard(x, y, this);
                isDown = !isDown;
                System.out.println("Piece changed to selected state 111.");
            }
            //move down
            if (sprite.getBoundingRectangle().contains(Controls.x, Controls.y+sprite.getBoundingRectangle().height)) {
                state = PieceState.NOT_SELECTED;
                board.setBoard(x, y, null);
                y--;
                board.setBoard(x, y, this);
                isDown = !isDown;
                System.out.println("Piece changed to selected state 111.");
            }
        }
    }

    // Getters and Setters
    public String getColor() {return color;}
    public PieceState getState() {return state;}

    public void setColor(String color) {this.color = color;}
    public void setState(PieceState state) {this.state = state;}
}
