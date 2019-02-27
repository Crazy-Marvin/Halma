package com.halma.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;
import com.halma.game.utils.Controls;

public class HintSpace extends BoardSpace {

    private Texture hintSpaceImg;
    private BoardPiece piece;

    public HintSpace(Handler handler, int x, int y, BoardPiece piece) {
        super(handler, x, y, true);
        this.piece = piece;
        init();
        System.out.println(x + ", " + y);
    }

    @Override
    public void init() {
        hintSpaceImg = Assets.hintPiece;
        super.init();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(1, 1, 1, 0.5f);
        batch.draw(hintSpaceImg, drawX, drawY, hintSpaceImg.getWidth()*1.5f, hintSpaceImg.getHeight()*1.5f);
        batch.setColor(1, 1, 1, 1);
    }

    @Override
    public void dispose() {

    }

    public boolean selected() {
        if (Gdx.input.justTouched() && circle.contains(Controls.x, Controls.y)) {
            return true;
        }
        return false;
    }

    //Getters and Setters
    public BoardPiece getPiece() {return piece;}

    public void setPiece(BoardPiece piece) {this.piece = piece;}

}
