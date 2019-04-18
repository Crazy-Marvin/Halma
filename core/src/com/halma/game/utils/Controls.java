package com.halma.game.utils;

import com.badlogic.gdx.Gdx;

public class Controls {

    public static float x, y;

    public static void update(float dt) {
        if (Gdx.input.isTouched()) {
            x = Gdx.input.getX();
            y = Gdx.graphics.getHeight()-Gdx.input.getY();
        } else {
            x = -100;
            y = -100;
        }
    }
}
