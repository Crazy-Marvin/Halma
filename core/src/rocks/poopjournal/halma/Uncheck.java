package rocks.poopjournal.halma;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.List;

public class Uncheck extends ClickListener {
    public static Uncheck get(){
        if(instance == null) instance = new Uncheck();
        return instance;
    }
    private static Uncheck instance;
    private Uncheck(){}
    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(event.getListenerActor() instanceof Button)
            ((Button) event.getListenerActor()).setChecked(false);
    }
    public static void addButtons(List e){
        for(Object b : e)
            if(b instanceof Button)
                ((Button)b).addListener(get());
    }
    public static void addButtons(Actor ... a){
        for(Object b : a)
            if(b instanceof Button)
                ((Button)b).addListener(get());
    }
}