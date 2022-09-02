package app.halma.play;

import java.util.LinkedList;
import java.util.Random;

public class Player {
    //variables
    private boolean isComputer;
    private boolean turn;
    private char color;
    private String name;
    //constructor
    public Player() {
        isComputer = false;
    }
    public Player(String name) {
        this.name = name;
    }
    private LinkedList<Field> allColorFields = new LinkedList<>();
    private LinkedList<Field> possibleFields = new LinkedList<>();
    public void play() {
        System.out.println("play");
        turn = true;
        if(!isComputer) return;
        randomPossibleField();
        randomField(possibleFields).clicked();
        possibleFields.clear();
        allColorFields.clear();
        System.out.println("comuputer made move!");
    }
    private void randomColoredField(){
        Board board = Play.getInstance().getBoard();
        for(Field f : board.getFields())
            if(f.getColorChar() == color)
                allColorFields.add(f);
        randomField(allColorFields).clicked();
    }
    private void randomPossibleField(){
        Board board = Play.getInstance().getBoard();
        do {
            System.out.println("times run in schleife");
            randomColoredField();
            for(Field f : board.getFields())
                if(f.getColorChar() == Field.POSSIBLE)
                    possibleFields.add(f);
        }
        while(possibleFields.size() == 0);
        System.out.println(possibleFields.size());
    }
    private Field randomField(LinkedList<Field> fields){ //get random actor from list
        Random r = new Random();
        return fields.get(r.nextInt(fields.size()));
    }
    public void makeMove(Field toMove){
        if(!turn) return;
        turn = false;
        Move move = new Move(this, Play.getInstance().getSelectedField(), toMove);
        if(move.from.getColorChar() != color) return;
        Play.getInstance().move(move);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player)
            return ((Player) obj).name.equals(name);
        return false;
    }
    public void computerClickField(final Field f, final int millis){
        // Commented out because of GWT
        /*System.out.println("before sleep");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("before sleep");
                    Thread.sleep(millis);
                    System.out.println("after sleep");
                    f.clicked();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
    //getter and setter
    public boolean isComputer() {return isComputer;}
    public void setComputer(boolean computer) {isComputer = computer;}
    public boolean isTurn() {return turn;}
    public void setTurn(boolean turn) {this.turn = turn;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public char getColor() {return color;}
    public void setColor(char color) {this.color = color;}
}
