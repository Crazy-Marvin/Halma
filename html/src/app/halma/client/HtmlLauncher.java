package app.halma.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import app.halma.Halma;

public class HtmlLauncher extends GwtApplication {

	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration config = new GwtApplicationConfiguration(true);
		config.padHorizontal = 0;
		config.padVertical = 0;
		config.disableAudio = true;
		config.useAccelerometer = false;
		return config;
	}

	@Override
	public ApplicationListener createApplicationListener () {
		return new Halma();
	}
}