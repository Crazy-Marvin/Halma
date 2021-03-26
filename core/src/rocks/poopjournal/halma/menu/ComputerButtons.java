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
import rocks.poopjournal.halma.OneChecker;

import java.util.LinkedList;

public class ComputerButtons extends Group {
    //variables
    public Button b0, b1, b2, b3, b4, b5;
    private float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
    private float buttonHeight = height/18, posx = width - width/8;
    public LinkedList<Actor> buttons = new LinkedList<>();
    private Skin skin;
    public int result = 0;
    private Menu menu;
    //constructor
    public ComputerButtons(Skin skin, Menu menu) {
        this.skin = skin;
        this.menu = menu;
    }
    public ComputerButtons create(){
        b0 = new TextButton("0", skin); b1 = new TextButton("1", skin);
        b2 = new TextButton("2", skin); b3 = new TextButton("3", skin);
        b4 = new TextButton("4", skin); b5 = new TextButton("5", skin);
        buttons.add(b0); buttons.add(b1); buttons.add(b2); buttons.add(b3); buttons.add(b4); buttons.add(b5);
        for(int i = 0; i<buttons.size(); i++){
            Actor b = buttons.get(i);
            if(i == 0) b.setPosition(posx, height - buttonHeight*2);
            else b.setPosition(posx, buttons.get(i-1).getY() - 3*buttonHeight);
            b.setHeight(buttonHeight);
            this.addActor(b);
        }
        Image icon = new Image(skin, "com");
        icon.setSize(buttonHeight, buttonHeight);
        icon.setPosition(width - buttonHeight, height - buttonHeight);
        this.addActor(icon);

        OneChecker.get().addButtons(buttons, OneChecker.get().generateSlot());
        return this;
    }
    public void clicked(Actor a){
        if(a instanceof TextButton)
            result = Integer.parseInt(String.valueOf(((TextButton) a).getText()));
        if(menu.state == 1)
        {
            menu.startButton.addAction(Actions.color(Color.WHITE, 2f));
            menu.state = 2;
            menu.titleLabel.setColor(Color.WHITE);
            menu.titleLabel.setText("HALMA");
            menu.titleLabel.pack();
            menu.infoLabel.addAction(Actions.color(Color.BLUE, 2f));
            menu.rules.addAction(Actions.color(Color.BLUE, 2f));
            menu.titleLabel.setPosition(width/2 - menu.titleLabel.getWidth()/2, height*0.9f);
            menu.titleLabel.addAction(Actions.forever(Actions.sequence(
                    Actions.color(Color.RED, 2f),
                    Actions.color(Color.GREEN, 2f),
                    Actions.color(Color.BLUE, 2f),
                    Actions.color(Color.GREEN, 2f),
                    Actions.color(Color.BLUE, 2f)
            )));
        }
    }
}
