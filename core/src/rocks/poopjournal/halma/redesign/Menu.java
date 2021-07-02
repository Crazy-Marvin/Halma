package rocks.poopjournal.halma.redesign;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import rocks.poopjournal.halma.AboutScreen;
import rocks.poopjournal.halma.BaseScreen;
import rocks.poopjournal.halma.Halma;
import rocks.poopjournal.halma.RulesScreen;
import rocks.poopjournal.halma.play.Play;

public class Menu extends BaseScreen {
    //variables
    private Image icon;
    private TextButton newGame, about, help;
    public Menu(Halma halma) {
        super(halma);
        create();
    }
    private void create(){
        this.bgColor = Color.WHITE;
        icon = new Image(new Texture("redesign/ic_foreground.png")); icon.setScaling(Scaling.fit);
        newGame = new TextButton("New game", skin); newGame.setColor(Color.SKY);
        about = new TextButton("About", skin); about.setColor(Color.LIGHT_GRAY);
        help = new TextButton("Help", skin); help.setColor(Color.LIGHT_GRAY);

        layout.add(icon);layout.row();
        layout.add(newGame).width(stage.getWidth()/2f).fill().row();
        Table otherButtons = new Table(skin);
        otherButtons.add(about).padRight(100); otherButtons.add(help).padLeft(100);
        layout.add(otherButtons).pad(30);

        addButtonsToListener(help, about, newGame);
    }

    @Override
    public void clicked(Actor a) {
        if(a == help) halma.setScreen(new RulesScreen(halma));
        if(a == about) halma.setScreen(new About(halma));
        if(a == newGame) halma.setScreen(new PlayConfig(halma));
    }
}
