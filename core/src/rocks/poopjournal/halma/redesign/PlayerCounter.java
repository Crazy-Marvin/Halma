package rocks.poopjournal.halma.redesign;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class PlayerCounter {
    private Label botText, peopleText;
    private int bots, people;
    private boolean square;

    public PlayerCounter(Label botText, Label peopleText, boolean square) {
        this.botText = botText;
        this.peopleText = peopleText;

        this.people = 1;
        this.bots = 1;
        this.square = square;
    }

    public void update(int bots, int people, boolean square){
        if(people <= 0) return;
        if(bots <= 0) return;
        if(square){
            if(people + bots > 4)
                if(!this.square){bots = 1; people = 1;} //too much players!
                else return;
        }
        else{
            if(people + bots > 6) return; //too much players!
        }

        botText.setText(bots);
        peopleText.setText(people);

        this.bots = bots;
        this.people = people;
        this.square = square;
    }

    public Label getBotText() {
        return botText;
    }

    public Label getPeopleText() {
        return peopleText;
    }

    public int getBots() {
        return bots;
    }

    public int getPeople() {
        return people;
    }

    public boolean isSquare() {
        return square;
    }
}
