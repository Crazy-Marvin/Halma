package app.halma.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.LinkedList;

public class Field extends Image {
    //variables
    public final static char BLACK = 'l', LILA = 'h', GREEN = 'g', RED = 'r', BLUE = 'b', YELLOW = 'w', NONE = 'N', POSSIBLE = 'p';
    private Vector2 pos, posTemp;
    private char colorChar = NONE, colorCharTemp = NONE;
    public static float size=Gdx.graphics.getHeight()/35;
    private Listener listener = new Listener();
    private Field up, down, left, right, upRight, upLeft, downLeft, downRight;
    private LinkedList<Field> neighbours = new LinkedList<>();
    private static int jumpCount = 0;
    //constructor
    public Field(Vector2 pos) {
        super(new Texture("Field.png"));
        this.pos = pos;
        posTemp = pos;
        layoutChanged();
        colorChanged();
        this.addListener(listener);
        this.scaleBy(0.5f);
        debug();
    }
    public Field(float x, float y) {
        this(new Vector2(x,y));
    }
    public void layoutChanged(){
        size = Gdx.graphics.getHeight()/35;
        this.setBounds(pos.x * size, pos.y * size * 2, size, size);
    }
    public void colorChanged(){
        switch(colorChar){
            case NONE: setColor(Color.WHITE); break;
            case BLACK: setColor(Color.BLACK); break;
            case BLUE: setColor(Color.BLUE); break;
            case GREEN: setColor(Color.GREEN); break;
            case LILA: setColor(Color.PURPLE); break;
            case RED: setColor(Color.RED); break;
            case YELLOW: setColor(Color.YELLOW); break;
            case POSSIBLE: setColor(new Color(1,1,1, 0.25f)); break;
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(!posTemp.equals(pos))
            layoutChanged();
        if(colorCharTemp != colorChar)
            colorChanged();
        colorCharTemp = colorChar;
        posTemp = pos;
    }
    public void clicked(){
        if(besetzt()) {
            Play.getInstance().setSelectedField(this);
            clearPossibleFields();
            showAllWays();
        }
        if(colorChar == POSSIBLE) {
            Play.getInstance().getPlayers().get(Play.getInstance().getCurrentPlayerIndex()).makeMove(this);
            clearPossibleFields();
        }
        if(colorChar == NONE)
            clearPossibleFields();
        //show all possible ways

        //if this is already a possible way, make a move
    }
    public void showAllWays() {
        if(jumpCount>10_000) return;
        for(Field neighbour : neighbours) {
            if(neighbour == null) continue; //ist das überhaupt ein nachbar?
            if(!neighbour.besetzt()) //kann ich dorthin?
                neighbour.colorChar = POSSIBLE;
            if(neighbour.besetzt()) //oder kann ich springen?
                jump(neighbour);
        }
        System.out.println(jumpCount);
        jumpCount = 0;
    }
    public void jump(Field neighbour) {
        if(jumpCount>10_000) return;
        jumpCount++;
        LinkedList<Field> nextNeighbours = neighbour.neighbours; //die nachbarn von dem Feld, das besetzt ist
        Field toJump = nextNeighbours.get(neighbours.indexOf(neighbour)); //das feld auf das ich springe
        if(toJump == null) return;
        if(!toJump.besetzt()) { //einmal checken, ob das feld frei ist
            toJump.setColorChar(POSSIBLE); // dann sagen, dass ich dorthin kann
            toJump.afterJump(neighbour);
        }
    }
    public void afterJump(Field fromField) {
        if(jumpCount>10_000) return;
        for(Field field: neighbours)
            if(field != null) //ist das überhaupt ein nachbar?
                if(field.besetzt()) //kann ich dort drüber springen?
                    if(field != fromField) //Springe ich eh nicht zurück?
                        jump(field);
    }
    public static void clearPossibleFields(){
        for(Field field : Play.getInstance().getBoard().getFields())
            if(field.colorChar == POSSIBLE)
                field.colorChar = NONE;
    }
    public void createNeighbours() {

        if(!Play.getInstance().getBoard().isSquare()){
            upLeft = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(-1, 1));
            upRight = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(1, 1));
            downLeft = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(-1, -1));
            downRight = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(1, -1));
            left = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(-2, 0));
            right = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(2, 0));
        }
        else{
            up = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(0, 1));
            down = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(0, -1));
            downLeft = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(-2, -1));
            downRight = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(2, -1));
            upLeft = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(-2, 1));
            upRight = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(2, 1));
            right = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(2, 0));
            left = Play.getInstance().getBoard().getFieldFromPos(new Vector2(pos).add(-2, 0));
        }
        neighbours.add(left);
        neighbours.add(right);
        neighbours.add(up);
        neighbours.add(down);
        neighbours.add(upLeft);
        neighbours.add(upRight);
        neighbours.add(downLeft);
        neighbours.add(downRight);
        //create the neighbours of an field
    }
    public boolean besetzt(){
        return colorChar != NONE && colorChar != POSSIBLE;
    }
    //getter and setter
    public Vector2 getPos() {return pos;}
    public void setPos(Vector2 pos) {this.pos = pos;}
    public char getColorChar() {return colorChar;}
    public void setColorChar(char colorChar) {this.colorChar = colorChar;}
    //inner class
    private class Listener extends ClickListener{
        public void clicked(InputEvent event, float x, float y) {
            if(event.getListenerActor() instanceof Field)
                ((Field) event.getListenerActor()).clicked();
        }
    }
}