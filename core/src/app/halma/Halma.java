package app.halma;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import app.halma.redesign.MainMenu;

import java.util.Locale;

public class Halma extends Game {

	private I18NBundle bundle;
	
	@Override
	public void create () {
		System.out.println("program start :)");

		// Override to specific language with new Locale("de"), "en", etc.
		Locale locale = Locale.getDefault();
		System.out.println("your language is:");
		System.out.println(locale);
		I18NBundle.setSimpleFormatter(true); // Ensure same behaviour on GWT and other platforms
		bundle = I18NBundle.createBundle(Gdx.files.internal("strings"), locale, "ISO-8859-1");

		setScreen(new MainMenu(this));
	}

	I18NBundle getBundle() {
		return bundle;
	}

}
