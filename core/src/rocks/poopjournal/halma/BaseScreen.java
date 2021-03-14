package rocks.poopjournal.halma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

public abstract class BaseScreen extends ScreenAdapter {
    protected Color bgColor;
    protected Skin skin;
    protected Stage stage;
    protected Table layout;
    protected Halma halma;
    protected Listener listener;

    public BaseScreen(Halma halma) {
        this.halma = halma;
        create();
    }

    private void create(){
        bgColor = Color.OLIVE;
        skin = new Skin(Gdx.files.internal("Skin.json"));
        stage = new Stage(new ScreenViewport());
        layout = new Table(skin);
        listener = new Listener();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(layout);
        layout.setFillParent(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void clicked(Actor a){
        System.out.println("clicked");
    }
    public class Listener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            BaseScreen.this.clicked(event.getListenerActor());
        }
    }
    protected void addButtonsToListener(Actor ... actors){
        for(Actor a : actors)
            a.addListener(listener);
    }
    protected void addButtonsToListener(List<Actor> list){
        for(Actor a : list)
            a.addListener(listener);
    }
}
