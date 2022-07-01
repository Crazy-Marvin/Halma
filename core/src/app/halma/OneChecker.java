package app.halma;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.LinkedList;
import java.util.List;

public class OneChecker extends ClickListener {
    private static OneChecker INSTANCE;
    private LinkedList<LinkedList<Button>> slots = new LinkedList<>();
    private OneChecker()  {}
    public static OneChecker get(){
        if(INSTANCE == null) INSTANCE = new OneChecker();
        return INSTANCE;
    }
    public int generateSlot(){
        LinkedList e = new LinkedList();
        slots.add(e);
        return slots.indexOf(e);
    }
    public void addButton(Button a, int slot){
        slots.get(slot).add(a);
        a.addListener(this);
    }
    public void addButtons(List a, int slot){
        for(Object b : a) {
            if(b instanceof Button)
                slots.get(slot).add((Button) b);
            ((Button)b).addListener(this);
        }
        slots.get(slot).getFirst().setChecked(true);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Button b = (Button) event.getListenerActor();
        LinkedList<Button> clickedSlot = null;
        for(LinkedList<Button> slot : slots)
            for(Button currentButton : slot)
                if(currentButton == b)
                    clickedSlot = slot;
        for(Button button: clickedSlot){
            if(b == button)button.setChecked(true);
            else button.setChecked(false);
        }
    }
}