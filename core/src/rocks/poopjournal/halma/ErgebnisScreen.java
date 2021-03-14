package rocks.poopjournal.halma;

import com.badlogic.gdx.scenes.scene2d.Actor;
import rocks.poopjournal.halma.play.Player;

public class ErgebnisScreen extends BaseScreen{
    private Player player;
    public ErgebnisScreen(Halma halma, Player player) {
        super(halma);
        this.player = player;
        create();
    }
    public void create(){
        layout.add(player.getName() + " hat gewonnen! \n GLÜCKWUNSCH \n drücke um zurück zu kommen");
        stage.addListener(listener);
    }

    @Override
    public void clicked(Actor a) {
        halma.setScreen(new Menu(halma));
    }
}
