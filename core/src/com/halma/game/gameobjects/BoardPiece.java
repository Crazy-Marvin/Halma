package com.halma.game.gameobjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;
import com.halma.game.utils.Controls;
import com.halma.game.utils.GameMaster;

public class BoardPiece extends BoardSpace {

    static int c = 0;

    enum BoardPiece_State {
        NOT_SELECTED,
        SELECTED
    }

    private Board board;
    private BoardPiece_State state;
    private Texture boardPieceImg;
    private Array<HintSpace> hintSpaces;
    private boolean inEndSpace;

    // Constructor
    public BoardPiece(Handler handler, int x, int y, int type) {
        super(handler, x, y, true);
        this.type = type;
        init(type);
    }

    // Main Method
    private void init(int type) {
        board = handler.getGameState().getBoard();
        board.getBoard()[y][x].setReal(false);
        if (state == null) {state = BoardPiece_State.NOT_SELECTED;}
        switch (type) {
            case 2: boardPieceImg = Assets.boardPiece_Red; break;
            case 3: boardPieceImg = Assets.boardPiece_Blue; break;
            case 4: boardPieceImg = Assets.boardPiece_Purple; break;
            case 5: boardPieceImg = Assets.boardPiece_Green; break;
            case 6: boardPieceImg = Assets.boardPiece_Yellow; break;
            case 7: boardPieceImg = Assets.boardPiece_SkyBlue; break;
            default: type = -1;
        }
        updateDrawCoord();
        circle = new Circle(drawX + boardPieceImg.getWidth() * 0.75f, drawY + boardPieceImg.getWidth() * 0.75f, boardPieceImg.getWidth()*0.75f);
        hintSpaces = new Array<HintSpace>();
    }

    @Override
    public void update(float dt) {
        select();
        move();
        inEndSpace = handleIfInEndSpace(board.getBoard()[y][x].type, type);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (state == BoardPiece_State.SELECTED) {
            batch.setColor(1, 1, 1, 0.5f);
        }
        batch.draw(boardPieceImg, drawX, drawY, boardPieceImg.getWidth()*1.5f, boardPieceImg.getHeight()*1.5f);
        batch.setColor(1, 1,1 ,1);
        if (hintSpaces != null) {
            for (HintSpace h : hintSpaces) h.render(batch);
        }
    }

    @Override
    public void dispose() {

    }

    // Other Methods
    public void select() {
        // Deselect the boardpiece
        if (state == BoardPiece_State.SELECTED) {
            if (Gdx.input.justTouched() && circle.contains(Controls.x, Controls.y) && GameMaster.isSelected()) {
                state = BoardPiece_State.NOT_SELECTED;
                hintSpaces = new Array<HintSpace>();
                GameMaster.setSelected(false);
            }
        }
        // Select the boardpiece
        else if (state == BoardPiece_State.NOT_SELECTED) {
            if (Gdx.input.justTouched() && circle.contains(Controls.x, Controls.y) && !GameMaster.isSelected()) {
                state = BoardPiece_State.SELECTED;
                createMovableAreas();
                GameMaster.setSelected(true);
            }
        }

    }

    public void move() {
        if (state == BoardPiece_State.SELECTED && Gdx.input.justTouched()) {

            for (int j = 0; j < board.getBoard().length; j++) {
                for (int i = 0; i < board.getBoard()[j].length; i++) {

                    if (board.getBoard()[j][i].isReal() && board.getBoard()[j][i].getCircle().contains(Controls.x, Controls.y)) {

                        boolean check = false;
                        for (int k = 0; k < hintSpaces.size; k++) {
                            if (hintSpaces.get(k).circle.contains(Controls.x, Controls.y)) {
                                check = true;
                                break;
                            }
                        }
                        if (!check) return;

                        board.getBoard()[y][x].setReal(true);
                        x = board.getBoard()[j][i].x;
                        y = board.getBoard()[j][i].y;
                        updateDrawCoord();
                        updateCircleCoord();
                        state = BoardPiece_State.NOT_SELECTED;
                        board.getBoard()[j][i].setReal(false);
                        GameMaster.setSelected(false);
                        hintSpaces = new Array<HintSpace>();
                    }

                }
            }

        }
    }

    public void createMovableAreas() {
        createAdjacentMovableAreas();
        createOverPieceMovablePositions(x, y, x, y);
    }

    private void createAdjacentMovableAreas() {
        //check under piece
        if (y-1 > 0) {
            int xx = x;
            if (y%2 == 1) xx++;
            //check bottom left
            if (x > 0 && GameMaster.isAdjacentReal(xx-1, y-1, xx, y) && GameMaster.isAreaInEndSpace(xx-1, y-1, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx-1, y-1, this));
            }
            //check bottom right
            if (GameMaster.isAdjacentReal(xx, y-1, xx, y) && GameMaster.isAreaInEndSpace(xx, y-1, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx, y-1, this));
            }
        }

        // check across the piece
        if (x > 0 && GameMaster.isAdjacentReal(x-1, y, x, y) && GameMaster.isAreaInEndSpace(x-1, y-1, inEndSpace)) {
            hintSpaces.add(new HintSpace(handler, x-1, y, this));
        }
        if (x < board.getBoard()[0].length-1 && GameMaster.isAdjacentReal(x+1, y, x, y) && GameMaster.isAreaInEndSpace(x+1, y-1, inEndSpace)) {
            hintSpaces.add(new HintSpace(handler, x+1, y, this));
        }

        //check above piece
        if (y < board.getBoard().length-1) {
            int xx = x;
            if (y%2 == 1) xx++;
            if (x > 0 && GameMaster.isAdjacentReal(xx-1, y+1, xx, y)&& GameMaster.isAreaInEndSpace(xx-1, y+1, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx-1, y+1, this));
            }
            if (GameMaster.isAdjacentReal(xx, y+1, xx, y)&& GameMaster.isAreaInEndSpace(xx, y+1, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx, y+1, this));
            }
        }
    }

    private void createOverPieceMovablePositions(int x, int y, int px, int py) {
        // check under piece
        if (y-1 > 0) {
            int xx = x;
            if (y%2 == 1) xx++;
            //check bottom left
            if (y%2 == 0 && x > 0 && GameMaster.isPieceAdjacent(xx-1, y-1, xx, y)
                    && GameMaster.isAdjacentReal(xx-1, y-2, xx-1, y-1)
                    && !GameMaster.isHintSpaceReal(xx-1, y-2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx-1, y-2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx-1, y-2, this));
                createOverPieceMovablePositions(xx-1, y-2, xx, y);
            }
            else if (y%2 == 1 && x > 0 && GameMaster.isPieceAdjacent(xx-1, y-1, xx, y)
                    && GameMaster.isAdjacentReal(xx-2, y-2, xx-1, y-1)
                    && !GameMaster.isHintSpaceReal(xx-2, y-2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx-2, y-2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx-2, y-2, this));
                createOverPieceMovablePositions(xx-2, y-2, xx, y);
            }
            //check bottom right

            if (y%2 == 0 && x+1 < board.getBoard()[0].length && GameMaster.isPieceAdjacent(xx, y-1, xx, y)
                    && GameMaster.isAdjacentReal(xx+1, y-2, xx, y-1)
                    && !GameMaster.isHintSpaceReal(xx+1, y-2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx+1, y-2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx+1, y-2, this));
                createOverPieceMovablePositions(xx+1, y-2, xx, y);
                System.out.println("Added this 0");
            }
            else if (y%2 == 1 && x+1 < board.getBoard()[0].length && GameMaster.isPieceAdjacent(xx, y-1, xx-1, y)
                    && GameMaster.isAdjacentReal(xx, y-2, xx, y-1)
                    && !GameMaster.isHintSpaceReal(xx, y-2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx, y-2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx, y-2, this));
                createOverPieceMovablePositions(xx, y-2, xx-1, y);
                System.out.println("Added this 1");
            }//*/
        }

        //check across the piece
        if (x > 0 && GameMaster.isPieceAdjacent(x-1, y, x, y) && GameMaster.isAdjacentReal(x-2, y, x-1, y)
                && !GameMaster.isHintSpaceReal(x-2, y, hintSpaces)
                && GameMaster.isAreaInEndSpace(x-2, y, inEndSpace)) {
            hintSpaces.add(new HintSpace(handler, x-2, y, this));
            createOverPieceMovablePositions(x-2, y, x, y);
        }
        if (x < board.getBoard()[0].length && GameMaster.isPieceAdjacent(x+1, y, x, y)
                && GameMaster.isAdjacentReal(x+2, y, x+1, y)
                && !GameMaster.isHintSpaceReal(x+2, y, hintSpaces)
                && GameMaster.isAreaInEndSpace(x+2, y, inEndSpace)) {
            hintSpaces.add(new HintSpace(handler, x+2, y, this));
            createOverPieceMovablePositions(x+2, y, x, y);
        }

        //check above the piece
        if (y < board.getBoard().length-1) {
            int xx = x;
            if (y%2 == 1) xx++;
            //check above left
            if (y%2 == 0 && x > 0 && GameMaster.isPieceAdjacent(xx-1, y+1, xx, y)
                    && GameMaster.isAdjacentReal(xx-1, y+2, xx-1, y+1)
                    && !GameMaster.isHintSpaceReal(xx-1, y+2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx-1, y+2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx-1, y+2, this));
                createOverPieceMovablePositions(xx-1, y+2, xx, y);
            }
            else if (y%2 == 1 && x > 0 && GameMaster.isPieceAdjacent(xx-1, y+1, xx, y)
                    && GameMaster.isAdjacentReal(xx-2, y+2, xx-1, y+1)
                    && !GameMaster.isHintSpaceReal(xx-2, y+2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx-2, y+2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx-2, y+2, this));
                createOverPieceMovablePositions(xx-2, y+2, xx, y);
            }
            //check above right
            if (y%2 == 0 && x < board.getBoard()[0].length && GameMaster.isPieceAdjacent(xx, y+1, xx, y)
                    && GameMaster.isAdjacentReal(xx+1, y+2, xx, y+1)
                    && !GameMaster.isHintSpaceReal(xx+1, y+2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx+1, y+2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx+1, y+2, this));
                createOverPieceMovablePositions(xx+1, y+2, xx, y);
            }
            else if (y%2 == 1 && x < board.getBoard()[0].length && GameMaster.isPieceAdjacent(xx, y+1, xx-1, y)
                    && GameMaster.isAdjacentReal(xx, y+2, xx, y+1)
                    && !GameMaster.isHintSpaceReal(xx, y+2, hintSpaces)
                    && GameMaster.isAreaInEndSpace(xx, y+2, inEndSpace)) {
                hintSpaces.add(new HintSpace(handler, xx, y+2, this));
                createOverPieceMovablePositions(xx, y+2, xx, y);
            }
        }
    }

    private boolean checkIfInEndSpace() {
        if (type == board.getBoard()[y][x].type) return true;
        return false;
    }

    private boolean handleIfInEndSpace(int bsType, int bpType) {
        if (bpType == 2 && bsType == 5) {
            return true;
        }
        else if (bpType == 3 && bsType == 6) {
            return true;
        }
        else if (bpType == 4 && bsType == 7) {
            return true;
        }
        else if (bpType == 5 && bsType == 2) {
            return true;
        }
        else if (bpType == 6 && bsType == 3) {
            return true;
        }
        else if (bpType == 7 && bsType == 4) {
            return true;
        }
        return false;
    }
    // Getters and Setters
    public boolean isInEndSpace() {return inEndSpace;}

    public void setInEndSpace(boolean inEndSpace) {this.inEndSpace = inEndSpace;}

}
