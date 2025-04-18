package app.halma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import app.halma.menu.Menu;

public class RulesScreen extends BaseScreen {
    private Label text;
    private int index = 1;
    private static final int TOTAL_RULES = 21; // Total number of rules
    private Halma halma;

    public RulesScreen(Halma halma) {
        super(halma);
        this.halma = halma;
        create();
    }

    private void create() {
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index++;
                if (index > TOTAL_RULES) {
                    halma.setScreen(new Menu(halma));
                } else {
                    text.setText(getRuleText(index));
                }
            }
        });
        text = new Label(getRuleText(index), skin);
        layout.add(text);
    }

    private String getRuleText(int index) {
        return getString("rules_" + index);
    }
}
