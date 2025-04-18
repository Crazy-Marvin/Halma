package app.halma;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import app.halma.menu.Menu;

public class RulesScreen extends BaseScreen{
    private Label text;
    private int index = 0;
    private String[] texts = new String[]{
            "Spieltyp: Taktikspiel\n" +
                    "Spielerzahl: 1-4\n" +
                    "Altersempfehlung: 8-88\n" +
                    "Spieldauer: ca. 30-45 Minuten\n" +
                    "\n",
                    "SPIELZIEL:\n" +
                    "\n" +
                    "Wer es zuerst schafft, seine Holz-\n" +
                    "kegel durch geschickte Züge in\n" +
                    "den gegenüberliegenden Hof zu brin-\n" +
                    "gen, gewinnt das Spiel.\n" +
                    "\n" ,
                    "SPIELMATERIAL:\n" +
                    "\n" +
                    "1 Doppelspielplan\n" +
                    "66 Halmakegel:\n" +
                    "19 schwarze\n" +
                    "19 rote\n" +
                    "15 grüne\n" +
                    "13 gelbe\n" +
                    "\n" ,
                    "SPIELVORBEREITUNG\n" +
                    "\n" +
                    "Das Spiel kann von 2, 3 oder 4\n" +
                    "Personen gespielt werden. Mit dem\n" +
                    "Karoplan können 2 oder 4 Spieler,\n" +
                    "mit dem Sternplan 2 oder 3 Spieler\n" +
                    "spielen.\n" +
                    "\n" ,
                    "Es spielen:\n" +
                    "2 Personen mit 2 x 19 Kegeln\n" +
                    "3 Personen mit 3 x 15 Kegeln\n" +
                    "4 Personen mit 4 x 13 Kegeln\n" +
                    "\n" ,
                    "Auf dem Karoplan befinden sich\n" +
                    "4 Ecken mit 13 Feldern, die durch ei-\n" +
                    "ne blaue Linie abgegrenzt sind.\n" +
                    "Diese 13 Felder werden zusammen\n" +
                    "der “kleine Hof” genannt.\n" +
                    "\n" +
                    "Beteiligen sich 4 Personen\n" +
                    "am Spiel, so besetzt jeder\n" +
                    "Spieler die 13 Felder eines sol-\n" +
                    "chen “kleinen Hofes” mit 13\n" +
                    "Kegeln derselben Farbe.\n" +
                    "\n" ,
                    "Spielen nur 2 Personen, so\n" +
                    "werden die beiden sich schräg\n" +
                    "gegenüberliegende “großen Hö-\n" +
                    "fe” bis zur schwarzen Linie\n" +
                    "mit 19 Kegeln besetzt.\n" +
                    "\n" +
                    "Die Aufstellung der Kegel auf dem\n" +
                    "sternförmigen Plan und die sich\n" +
                    "daraus ergebenden Regeln werden\n" +
                    "in einem eigenen Abschnitt (Stern-\n" +
                    "halma) beschrieben.\n" +
                    "\n" ,
                    "SPIELABLAUF:\n" +
                    "\n" +
                    "Jeder Spieler versucht, so schnell\n" +
                    "und geschickt wie möglich, seine\n" +
                    "Kegel in den gegenüberliegenden\n" +
                    "Hof zu bringen.\n" +
                    "\n" +
                    "Die einzelnen Spieler bewegen der\n" +
                    "Reihe nach abwechselnd einen ei-\n" +
                    "genen Kegel. Gespielt wird im\n" +
                    "Uhrzeigersinn.\n" +
                    "\n" +
                    "Mit einem Kegel kann man entwe-\n" +
                    "der ziehen oder springen.\n" +
                    "\n" ,
                    "ZIEHEN\n" +
                    "\n" +
                    "Alle Zugrichtungen sind erlaubt: ein\n" +
                    "Feld waagerecht, senkrecht, diago-\n" +
                    "nal, rückwärts oder seitwärts, je\n" +
                    "nach Belieben. Auf jedem Feld darf\n" +
                    "nur 1 Kegel stehen.\n" +
                    "\n" ,
                    "SPRINGEN\n" +
                    "\n" +
                    "Ein gegnerischer, aber auch ein ei-\n" +
                    "gener Kegel, der direkt vor oder ne-\n" +
                    "ben einem steht, kann überspungen\n" +
                    "werden, wenn unmittelbar dahinter\n" +
                    "ein freies Feld ist. Dabei kann in je-\n" +
                    "de Richtung gesprungen werden.\n" +
                    "\n" +
                    "Aber aufgepaßt: Obwohl die Kegel\n" +
                    "wie beim Damespiel übersprungen\n" +
                    "werden, werden sie nicht aus dem\n" +
                    "Spiel herausgenommen.\n" +
                    "\n" ,
                    "KETTENSPRÜNGE\n" +
                    "\n" +
                    "Kettensprünge sind aufeinan-\n" +
                    "derfolgende Sprünge. In einem\n" +
                    "Zug können dabei mehrere Kegel\n" +
                    "übersprungen werden. Hinter jedem\n" +
                    "Kegel muß sich dabei ein freies Feld\n" +
                    "befinden.\n" +
                    "\n" +
                    "Die Richtungen zwischen den ein-\n" +
                    "zelnen Sprüngen können beliebig\n" +
                    "gewechselt werden, auch Zick-\n" +
                    "zacksprünge sind erlaubt. Wie\n" +
                    "weit ein Spieler bei einem Ket-\n" +
                    "tensprung springt, bleibt ihm selber\n" +
                    "überlassen.\n" +
                    "\n" ,
                    "Jeder Spieler sollte versuchen, mit\n" +
                    "den eigenen Kegeln Ketten aufzu-\n" +
                    "bauen, damit die eigene Mann-\n" +
                    "schaft schneller vorankommt. Der\n" +
                    "andere profitiert davon natürlich auch.\n" +
                    "\n" ,
                    "TAKTISCHE SPIELTIPS\n" +
                    "\n" +
                    "Es liegt im Interesse eines jeden\n" +
                    "Spielers, in seinem Zug so viel wie\n" +
                    "möglich zu springen, um schnel-\n" +
                    "ler im gegenüberliegenden Hof an-\n" +
                    "zukommen. Er sollte darauf achten,\n" +
                    "seine Gegenspieler am Springen zu\n" +
                    "hindern. Seine Kegel stellt er dabei\n" +
                    "so auf, daß sich hinter ihnen kein\n" +
                    "freies Feld befindet.\n" +
                    "\n" ,
                    "Ein gegnerischer Kegel kann bis\n" +
                    "zuletzt in seinem Hof eingeschlos-\n" +
                    "sen werden. Ebenso kann ein Spie-\n" +
                    "ler im eigenen Hof einen Kegel\n" +
                    "zurücklassen, damit der Gegner den\n" +
                    "Hof nicht vollständig besetzen kann.\n" +
                    "Dennoch sollte jeder dafür sorgen,\n" +
                    "daß dieser letzte Kegel mit wenigen\n" +
                    "Zügen folgen kann, damit nicht der\n" +
                    "eigene Sieg vereitelt wird.\n" +
                    "\n" ,
                    "Beteiligen sich 4 Personen am\n" +
                    "Spiel, so kann jeder Spieler für sich\n" +
                    "versuchen, einen der Höfe zu er-\n" +
                    "obern. Eventuell empfiehlt es sich,\n" +
                    "wenn zwei sich schräg gegenüber-\n" +
                    "liegende Spieler zusammenspie-\n" +
                    "len. Sie versuchen, ihre Höfe so\n" +
                    "schnell wie möglich zu vertauschen,\n" +
                    "indem sie sich gegenseitig Ge-\n" +
                    "legenheit zum Springen geben und\n" +
                    "dabei die beiden anderen Spieler\n" +
                    "möglichst am Vorwärtskommen hin-\n" +
                    "dern.\n" +
                    "\n" ,
                    "SPIELENDE\n" +
                    "\n" +
                    "Spiel ist sofort beendet, wenn\n" +
                    "ein Spieler mit seinem letzten\n" +
                    "Kegel das letzte noch freie Zielfeld\n" +
                    "\n" +
                    "im gegenüberliegenden Hof besetzt\n" +
                    "hat.\n" +
                    "\n" ,
                    "STERNHALMA\n" +
                    "FÜR 2 BIS 3 PERSONEN\n" +
                    "\n" +
                    "eim Halma-Spiel für 3 Personen\n" +
                    "\n" +
                    "wird mit dem 6zackigen Stern auf\n" +
                    "der Rückseite des Spielplans ge-\n" +
                    "spielt. Jeder setzt 15 Kegel einer\n" +
                    "Farbe auf eine Sternspitze. Dabei\n" +
                    "muß zwischen den Startplätzen der\n" +
                    "Spieler jeweils eine Sternspitze un-\n" +
                    "besetzt bleiben. Die Spieler müssen\n" +
                    "nun versuchen, zuerst mit allen Ke-\n" +
                    "geln den gegenüberliegenden gleich-\n" +
                    "farbigen Zielbereich zu erreichen.\n" +
                    "\n" ,
                    "im Gegensatz zum Karoplan zieht\n" +
                    "\n" +
                    "man seine Kegel nicht auf Feldern\n" +
                    "sondern über Linien von Punkt\n" +
                    "zu Punkt. Fremde oder eigene\n" +
                    "Kegel kann man überspringen, wenn\n" +
                    "die durch eine Linie verbundenen\n" +
                    "Punkte direkt dahinter frei sind. Das\n" +
                    "Zielfeld ist hierbei anfangs - im\n" +
                    "Gegensatz zum Spiel auf dem\n" +
                    "Karoplan - unbesetzt. Ansonsten\n" +
                    "\n" +
                    "gelten hierfür die gleichen Regeln\n" +
                    "wie bei dem Spiel auf dem Karoplan.\n" +
                    "\n" +
                    "Mit diesem Plan können auch 2\n" +
                    "Personen spielen.\n" +
                    "\n" ,
                    "HALMA SOLO\n" +
                    "FÜR 1 SPIELER\n" +
                    "\n" +
                    "Halma kann man auch auf dem\n" +
                    "Karoplan alleine spielen.\n" +
                    "\n" +
                    "Mit 19 Kegeln besetzt man einen\n" +
                    "\n" +
                    "Hof und versucht, mit wenigen\n" +
                    "Zügen den diagonal gegenüberlie-\n" +
                    "genden Hof zu erreichen. Dabei\n" +
                    "zählt man mit, wie viele Züge bzw.\n" +
                    "Sprünge man braucht, um das\n" +
                    "Zielfeld mit allen Kegeln zu errei-\n" +
                    "chen. Man versucht, es in der näch-\n" +
                    "sten Partie besser zu machen und\n" +
                    "noch günstigere Möglichkeiten her-\n" +
                    "auszubekommen.\n" +
                    "\n" ,
                    "Diese Variante kann man natürlich\n" +
                    "auch mit 15 Kegeln auf dem\n" +
                    "Sternplan spielen.",
            "Alles verstanden? Dann tippe nochmal, \n um zurueck zu kehren :)"
    };
    public RulesScreen(Halma halma) {
        super(halma);
        create();
    }
    private void create(){
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index++;
                if(index == texts.length)
                    halma.setScreen(new Menu(halma));
                else
                    text.setText(texts[index]);
                //text position
            }
        });
        text = new Label(texts[index], skin);
        layout.add(text);
    }
}
