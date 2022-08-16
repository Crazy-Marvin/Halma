package app.halma.play;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.LinkedList;

public class Board {
    private LinkedList<Field> fields = new LinkedList<>();
    public static int width, height;
    private Stage stage;
    private LinkedList<LinkedList<Field>> colorFields;
    public static boolean isSquare;
    private FieldGroup fieldGroup;

    public LinkedList<Field> upper = new LinkedList();
    public LinkedList<Field> upperLeft = new LinkedList();
    public LinkedList<Field> upperRight = new LinkedList();
    public LinkedList<Field> lowerRight = new LinkedList();
    public LinkedList<Field> lowerLeft = new LinkedList();
    public LinkedList<Field> lower = new LinkedList();
    private BaseBackground baseColors;

    public Board(boolean isSquare, Stage stage){
        this.stage = stage;
        this.isSquare = isSquare;
    }
    public Board create(){
        if(isSquare)createSquare();
        else createStar();
        fieldGroup = new FieldGroup(stage.getWidth(), stage.getHeight(), stage);
        baseColors = new BaseBackground(this);
        for(Field f : fields) {
            fieldGroup.addActor(f);
            f.createNeighbours();
        }
        return this;
    }
    private void createSquare(){
        width = 32;
        height = 16;
        for(int x = 0; x<width; x+=2)
            for(float y = 0; y< height; y++)
                fields.add(new Field(x,y));
        for(Field f : fields){

            if(f.getPos().equals(new Vector2(8,2)))continue;
            if(f.getPos().equals(new Vector2(width -10,2)))continue;
            if(f.getPos().equals(new Vector2(8,3)))continue;
            if(f.getPos().equals(new Vector2(width -10,3)))continue;
            if(f.getPos().equals(new Vector2(8,4)))continue;
            if(f.getPos().equals(new Vector2(width -10,4)))continue;

            if(f.getPos().equals(new Vector2(4,4)))continue;
            if(f.getPos().equals(new Vector2(width-6,4)))continue;
            if(f.getPos().equals(new Vector2(6,4)))continue;
            if(f.getPos().equals(new Vector2(width-8,4)))continue;

            if(f.getPos().equals(new Vector2(6,3)))continue;
            if(f.getPos().equals(new Vector2(width-8,3)))continue;


            if(f.getPos().equals(new Vector2(8,height - 3)))continue;
            if(f.getPos().equals(new Vector2(width - 10,height - 3)))continue;
            if(f.getPos().equals(new Vector2(8,height - 4)))continue;
            if(f.getPos().equals(new Vector2(width - 10,height - 4)))continue;
            if(f.getPos().equals(new Vector2(8,height - 5)))continue;
            if(f.getPos().equals(new Vector2(width - 10,height - 5)))continue;

            if(f.getPos().equals(new Vector2(4,height - 5)))continue;
            if(f.getPos().equals(new Vector2(width - 6,height - 5)))continue;
            if(f.getPos().equals(new Vector2(6,height - 5)))continue;
            if(f.getPos().equals(new Vector2(width - 8,height - 5)))continue;
            if(f.getPos().equals(new Vector2(6,height - 4)))continue;
            if(f.getPos().equals(new Vector2(width - 8,height - 4)))continue;

            if(f.getPos().x < 9) {
                if (f.getPos().y < 5)
                    lowerLeft.add(f);
                if(f.getPos().y > height - 6)
                    upperLeft.add(f);
            }
            else if(f.getPos().x > width - 11){
                if (f.getPos().y < 5)
                    lowerRight.add(f);
                if(f.getPos().y > height - 6)
                    upperRight.add(f);
            }
        }

        if(Play.getInstance().getPlayers().size() == 2) {
            upperRight.clear();
            lowerLeft.clear();
        }

        for(Field f : upper)
            f.setColorChar(Field.LILA);
        for(Field f : upperLeft)
            f.setColorChar(Field.BLACK);
        for(Field f : upperRight)
            f.setColorChar(Field.BLUE);
        for(Field f : lowerRight)
            f.setColorChar(Field.YELLOW);
        for(Field f : lowerLeft)
            f.setColorChar(Field.GREEN);
        for(Field f : lower)
            f.setColorChar(Field.RED);
    }
    private void createStar(){

        width = 24;
        height = 16;

        //create lower part
        for(int i = 0; i < 4; i++){
            for(int j = 0; j<=i; j++){
                fields.add(new Field(
                        width/2 - i + 2*j,
                        i
                ));
            }
        }
        //create middle part
        for(int y = 0; y<5; y++){
            for(int x = 0; x <= width/2 - y; x++){
                fields.add(new Field(
                        2*x + y,
                        y+4
                ));
            }
        }
        for(int y = 0; y<4; y++){
            for(int x = 0; x <= width/2 - y; x++){
                fields.add(new Field(
                        2*x + y,
                        12-y
                ));
            }
        }
        //create upper part
        for(int y = 0; y< 4; y++){
            for(int x = 0; x<=y; x++){
                fields.add(new Field(
                        width/2 - y + 2*x,
                        height - y
                ));
            }
        }
        createColorFieldsStar();
    }
    private void createColorFieldsStar(){
        for(Field f : fields) {

            if(f.getPos().equals(new Vector2(width/2+5, height/2 + 1)))continue;
            if(f.getPos().equals(new Vector2(width/2+5, height/2 + 3)))continue;
            if(f.getPos().equals(new Vector2(width/2+6, height/2 + 2)))continue;
            if(f.getPos().equals(new Vector2(width/2+7, height/2 + 1)))continue;

            if(f.getPos().equals(new Vector2(width/2-5, height/2 + 1)))continue;
            if(f.getPos().equals(new Vector2(width/2-5, height/2 + 3)))continue;
            if(f.getPos().equals(new Vector2(width/2-6, height/2 + 2)))continue;
            if(f.getPos().equals(new Vector2(width/2-7, height/2 + 1)))continue;

            if(f.getPos().equals(new Vector2(width/2-5, height/2 - 1)))continue;
            if(f.getPos().equals(new Vector2(width/2-5, height/2 - 3)))continue;
            if(f.getPos().equals(new Vector2(width/2-6, height/2 - 2)))continue;
            if(f.getPos().equals(new Vector2(width/2-7, height/2 - 1)))continue;

            if(f.getPos().equals(new Vector2(width/2+5, height/2 - 1)))continue;
            if(f.getPos().equals(new Vector2(width/2+5, height/2 - 3)))continue;
            if(f.getPos().equals(new Vector2(width/2+6, height/2 - 2)))continue;
            if(f.getPos().equals(new Vector2(width/2+7, height/2 - 1)))continue;

            if (f.getPos().y > height - 4f)
                upper.add(f);
            if(f.getPos().y > height/2){
                if(f.getPos().x > width/2 + 4)
                    upperRight.add(f);
                if(f.getPos().x < width/2 - 4)
                    upperLeft.add(f);
            }
            if(f.getPos().y < height/2){
                if(f.getPos().x > width/2 + 4)
                    lowerRight.add(f);
                if(f.getPos().x < width/2 - 4)
                    lowerLeft.add(f);
            }
            if (f.getPos().y < 4)
                lower.add(f);
        }
        if(Play.getInstance().getPlayers().size() == 2) {
            upperLeft.clear();
            upperRight.clear();
            lowerLeft.clear();
            lowerRight.clear();
        }
        if(Play.getInstance().getPlayers().size() == 3 ){
            upper.clear();
            lowerLeft.clear();
            lowerRight.clear();
        }
        if(Play.getInstance().getPlayers().size() == 4){
            upper.clear();
            lower.clear();
        }
        if(Play.getInstance().getPlayers().size() == 5)
            upper.clear();
        for(Field f : upper)
            f.setColorChar(Field.LILA);
        for(Field f : upperLeft)
            f.setColorChar(Field.BLACK);
        for(Field f : upperRight)
            f.setColorChar(Field.BLUE);
        for(Field f : lowerRight)
            f.setColorChar(Field.YELLOW);
        for(Field f : lowerLeft)
            f.setColorChar(Field.GREEN);
        for(Field f : lower)
            f.setColorChar(Field.RED);
    }
    public Field getFieldFromPos(Vector2 pos){
        for (Field f: fields){
            if(f.getPos().x == pos.x)
                if(f.getPos().y == pos.y)
                    return f;
        }
        return null;
    }

    //getter and setter
    public LinkedList<Field> getFields() {return fields;}
    public void setFields(LinkedList<Field> fields) {this.fields = fields;}
    public LinkedList<LinkedList<Field>> getColorFields() {return colorFields;}
    public void setColorFields(LinkedList<LinkedList<Field>> colorFields) {this.colorFields = colorFields;}
    public boolean isSquare() {return isSquare;}
    public void setSquare(boolean square) {isSquare = square;}
    public FieldGroup getFieldGroup() {return fieldGroup;}
}