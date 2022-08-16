package app.halma.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.LinkedList;

public class BaseBackground {

    private Board board;
    private LinkedList<Image> images = new LinkedList();
    public BaseBackground(Board board){
        this.board = board;
        create();
    }

    private void create() {
        Image upper, upperLeft, upperRight, lowerLeft, lowerRight, lower;
        LinkedList<LinkedList<Field>> groups;

        upper = new Image(new TextureRegionDrawable(new Texture("triangle-p.png")));
        upperRight = new Image(new TextureRegionDrawable(new Texture("triangle-p.png")));
        upperLeft = new Image(new TextureRegionDrawable(new Texture("triangle-p.png")));
        lowerLeft = new Image(new TextureRegionDrawable(new Texture("triangle-p.png")));
        lowerRight = new Image(new TextureRegionDrawable(new Texture("triangle-p.png")));
        lower = new Image(new TextureRegionDrawable(new Texture("triangle-p.png")));

        createTriangle(board.upper, upper, Color.PURPLE, false);
        createTriangle(board.upperRight, upperRight, Color.BLUE, true);
        createTriangle(board.upperLeft, upperLeft, Color.BLACK, true);
        createTriangle(board.lowerLeft, lowerLeft, Color.GREEN, false);
        createTriangle(board.lowerRight, lowerRight, Color.YELLOW, false);
        createTriangle(board.lower, lower, Color.RED, true);

    }

    private void createTriangle(LinkedList<Field> fields, Image image, Color color, boolean flip) {
        boolean first = true;

        Data data = null;
        for(Field field : fields){
            if(first){
                data = new Data(field, field, field, field);
                first = false;
            }
            data.lowestX = field.getPos().x < data.lowestX.getPos().x ? field : data.lowestX;
            data.lowestY = field.getPos().y < data.lowestY.getPos().y ? field : data.lowestY;

            data.highestX = field.getPos().x > data.highestX.getPos().x ? field : data.highestX;
            data.highestY = field.getPos().y > data.highestY.getPos().y ? field : data.highestY;

        }

        if(data == null)
            return;

        image.setPosition(data.lowestX.getX() + data.lowestX.getWidth()/2,
            data.lowestY.getY() + data.lowestY.getHeight()/2);
        image.setSize(data.highestX.getX() - data.lowestX.getX(), data.highestY.getY() - data.lowestY.getY());
        image.setColor(color);
        image.getColor().a = 0.2f;
        image.setOrigin(image.getWidth()/2, image.getHeight()/2);
        if(flip)
            image.rotateBy(180);

        images.add(image);

        board.getFieldGroup().addActor(image);
    }

    public void layoutChanged() {
        for(Image image : images)
            image.setDrawable(null);
        create();
    }

    private static class Data {
        Field highestY, highestX, lowestY, lowestX;

        public Data(Field highestY, Field highestX, Field lowestY, Field lowestX) {
            this.highestY = highestY;
            this.highestX = highestX;
            this.lowestY = lowestY;
            this.lowestX = lowestX;
        }
    }

}
