package rocks.poopjournal.halma;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import rocks.poopjournal.halma.play.Play;

public class Menu extends BaseScreen {
    //variables
    private int playerCount;
    private int computerCount;
    private Label titleLabel, playerCountLabel, comCountLabel;
    private TextButton player2, player3, player4, player5, player6;
    private TextButton com0, com1, com2, com3, com4, com5;
    private TextButton squareBoardButton;
    private TextButton startButton;
    private boolean squareBoard;

    public Menu(Halma halma) {
        super(halma);
        create();
    }

    public void create(){
        playerCount = 0;
        computerCount = 0;
        squareBoard = false;

        titleLabel = new Label("Halma!", skin);
        playerCountLabel = new Label("How many players?", skin);
        comCountLabel = new Label("How many computers?", skin);

        int playerSlot = OneChecker.get().generateSlot();

        player2 = new TextButton("2 player", skin); OneChecker.get().addButton(player2, playerSlot);
        player3 = new TextButton("3 player", skin); OneChecker.get().addButton(player3, playerSlot);
        player4 = new TextButton("4 player", skin); OneChecker.get().addButton(player4, playerSlot);
        player5 = new TextButton("5 player", skin); OneChecker.get().addButton(player5, playerSlot);
        player6 = new TextButton("6 player", skin); OneChecker.get().addButton(player6, playerSlot);

        int comSlot = OneChecker.get().generateSlot();

        com0 = new TextButton("0 com", skin); OneChecker.get().addButton(com0, comSlot);
        com1 = new TextButton("1 com", skin); OneChecker.get().addButton(com1, comSlot);
        com2 = new TextButton("2 com", skin); OneChecker.get().addButton(com2, comSlot);
        com3 = new TextButton("3 com", skin); OneChecker.get().addButton(com3, comSlot);
        com4 = new TextButton("4 com", skin); OneChecker.get().addButton(com4, comSlot);
        com5 = new TextButton("5 com", skin); OneChecker.get().addButton(com5, comSlot);

        squareBoardButton = new TextButton("Square Board", skin);

        startButton = new TextButton("start!", skin);

        layout.defaults().expand();

        layout.add(titleLabel); layout.row();
        layout.add(playerCountLabel); layout.row();
        layout.add(player2, player3, player4, player5, player6); layout.row();
        layout.add(comCountLabel); layout.row();
        layout.add(com0, com1, com2, com3, com4, com5); layout.row();
        layout.add(squareBoardButton); layout.row();
        layout.add(startButton);

        addButtonsToListener(com0, com1, com2, com3, com4, com5, player2, player3, player4, player5, player6, squareBoardButton, startButton);
    }

    @Override
    public void clicked(Actor a) {
        super.clicked(a);
        if(a == com0) computerCount = 0;
        if(a == com1) computerCount = 1;
        if(a == com2) computerCount = 2;
        if(a == com3) computerCount = 3;
        if(a == com4) computerCount = 4;
        if(a == com5) computerCount = 5;
        if(a == player2) playerCount = 2;
        if(a == player3) playerCount = 3;
        if(a == player4) playerCount = 4;
        if(a == player5) playerCount = 5;
        if(a == player6) playerCount = 6;
        if(a == squareBoardButton) squareBoard = squareBoardButton.isChecked();
        if(a == startButton) halma.setScreen(Play.createInstance(halma, playerCount, computerCount, squareBoard));

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