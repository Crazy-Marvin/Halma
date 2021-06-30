package rocks.poopjournal.halma.redesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import rocks.poopjournal.halma.BaseScreen;
import rocks.poopjournal.halma.Halma;
import rocks.poopjournal.halma.Uncheck;

public class About extends BaseScreen {
    private Table content;
    private TextButton code;
    public About(Halma halma) {
        super(halma);
        create();
    }
    private void create(){
        bgColor = Color.WHITE;

        code = tb("View source code"); code.setColor(Color.GRAY);
        content = new Table(skin);

        content.defaults().padRight(stage.getWidth()/20);
        content.add(lbl("Lead developer:"), lbl("Marvin")).row();
        content.add(lbl("Co-developer:"), lbl("Martin"));

        content.pad(stage.getWidth()/35);
        layout.add(content).row();
        layout.add(code);

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
        if(code == a) Gdx.net.openURI("https://github.com/Crazy-Marvin/Halma");
    }
}
