package com.halma.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;
import com.halma.game.ui.UIButton;
import com.halma.game.utils.Assets;

public class MenuState extends State {

    private UIButton playButton;

    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void init() {
        playButton = new UIButton(handler, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Assets.playButton, UIButton.b.PLAY);
    }

    @Override
    public void update(float dt) {
        playButton.update(dt);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        playButton.render(batch);
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
