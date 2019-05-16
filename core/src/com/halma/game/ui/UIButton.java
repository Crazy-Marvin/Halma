package com.halma.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.halma.game.Handler;
import com.halma.game.gameobjects.GameObject;
import com.halma.game.states.State;
import com.halma.game.utils.Controls;

public class UIButton extends GameObject {

    public enum b {
        PLAY,
        EXIT
    }

    public Rectangle bounds;
    private b button;
    private Texture t;

    public UIButton(Handler handler, int x, int y, Texture t, b button) {
        super(handler, x , y);
        bounds = new Rectangle(0, 0, 0, 0);
        bounds.x = x;
        bounds.y = y;
        this.t = t;
        bounds.width = t.getWidth();
        bounds.height = t.getHeight();
        bounds.x -= bounds.width/2;
        bounds.y -= bounds.height/2;
        this.button = button;
    }

    @Override
    public void update(float dt) {
        click();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(t, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void dispose() {
        t.dispose();
    }

    public void click() {
        if (Gdx.input.justTouched() && bounds.contains(Controls.x, Controls.y)) {
            clickEvent(button);
        }
    }

    private void clickEvent(b button) {
        switch(button) {
            case PLAY:
                State.setCurrentState(handler.getGameState());
                State.getCurrentState().init();
                break;
            case EXIT:
                Gdx.app.exit();
                break;
            default:
        }
    }
}
