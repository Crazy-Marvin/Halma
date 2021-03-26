package rocks.poopjournal.halma.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.w3c.dom.ls.LSOutput;
import rocks.poopjournal.halma.OneChecker;

import java.util.LinkedList;
import java.util.List;

public class PlayerButtons extends Group {
    //variables
    public TextButton b2, b3, b4, b5, b6;
    private float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    private float buttonHeight = height/18, posx = width/8 - width/16;
    public LinkedList<Actor> buttons = new LinkedList<>();
    private Skin skin;
    public int result = 2;
    private Menu menu;
    //constructor
    public PlayerButtons(Skin skin, Menu menu) {
        this.skin = skin;
        this.menu = menu;
    }
    //methods
    public PlayerButtons create(){
        b2 = new TextButton("2", skin); b3 = new TextButton("3", skin);
        b4 = new TextButton("4", skin); b5 = new TextButton("5", skin); b6 = new TextButton("6", skin);
        buttons.add(b2); buttons.add(b3); buttons.add(b4); buttons.add(b5); buttons.add(b6);
        for(int i = 0; i<buttons.size(); i++){
            Actor b = buttons.get(i);
            if(i == 0) b.setPosition(posx, height - buttonHeight*2);
            else b.setPosition(posx, buttons.get(i-1).getY() - 3*buttonHeight);
            b.setHeight(buttonHeight);
            this.addActor(b);
        }
        Image icon = new Image(skin, "man");
        icon.setSize(buttonHeight, buttonHeight);
        icon.setPosition(0, height - buttonHeight);
        this.addActor(icon);

        OneChecker.get().addButtons(buttons, OneChecker.get().generateSlot());
        return this;
    }
    public void clicked(Actor a){
        if(a instanceof TextButton)
            result = Integer.parseInt(String.valueOf(((TextButton) a).getText()));
        if(menu.state == 0) {
            menu.computerButtons.addAction(Actions.color(Color.WHITE, 2f));
            menu.state = 1;
            menu.titleLabel.setText("choose amount of computers");
        }
    }
}
