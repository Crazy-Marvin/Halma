package com.halma.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;

public abstract class State {

    protected Handler handler;
    protected static State currentState;

    // Constructors
    public State(Handler handler) {
        this.handler = handler;
    }

    // Main methods for state classes
    public void init() {}
    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

    //Getters and Setters
    public static State getCurrentState() {return currentState;}

    public static void setCurrentState(State state) {currentState = state;}
}
