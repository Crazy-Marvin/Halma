package com.halma.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;
import com.halma.game.utils.GameMaster;

public class GameOverState extends State {


    public GameOverState(Handler handler) {
        super(handler);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            State.setCurrentState(handler.getMenuState());
            State.getCurrentState().init();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Assets.font.draw(batch, GameMaster.getWinner()+" has won!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    @Override
    public void dispose() {

    }
}
