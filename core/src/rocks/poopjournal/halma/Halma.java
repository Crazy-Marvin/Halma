package rocks.poopjournal.halma;

import com.badlogic.gdx.Game;
import rocks.poopjournal.halma.redesign.MainMenu;

import java.util.Locale;

public class Halma extends Game {
	/*SpriteBatch batch;
	Texture img;*/
	
	@Override
	public void create () {
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");*/
		System.out.println("program start :)");
		System.out.println("your language is:");
		System.out.println(Locale.getDefault().toString());
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
		super.render();
	}

	@Override
	public void dispose () {
		/*batch.dispose();
		img.dispose();*/
	}
}
