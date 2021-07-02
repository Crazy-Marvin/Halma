package rocks.poopjournal.halma.redesign;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import rocks.poopjournal.halma.BaseScreen;
import rocks.poopjournal.halma.Halma;
import rocks.poopjournal.halma.Uncheck;
import rocks.poopjournal.halma.play.Play;

public class PlayConfig extends BaseScreen {
    private Table settings, onlineOffline, boardType, botCountTable, peopleCountTable;
    private Label mode, board, bots, people, botCount, peopleCount;
    private TextButton online, offline, square, star, botUp, botDown, peopleUp, peopleDown, startButton;
    private PlayerCounter counter;
    public PlayConfig(Halma halma) {
        super(halma);
        bgColor = Color.WHITE;

        settings = new Table(skin); onlineOffline = new Table(skin); boardType = new Table(skin);
        botCountTable = new Table(skin); peopleCountTable = new Table(skin);

        mode = lbl("Mode"); board = lbl("Board"); bots = lbl("Bots");
        people = lbl("People"); botCount = lbl("1"); peopleCount = lbl("1");

        online = tb("Online"); offline = tb("Offline"); square = tb("Square");
        star = tb("Star"); botUp = tb("+"); botDown = tb("-");
        peopleUp = tb("+"); peopleDown = tb("-");
        startButton = tb("Start Game");

        online.setColor(Color.LIGHT_GRAY);
        square.setColor(Color.LIGHT_GRAY);
        startButton.setColor(Color.SKY);
        botUp.getLabel().setColor(Color.SKY);
        botDown.getLabel().setColor(Color.SKY);
        peopleDown.getLabel().setColor(Color.SKY);
        peopleUp.getLabel().setColor(Color.SKY);

        settings.pad(stage.getWidth()/20);
        settings.defaults().pad(stage.getWidth()/40);

        onlineOffline.add(online, offline); boardType.add(square, star); botCountTable.add(botDown, botCount, botUp);
        peopleCountTable.add(peopleDown, peopleCount, peopleUp);
        settings.add(mode, onlineOffline).row(); settings.add(board, boardType).row();
        settings.add(bots, botCountTable).row(); settings.add(people, peopleCountTable);
        layout.add(settings).row(); layout.add(startButton);

        addButtonsToListener(startButton, star, online, botDown, botUp, offline, peopleDown, peopleUp, square);

        Uncheck.addButtons(botUp, peopleUp, botDown, peopleDown, square, star, online, offline);

        counter = new PlayerCounter(botCount, peopleCount, true);
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
        if(a == botUp) counter.update(counter.getBots()+1, counter.getPeople(), counter.isSquare());
        if(a == peopleUp)counter.update(counter.getBots(), counter.getPeople()+1, counter.isSquare());
        if(a == botDown)counter.update(counter.getBots()-1, counter.getPeople(), counter.isSquare());
        if(a == peopleDown)counter.update(counter.getBots(), counter.getPeople()-1, counter.isSquare());
        if(a == star){counter.update(counter.getBots(), counter.getPeople(), false); square.setColor(Color.WHITE); star.setColor(Color.GRAY);}
        if(a == square){counter.update(counter.getBots(), counter.getPeople(), true); square.setColor(Color.GRAY); star.setColor(Color.WHITE);}
        if(a == online){online.setColor(Color.GRAY); offline.setColor(Color.WHITE);}
        if(a == offline){online.setColor(Color.WHITE); offline.setColor(Color.GRAY);}

        if(a == startButton) halma.setScreen(Play.createInstance(halma, counter.getBots() + counter.getPeople(),
                counter.getBots(), counter.isSquare()));

    }
}
