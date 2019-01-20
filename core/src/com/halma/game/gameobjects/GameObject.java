package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;

public abstract class GameObject {

    protected Handler handler;
    protected int x, y;

    public GameObject(Handler handler, int x, int y) {
        this.handler = handler;
        this.x = x;
        this.y = y;
    }

    public void init() {}
    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

    //Getters and Setters
    public int getX() {return x;}
    public int getY() {return y;}
}
