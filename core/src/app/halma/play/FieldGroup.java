package app.halma.play;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FieldGroup extends Group {

    public FieldGroup(float width, float height, Stage stage) {
        layoutChanged(width, height);
        stage.addActor(this);
    }

    public void layoutChanged(float width, float height) {
        float boardHeight = 0;
        float boardWidth = 0;
        if (!Board.isSquare) {
            boardHeight = Field.size * Board.height * 2 + 1;
            boardWidth = Field.size * Board.width +1;
        }
        if(Board.isSquare){
            boardHeight = Field.size * Board.width;
            boardWidth = boardHeight;
        }
        System.out.println(boardHeight);
        System.out.println(boardWidth);
        System.out.println(Board.isSquare);
        //du weißt die genaue größe des boards
        //und du weißt die genaue größe des screens
        super.setBounds(width/2 - boardWidth/2, height/2 - boardHeight/2, width,height);
    }

}
