package rocks.poopjournal.halma.menu;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Animator implements TweenAccessor<Actor> {
    //keys
    public static final int RGBA = 0;
    public static final int POSITION = 1;
    //methods
    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch(tweenType)
        {
            case RGBA:
                returnValues[0] = target.getColor().r;
                returnValues[1] = target.getColor().g;
                returnValues[2] = target.getColor().b;
                returnValues[3] = target.getColor().a;
                break;
            case POSITION:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                break;
        }
        return 0;
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch(tweenType)
        {
            case RGBA:
                target.setColor(newValues[0], newValues[1], newValues[2], newValues[3]);
                break;
            case POSITION:
                target.setPosition(newValues[0], newValues[1]);
        }
    }
}
