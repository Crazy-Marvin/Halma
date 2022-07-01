package app.halma.redesign;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;

import app.halma.BaseScreen;
import app.halma.Halma;
import app.halma.RulesScreen;

public class MainMenu extends BaseScreen {
    //variables
    private Image icon;
    private TextButton newGame, about, help;
    public MainMenu(Halma halma) {
        super(halma);
        create();
    }
    private void create(){
        this.bgColor = Color.WHITE;
        icon = new Image(new Texture("redesign/ic_foreground.png")); icon.setScaling(Scaling.fit);
        newGame = new TextButton(getString("newGame"), skin, "Blue");
        about = new TextButton(getString("about"), skin, "default");
        help = new TextButton(getString("help"), skin, "default");

        addActorsToStage(icon, newGame, about, help);
        addButtonsToListener(help, about, newGame);

        layout();
    }
    private void layout(){
        setBounds(icon, 272, 172, 176, 176); icon.debug();
        setBounds(newGame, 204, 72, 312, 36);
        setBounds(about, 204, 24, 152, 36);
        setBounds(help, 364, 24, 152, 36);
    }

    private void setBounds(Actor actor, float x, float y, float width, float height){
        Vector2 realSize = new Vector2(stage.getWidth(), stage.getHeight());
        Vector2 protoSize = new Vector2(720, 360);

        Vector2 factor = new Vector2(realSize.x / protoSize.x, realSize.y / protoSize.y);

        float em = realSize.x/protoSize.x;

        if(ratio(realSize) > ratio(protoSize) * 1.25f    ||    ratio(realSize) * 1.25f < ratio(protoSize)){
                System.out.println("sehr schlechte ratio!!!!!");
                if(ratio(realSize) > ratio(protoSize)) //width ist größer
                    em = realSize.y/protoSize.y;
                if(ratio(realSize) < ratio(protoSize)) //height ist größer
                    em = realSize.x/protoSize.x;
            }
        
        actor.setBounds(x*factor.x, y*factor.y, width*em, height*em);
    }
    private float ratio(Vector2 vector){
        return vector.x/vector.y;
    }
    @Override
    public void clicked(Actor a) {
        if(a == help) halma.setScreen(new RulesScreen(halma));
        if(a == about) halma.setScreen(new About(halma));
        if(a == newGame) halma.setScreen(new PlayConfig(halma));
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        layout();
    }
}