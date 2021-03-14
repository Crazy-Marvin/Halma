package rocks.poopjournal.halma.play;

public class Move {
    public Player player;
    public Field from, to;

    public Move(Player player, Field from, Field to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public void move(){
        to.setColorChar(from.getColorChar());
        from.setColorChar(Field.NONE);
    }
}
