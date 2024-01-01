package app.halma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class AboutScreen extends BaseScreen{
    //fields
    Label titleText;
    Image titleImage;
    Label linkToMarvin;
    Label linkToMartin;
    Label viewSource;
    Label reportProblem;
    Label helpWithTranslation;
    Label privacyPolicy;

    Table persons;
    Table titleTable;
    //constructor
    public AboutScreen(Halma halma) {
        super(halma);
        create();
    }
    //methods
    private void create(){
        System.out.println("create about-screen");
        super.skin = new Skin(Gdx.files.internal("skin/flat/flat-earth-ui.json"));

        titleText = new Label("Halma's world", skin, "title");
        titleImage = new Image(skin, "earth");
        linkToMarvin = new Label("marvin, main developer", skin); linkToMarvin.setColor(Color.BLUE);
        linkToMartin = new Label("martin, co developer", skin); linkToMartin.setColor(Color.BLUE);
        helpWithTranslation = new Label("Help with translaiton", skin); linkToMarvin.setColor(Color.BLUE);
        privacyPolicy = new Label("Privacy Policy", skin); linkToMarvin.setColor(Color.BLUE);
        viewSource = new Label("100% transparent, view source code", skin); viewSource.setColor(Color.BLUE);
        reportProblem = new Label("I found a problem, help us making the game better :)", skin); reportProblem.setColor(Color.BLUE);
        persons = new Table();
        titleTable = new Table();

        layout.defaults().expand().fillY();
        titleTable.defaults().expand().fillY();
        persons.defaults().expand().fillY();

        persons.add(linkToMarvin).row();
        persons.add(linkToMartin).row();
        persons.add(helpWithTranslation);
        persons.add(termsOfService);
        persons.add(privacyPolicy);
        persons.add(viewSource).row();
        persons.add(reportProblem);

        titleTable.add(titleText).row();
        titleTable.add(titleImage).fill(false);

        layout.add(titleTable).row();
        layout.add(persons);

        for(Actor a : persons.getChildren())
            addButtonsToListener(a);
    }

    @Override
    public void clicked(Actor a) {
        String link = "https://google.com";
        if(a == linkToMartin)
            link = "https://github.com/mmelnizky";
        if(a == linkToMarvin)
            link = "https://github.com/Crazy-Marvin";
        if (a == helpWIthTranslation)
            link = "https://hosted.weblate.org/engage/halma/";
        if (a == privacyPolicy)
            link = "https://app.getterms.io/view/7FsFk/privacy/en-us";
        if(a == viewSource)
            link = "https://github.com/Crazy-Marvin/Halma";
        if(a == reportProblem)
            link = "https://github.com/Crazy-Marvin/Halma/issues";
        Gdx.net.openURI(link);
    }
}
