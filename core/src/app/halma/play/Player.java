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

    private final LinkedList<Field> allColorFields = new LinkedList<>();
    private final LinkedList<Field> possibleFields = new LinkedList<>();
    private Field currentField;

    public void play() {
        turn = true;
        if (!isComputer) return;
        randomPossibleField();
        Field field = randomField(possibleFields);
        if (field != null) field.clicked();
        possibleFields.clear();
        allColorFields.clear();
    }

    private void randomColoredField() {
        Board board = Play.getInstance().getBoard();
        for (Field f : board.getFields())
            if (f.getColorChar() == color)
                allColorFields.add(f);
        currentField = randomField(allColorFields);
        currentField.clicked();
    }

    private void randomPossibleField() {
        Board board = Play.getInstance().getBoard();
        do {
            randomColoredField();
            for (Field f : board.getFields())
                if (f.getColorChar() == Field.POSSIBLE)
                    possibleFields.add(f);
        }
        while (possibleFields.isEmpty());
    }

    private Field randomField(LinkedList<Field> fields) { //get random actor from list
        if (isComputer && fields == possibleFields) {
            LinkedList<Field> targetFields = getTargetFields();// Get target corner fields
            if (targetFields != null && targetFields.isEmpty()) return null;
            // Get possible target location
            Field target = targetFields.get(0);
            for (Field f : targetFields) {
                if (f.getColorChar() == Field.NONE) {
                    target = f;
                    break;
                }
            }

            // Check next shortest possible field
            Field field = fields.get(0);
            for (Field f : fields) {
                if (f.getPos().dst2(target.getPos()) < field.getPos().dst2(target.getPos())) {
                    field = f;
                }
            }

            return field;
        }
        Random r = new Random();
        return fields.get(r.nextInt(fields.size()));
    }

    private LinkedList<Field> getTargetFields() {
        return Play.getInstance().getBoard().lower;
    }

    public void makeMove(Field toMove) {
        if (!turn) return;
        turn = false;
        Move move = new Move(this, Play.getInstance().getSelectedField(), toMove);
        if (move.from.getColorChar() != color) return;
        Play.getInstance().move(move);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player)
            return ((Player) obj).name.equals(name);
        return false;
    }

    public void computerClickField(final Field f, final int millis) {
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
    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
