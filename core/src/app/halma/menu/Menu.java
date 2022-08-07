package app.halma.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import app.halma.AboutScreen;
import app.halma.BaseScreen;
import app.halma.Halma;
import app.halma.RulesScreen;
import app.halma.play.Play;

public class Menu extends BaseScreen {
    //variables
    private int playerCount;
    private int computerCount;
    public Label titleLabel;
    public PlayerButtons playerButtons;
    public ComputerButtons computerButtons;
    private TextButton squareBoardButton;
    public Button startButton;
    private boolean squareBoard;
    public Label rules;
    public Label aboutLabel;

    public int state = 0;

    public Menu(Halma halma) {
        super(halma);
        create();
    }

    public void create(){
        playerCount = 2;
        computerCount = 0;
        squareBoard = false;

        titleLabel = new Label("choose amount of players", skin);
        titleLabel.setPosition(stage.getWidth()/2 - titleLabel.getWidth()/2, stage.getHeight()*0.9f);
        stage.addActor(titleLabel);

        playerButtons = new PlayerButtons(skin, this).create();
        computerButtons = new ComputerButtons(skin, this).create();
        stage.addActor(playerButtons);
        stage.addActor(computerButtons);

        squareBoardButton = new TextButton("Square Board", skin);
        squareBoardButton.setPosition(stage.getWidth()/2-squareBoardButton.getWidth()/2,
                stage.getHeight()/3-squareBoardButton.getHeight()/2);
        stage.addActor(squareBoardButton);
        float dif= stage.getWidth()/5;
        aboutLabel =new Label("about us",skin);
        rules=new Label("play-rules ",skin);
        aboutLabel.setPosition(stage.getWidth()/2 + dif/2- aboutLabel.getWidth()/2, stage.getHeight()/6- aboutLabel.getHeight()/2);
        rules.setPosition(stage.getWidth()/2 - dif/2- rules.getWidth()/2, stage.getHeight()/6-rules.getHeight()/2);
        stage.addActor(rules);
        stage.addActor(aboutLabel);
        rules.setColor(Color.CLEAR);
        aboutLabel.setColor(Color.CLEAR);


        startButton = new Button(skin, "play");
        startButton.setPosition(stage.getWidth()/2 - startButton.getWidth()/2, stage.getHeight()/2 - startButton.getHeight()/2);
        stage.addActor(startButton);
        addButtonsToListener(startButton, squareBoardButton);
        addButtonsToListener(playerButtons.buttons);
        addButtonsToListener(computerButtons.buttons);
        addButtonsToListener(aboutLabel, rules);
        startButton.setColor(1,1,1,0);
        computerButtons.setColor(1,1,1,0);
        playerButtons.setColor(1,1,1,0);

        playerButtons.addAction(Actions.color(Color.WHITE, 2f));
    }

    @Override
    public void clicked(Actor a) {
        super.clicked(a);
        if (computerButtons.buttons.contains(a)) computerButtons.clicked(a);
        if (playerButtons.buttons.contains(a)) playerButtons.clicked(a);
        if (a == squareBoardButton) squareBoard = squareBoardButton.isChecked();
        if (a == startButton) halma.setScreen(Play.createInstance(halma, playerCount, computerCount, squareBoard));
        if (a == aboutLabel)
            stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {public void run() {
                halma.setScreen(new AboutScreen(halma));
            }})));
        if (a == rules)
            stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {public void run() {
                halma.setScreen(new RulesScreen(halma));
            }}), Actions.fadeIn(1f)));
        computerCount = computerButtons.result;
        playerCount = playerButtons.result;

        if(squareBoard)
        {
            if(playerCount > 4)
                playerCount = 4;
            if(playerCount == 3)
                playerCount = 4;
        }

        if(computerCount >= playerCount)
            computerCount = playerCount-1;
    }
}