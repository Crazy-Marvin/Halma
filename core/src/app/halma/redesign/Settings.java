package app.halma.redesign;

import app.halma.BaseScreen;
import app.halma.Halma;
import app.halma.Uncheck;
import app.halma.utils.ErrorMonitoring;
import app.halma.utils.HalmaPreferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Settings extends BaseScreen {
    // Difficulty Buttons
    private TextButton easy, medium, hard;
    private ImageButton backButton;


    public Settings(Halma halma) {
        super(halma);
        create();
        if (HalmaPreferences.isFirstRun()) HalmaPreferences.setFirst();
    }

    private void create() {
        bgColor = Color.WHITE;

        Label difficulty = lbl(getString("difficulty"));
        easy = tb(getString("easy"));
        medium = tb(getString("medium"));
        hard = tb(getString("hard"));

        Table content = new Table(skin);
        content.defaults().pad(5);

        ButtonGroup<TextButton> group = new ButtonGroup<>(easy, medium, hard);
        group.setMaxCheckCount(1);
        group.setUncheckLast(true);
        group.setChecked(HalmaPreferences.getGameLevel(getString("easy")));

        content.add(difficulty).padRight(10);
        content.add(easy, medium, hard).row();

        // Error monitoring switch
        // Load the images for the switch
        TextureRegionDrawable switchOn = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("redesign/tick.png"))));
        TextureRegionDrawable switchOff = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("redesign/cross.png"))));

        // Create a custom style for the CheckBox
        CheckBox.CheckBoxStyle switchStyle = new CheckBox.CheckBoxStyle();
        switchStyle.checkboxOn = switchOn;
        switchStyle.checkboxOff = switchOff;
        switchStyle.font = skin.getFont("font");

        // Create a CheckBox with the custom style
        CheckBox errorMonitoringSwitch = new CheckBox("", switchStyle);
        errorMonitoringSwitch.setChecked(Gdx.app.getPreferences("settings").getBoolean("errorMonitor"));
        errorMonitoringSwitch.background(skin.getDrawable("GrayButton10"));

        // Add a ClickListener to the CheckBox
        errorMonitoringSwitch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Toggle the error monitoring feature
                if (errorMonitoringSwitch.isChecked()) {
                    // Enable error monitoring
                    HalmaPreferences.monitorErrors(true);
                    Gdx.app.getPreferences("settings")
                            .putBoolean("errorMonitor", true);
                    ErrorMonitoring.startMonitoring();
                } else {
                    // Disable error monitoring
                    ErrorMonitoring.stopMonitoring();
                }
            }
        });

        content.add(lbl(getString("errorMonitor"))).colspan(3);
        content.add(errorMonitoringSwitch).pad(5);

        Table backButtonTable = new Table();
        backButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("redesign/back.png")))));
        backButtonTable.add(backButton).top().left().pad(10);

        layout.add(backButtonTable).top().left().expandX().pad(10).row();
        layout.add(content).center().expand();


        addButtonsToListener(easy, medium, hard, backButton);
        Uncheck.addButtons(easy, medium, hard, backButton);
    }

    private TextButton tb(String content) {
        TextButton tb = new TextButton(content, skin, "toggle");
        tb.getLabel().setColor(Color.BLACK);
        return tb;
    }

    private Label lbl(String content) {
        Label l = new Label(content, skin);
        l.setColor(Color.BLACK);
        return l;
    }

    @Override
    public void clicked(Actor a) {
        if (backButton == a) halma.setScreen(new MainMenu(halma));
        if (easy == a) HalmaPreferences.setGameLevel(getString("easy"));
        if (medium == a) HalmaPreferences.setGameLevel(getString("medium"));
        if (hard == a) HalmaPreferences.setGameLevel(getString("hard"));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
