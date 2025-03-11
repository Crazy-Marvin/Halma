package app.halma.redesign;

import app.halma.BaseScreen;
import app.halma.Halma;
import app.halma.Uncheck;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;

public class About extends BaseScreen {
    private Table content;
    private TextButton code;
    private TextButton translations;
    private TextButton terms;
    private TextButton privacy;

    public About(Halma halma) {
        super(halma);
        create();
    }

    private void create() {
        bgColor = Color.WHITE;

        code = tb(getString("viewSource"));
        translations = tbw(getString("translations"));
        terms = tbw(getString("terms"));
        privacy = tbw(getString("privacy"));

        content = new Table(skin);

        content.defaults().padRight(stage.getWidth() / 20);
        String[] leadDev = getString("director").split(":");
        content.add(lbl(leadDev[0] + ":"), lbl(leadDev[1])).row();
        String[] coDev = getString("leadDev").split(":");
        content.add(lbl(coDev[0] + ":"), lbl(coDev[1]));

        content.pad(stage.getWidth() / 35);
        layout.add(content).row();
        layout.add(code).expandX();
        layout.row();

        Table bottomRow = new Table();
        bottomRow.defaults().pad(10);
        // Add the buttons and dots to the bottomRow
        bottomRow.add(translations);
        bottomRow.add(getDot()).width(10); // Set size here
        bottomRow.add(terms);
        bottomRow.add(getDot()).width(10); // Set size here
        bottomRow.add(privacy);
        layout.add(bottomRow).padTop(20);

        addButtonsToListener(code, translations, terms, privacy);
        Uncheck.addButtons(code, translations, terms, privacy);
    }

    private static Image getDot() {
        Texture dotTexture = new Texture("circle.png");

        TextureRegion dotRegion = new TextureRegion(dotTexture);

        Image dot = new Image(dotRegion);
        dot.setColor(Color.GRAY);
        dot.setScaling(Scaling.fit);
        dot.setWidth(10);
        dot.setHeight(10);

        return dot;
    }

    private TextButton tb(String content) {
        TextButton tb = new TextButton(content, skin);
        tb.getLabel().setColor(Color.BLACK);
        return tb;
    }

    private TextButton tbw(String content) {
        TextButton tb = new TextButton(content, skin, "White");
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
        if (code == a) Gdx.net.openURI(getString("repoURL"));
        if (translations == a) Gdx.net.openURI(getString("translationsURL"));
        if (terms == a) Gdx.net.openURI(getString("termsURL"));
        if (privacy == a) Gdx.net.openURI(getString("privacyURL"));
    }
}
