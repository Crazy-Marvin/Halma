package com.halma.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.states.*;
import com.halma.game.utils.Assets;
import com.halma.game.utils.Controls;
import com.halma.game.utils.GameMaster;

public class Handler {

    public Main main;

    //States
    private State gameState;
    private State menuState;

    // Constructor
    public Handler(Main main) {
        this.main = main;
    }

    // Main methods
    public void init() {
        GameMaster.handler = this;
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setCurrentState(menuState);
        State.getCurrentState().init();
    }

    public void update(float dt) {
        Controls.update(dt);
        if (State.getCurrentState() != null) {
            State.getCurrentState().update(dt);
        }
    }

    public void render(SpriteBatch batch) {
        if (State.getCurrentState() != null) {
            State.getCurrentState().render(batch);
        }
    }

    public void dispose() {
        gameState.dispose();
        menuState.dispose();
        Assets.dispose();
    }

    // Other methods

    // Getters and Setters
    public GameState getGameState() {return (GameState) gameState;}
    public MenuState getMenuState() {return (MenuState) menuState;}
}
