package rocks.poopjournal.halma.redesign;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import rocks.poopjournal.halma.BaseScreen;
import rocks.poopjournal.halma.Halma;
import rocks.poopjournal.halma.Uncheck;
import rocks.poopjournal.halma.play.Play;

public class PlayConfig extends BaseScreen {
    private TextButtonStyle whiteStyle, grayStyle;
    private Table settings, onlineOffline, boardType, botCountTable, peopleCountTable;
    private Label mode, board, bots, people, botCount, peopleCount;
    private TextButton online, offline, square, star, startButton;
    private Image botUp, botDown, peopleUp, peopleDown;
    private PlayerCounter counter;
    public PlayConfig(Halma halma) {
        super(halma);
        bgColor = Color.WHITE;

        // Tables
        settings = new Table(skin); onlineOffline = new Table(skin); boardType = new Table(skin);
        botCountTable = new Table(skin); peopleCountTable = new Table(skin);

        // Labels
        mode = lbl(getString("mode")); board = lbl(getString("board")); bots = lbl(getString("bots"));
        people = lbl(getString("people")); botCount = lbl("1"); peopleCount = lbl("1");

        // TextButtons
        online = tb(getString("online")); offline = tb(getString("offline")); square = tb(getString("square"));
        star = tb(getString("star")); startButton = tb(getString("start"), "Blue");

        // Images
        botUp = new Image(skin, "Plus"); botDown = new Image(skin, "Minus");
        peopleUp = new Image(skin, "Plus"); peopleDown = new Image(skin, "Minus");

        settings.pad(stage.getWidth()/20);
        settings.defaults().pad(stage.getWidth()/40);
        
        onlineOffline.add(online, offline); boardType.add(square, star); botCountTable.add(lbl("  "), botDown, lbl("  "), botCount, lbl("  "), botUp, lbl("  "));
        peopleCountTable.add(lbl("  "), peopleDown, lbl("  "), peopleCount, lbl("  "), peopleUp, lbl("  "));
        settings.add(mode, onlineOffline).row(); settings.add(board, boardType).row();
        settings.add(bots, botCountTable).row(); settings.add(people, peopleCountTable);
        layout.add(settings).row(); layout.add(startButton);

        System.out.println(square.getX());

        addButtonsToListener(startButton, star, online, botDown, botUp, offline, peopleDown, peopleUp, square);

        Uncheck.addButtons(botUp, peopleUp, botDown, peopleDown, square, star, online, offline);

        counter = new PlayerCounter(botCount, peopleCount, true);

        addGrayBorder(onlineOffline); addGrayBorder(boardType); addGrayBorder(botCountTable); addGrayBorder(peopleCountTable);

        whiteStyle = new TextButtonStyle(star.getStyle());
        grayStyle = new TextButtonStyle(whiteStyle);
        grayStyle.up = skin.getDrawable("GrayButton10"); 
        setActive(offline, online);
        setActive(star, square);
    }
    private TextButton tb(String content){
        return tb(content, "White");
    }
    private Label lbl(String content){
        return lbl(content, "default");
    }
    private TextButton tb(String content, String skinconfig){
        TextButton tb = new TextButton(content, skin, skinconfig);
        tb.getLabel().setColor(Color.BLACK);
        return tb;
    }
    private Label lbl(String content, String skinconfig){
        Label l = new Label(content, skin, skinconfig);
        l.setColor(Color.BLACK);
        return l;
    }
    private void addGrayBorder(Table t){
        t.pad(3,3,3,3);
        t.setBackground(skin.getDrawable("GrayButton10"));
    }
    private void setActive(TextButton white, TextButton gray){
        white.setStyle(whiteStyle);
        gray.setStyle(grayStyle);
    }


    @Override
    public void clicked(Actor a) {
        if(a == botUp) counter.update(counter.getBots()+1, counter.getPeople(), counter.isSquare());
        if(a == peopleUp)counter.update(counter.getBots(), counter.getPeople()+1, counter.isSquare());
        if(a == botDown)counter.update(counter.getBots()-1, counter.getPeople(), counter.isSquare());
        if(a == peopleDown)counter.update(counter.getBots(), counter.getPeople()-1, counter.isSquare());
        if(a == star){counter.update(counter.getBots(), counter.getPeople(), false); setActive(star, square);}
        if(a == square){counter.update(counter.getBots(), counter.getPeople(), true); setActive(square, star);}
        if(a == online){setActive(online, offline);}
        if(a == offline){setActive(offline, online);}

        if(a == startButton) halma.setScreen(Play.createInstance(halma, counter.getBots() + counter.getPeople(),
                counter.getBots(), counter.isSquare()));

    }
}
