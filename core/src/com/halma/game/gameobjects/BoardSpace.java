package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;

public class BoardSpace extends GameObject {

    public Texture imgBoardSpace;
    public float drawX, drawY;

    public BoardSpace(Handler handler, int x, int y) {
        super(handler, x, y);
        this.imgBoardSpace = Assets.boardSpace;
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
        batch.draw(imgBoardSpace, drawX, drawY, imgBoardSpace.getWidth()*1.5f, imgBoardSpace.getHeight()*1.5f);
    }

    @Override
    public void dispose() {

    }
}
