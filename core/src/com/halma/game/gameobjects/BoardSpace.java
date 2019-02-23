package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;

public class BoardSpace extends GameObject {

    private Texture imgBoardSpace;
    protected float drawX, drawY;
    private boolean real;
    protected Circle circle;

    public BoardSpace(Handler handler, int x, int y, boolean real) {
        super(handler, x, y);
        this.imgBoardSpace = Assets.boardSpace;
        this.real = real;
        init();
    }

    public void init() {
        updateDrawCoord();
        circle = new Circle(drawX + imgBoardSpace.getWidth() * 0.75f, drawY + imgBoardSpace.getWidth() * 0.75f, imgBoardSpace.getWidth()*0.75f);
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

    // Other Methods
    protected void updateDrawCoord() {
        if (y % 2 == 0) {
            drawX = x * 42.2f - 21.1f;
        } else {drawX = x * 42.2f;}
        drawX += 103.5;

        drawY = y * 36.55f - 7+20;
    }

    protected void updateCircleCoord() {
        circle.x = drawX + imgBoardSpace.getWidth() * 0.75f;
        circle.y = drawY + imgBoardSpace.getWidth() * 0.75f;
    }

    //Getters and Setters
    public float getDrawX() {return drawX;}
    public float getDrawY() {return drawY;}
    public boolean isReal() {return real;}
    public Circle getCircle() {return circle;}

    public void setDrawX(float drawX) {this.drawX = drawX;}
    public void setDrawY(float drawY) {this.drawY = drawY;}
    public void setReal(boolean real) {this.real = real;}
    public void setCircle(Circle circle) {this.circle = circle;}
}
