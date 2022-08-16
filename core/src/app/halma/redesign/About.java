package app.halma.redesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import app.halma.BaseScreen;
import app.halma.Halma;
import app.halma.Uncheck;

public class About extends BaseScreen {
    private Table content;
    private TextButton code;
    public About(Halma halma) {
        super(halma);
        create();
    }
    private void create(){
        bgColor = Color.WHITE;

        code = tb(getString("viewSource"));
        content = new Table(skin);

        content.defaults().padRight(stage.getWidth()/20);
        String[] leadDev = getString("director").split(":");
        content.add(lbl(leadDev[0] + ":"), lbl(leadDev[1])).row();
        String[] coDev = getString("leadDev").split(":");
        content.add(lbl(coDev[0] + ":"), lbl(coDev[1]));

        content.pad(stage.getWidth()/35);
        layout.add(content).row();
        layout.add(code).expandX();

        addButtonsToListener(code);
        Uncheck.addButtons(code);
    }
    private TextButton tb(String content){
        TextButton tb = new TextButton(content, skin);
        tb.getLabel().setColor(Color.BLACK);
        return tb;
    }
    private Label lbl(String content){
        Label l = new Label(content, skin);
        l.setColor(Color.BLACK);
        return l;
    }

    @Override
    public void clicked(Actor a) {
        if(code == a) Gdx.net.openURI(getString("repoURL"));
    }
}
