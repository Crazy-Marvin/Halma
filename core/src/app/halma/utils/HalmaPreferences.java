package app.halma.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HalmaPreferences {
    private static final String SETTINGS_PREFERENCES = "settings";
    private static final String MAIN_PREFERENCES = "main";


    public static boolean isFirstRun() {
        return get(MAIN_PREFERENCES).getBoolean("first", true);
    }

    public static void setFirst() {
        get(MAIN_PREFERENCES).putBoolean("first", false).flush();
    }

    public static boolean isMonitoring() {
        return get(SETTINGS_PREFERENCES).getBoolean("errorMonitor", false);
    }

    public static void monitorErrors(boolean monitor) {
        get(SETTINGS_PREFERENCES).putBoolean("errorMonitor", monitor).flush();
    }

    public static void setGameLevel(String level) {
        get(SETTINGS_PREFERENCES).putString("level", level);
    }

    public static String getGameLevel(String defaultValue) {
        return get(SETTINGS_PREFERENCES).getString("level", defaultValue);
    }

    private static Preferences get(String name) {
        return Gdx.app.getPreferences(name);
    }
}
