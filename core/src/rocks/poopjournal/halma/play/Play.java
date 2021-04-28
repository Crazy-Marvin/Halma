package rocks.poopjournal.halma.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import rocks.poopjournal.halma.BaseScreen;
import rocks.poopjournal.halma.ResultScreen;
import rocks.poopjournal.halma.Halma;

import java.util.LinkedList;

public class Play extends BaseScreen {

    private LinkedList<Player> players;
    private Board board;
    private int currentPlayerIndex = 0;
    private Label currentPlayerLabel;
    private static Play instance;
    private int coms, playerCount;
    private boolean isSquare;
    private Field selectedField;
    public static Play getInstance(){
        if(instance == null) throw new RuntimeException("Singelton error in play");
        return instance;
    }
    public static Play createInstance(Halma halma, int playerCount, int coms, boolean isSquare){
        instance = new Play(halma, playerCount, coms, isSquare);
        instance.create();
        return instance;
    }
    private Play(Halma halma, int playerCount, int coms, boolean isSquare) {
        super(halma);
        this.playerCount = playerCount;
        this.coms = coms;
        this.isSquare = isSquare;
    }
    private void create(){
        System.out.println("so viele spieler: " + playerCount + " davon " + coms + " coms und spielfeld eckig = " + isSquare);
        this.players = new LinkedList<>();
        PlayColorManager.maxPlayer = playerCount;
        for (int i = 0; i < playerCount; i++)
            this.players.add(new Player("player " + i));
        for (int i = 0; i < coms; i++)
            this.players.get(i).setComputer(true);
        for(Player player : players)
            PlayColorManager.apply(player, players.indexOf(player), isSquare);
        currentPlayerLabel = new Label(players.get(0).getName(), skin);
        currentPlayerLabel.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()*0.9f);
        stage.addActor(currentPlayerLabel);
        board = new Board(isSquare, stage);
        board.create();
        play();
    }
    private void play(){
        players.get(currentPlayerIndex).play();
    }
    public void move(Move move){
        if(move.player == players.get(currentPlayerIndex))
            move.move();
        WinnerChecker.players = playerCount;
        if(WinnerChecker.check(board)) {
            System.out.println("someone won!!!");
            halma.setScreen(new ResultScreen(halma, players.get(currentPlayerIndex)));
        }
        currentPlayerIndex++;
        if(currentPlayerIndex > players.size()-1)
            currentPlayerIndex = 0;
        currentPlayerLabel.setText(players.get(currentPlayerIndex).getName());
        play();
    }

    //getter and setter
    public LinkedList<Player> getPlayers() {return players;}
    public void setPlayers(LinkedList<Player> players) {this.players = players;}
    public Board getBoard() {return board;}
    public void setBoard(Board board) {this.board = board;}
    public int getCurrentPlayerIndex() {return currentPlayerIndex;}
    public void setCurrentPlayerIndex(int currentPlayerIndex) {this.currentPlayerIndex = currentPlayerIndex;}
    public Label getCurrentPlayerLabel() {return currentPlayerLabel;}
    public void setCurrentPlayerLabel(Label currentPlayerLabel) {this.currentPlayerLabel = currentPlayerLabel;}
    public Field getSelectedField() {return selectedField;}
    public void setSelectedField(Field selectedField) {this.selectedField = selectedField;}
}
