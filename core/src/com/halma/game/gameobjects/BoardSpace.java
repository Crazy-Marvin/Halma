package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;

public class BoardSpace extends GameObject {

    private Texture imgBoardSpace;
    private float drawX, drawY;
    private boolean real;

    public BoardSpace(Handler handler, int x, int y, boolean real) {
        super(handler, x, y);
        this.imgBoardSpace = Assets.boardSpace;
        this.real = real;
        init();
    }

    public void init() {
        if (y % 2 == 0) {
            drawX = x * 42.2f - 21.1f;
        } else {drawX = x * 42.2f;}
        drawX += 103.5;

        drawY = y * 36.55f - 7;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        if (real)
            batch.draw(imgBoardSpace, drawX, drawY, imgBoardSpace.getWidth()*1.5f, imgBoardSpace.getHeight()*1.5f);
    }

    @Override
    public void dispose() {

    }

    //Getters and Setters
    public float getDrawX() {return drawX;}
    public float getDrawY() {return drawY;}
    public boolean isReal() {return real;}

    public void setDrawX(float drawX) {this.drawX = drawX;}
    public void setDrawY(float drawY) {this.drawY = drawY;}
    public void setReal(boolean real) {this.real = real;}
}
