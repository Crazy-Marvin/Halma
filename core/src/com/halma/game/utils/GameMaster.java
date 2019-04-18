package com.halma.game.utils;

import com.badlogic.gdx.utils.Array;
import com.halma.game.Handler;
import com.halma.game.gameobjects.Board;
import com.halma.game.gameobjects.BoardPiece;
import com.halma.game.gameobjects.HintSpace;

public class GameMaster {

    public static Handler handler;
    private static Array<String> players;
    private static boolean selected = false;

    public static boolean isSpaceFree(int x, int y) {
        if (handler.getGameState().getBoard().getBoard()[y][x].isReal()) {
            return true;
        }
        return false;
    }

    public static boolean isAdjacent(int x, int y, int px, int py) {
        if (px-x == 1 && py-y == 1) return true; //top left
        else if (px == x && py-y == 1) return true; // top right
        else if (px-x == 1 && py == y) return true; // left
        else if (px-x == -1 && py == y) return true; // right
        else if (px == x && py-y == -1) return true; // bottom right
        else if (px-x == 1 && py-y == -1) return true; // bottom left
        else if (py%2 == 1 && px-x == -1 && py-y == 1) return true; // when py%2==1, top right
        else if (py%2 == 1 && px-x == -1 && py-y == -1) return true; // when py%2==1, bottom right
        else return false;
    }

    public static boolean isAdjacentReal(int x, int y, int px, int py) { // checks if space is open and exist
        if (isAdjacent(x, y, px, py)) {
            if (y >= 0 && y < handler.getGameState().getBoard().getBoard().length
                    && x >= 0 && x < handler.getGameState().getBoard().getBoard()[0].length
                    && handler.getGameState().getBoard().getBoard()[y][x].isReal()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPieceAdjacent(int x, int y, int px, int py) {
        if (isAdjacent(x, y, px, py)) {
            boolean check = false;
            Board b = handler.getGameState().getBoard();

            // checks pieces5
            if (b.getRedPieces() != null) {
                for (int i = 0; i < b.getRedPieces().length; i++) {
                    if (b.getRedPieces()[i].getX() == x && b.getRedPieces()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            // checks pieces3
            if (b.getPieces3() != null) {
                for (int i = 0; i < b.getPieces3().length; i++) {
                    if (b.getPieces3()[i].getX() == x && b.getPieces3()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            // checks pieces4
            if (b.getPieces4() != null) {
                for (int i = 0; i < b.getPieces4().length; i++) {
                    if (b.getPieces4()[i].getX() == x && b.getPieces4()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            // checks pieces2
            if (!check && b.getGreenPieces() != null) {
                for (int i = 0; i < b.getGreenPieces().length; i++) {
                    if (b.getGreenPieces()[i].getX() == x && b.getGreenPieces()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            // checks pieces6
            if (b.getPieces6() != null) {
                for (int i = 0; i < b.getPieces6().length; i++) {
                    if (b.getPieces6()[i].getX() == x && b.getPieces6()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            // checks pieces7
            if (b.getPieces7() != null) {
                for (int i = 0; i < b.getPieces7().length; i++) {
                    if (b.getPieces7()[i].getX() == x && b.getPieces7()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            return check;
        }
        return false;
    }

    public static boolean isHintSpaceReal(int x, int y, Array<HintSpace> hintSpaces) {
        for (int i = 0; i < hintSpaces.size; i++) {
            if (x == hintSpaces.get(i).getX() && y == hintSpaces.get(i).getY()) return true;
        }
        return false;
    }

    public static boolean isAreaInEndSpace(int x, int y, boolean inEndSpace) {
        if (inEndSpace) {
            if (handler.getGameState().getBoard().getBoard()[y][x].getType() != 1) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean isDownTriangleFull(int x, int y) {
        Board b = handler.getGameState().getBoard();
        if (!b.getBoard()[y][x].isReal() && !b.getBoard()[y+1][x-1].isReal() &&
            !b.getBoard()[y+1][x].isReal() && !b.getBoard()[y+2][x-1].isReal() &&
            !b.getBoard()[y+2][x].isReal() && !b.getBoard()[y+2][x+1].isReal() &&
            !b.getBoard()[y+3][x-2].isReal() && !b.getBoard()[y+3][x-1].isReal() &&
            !b.getBoard()[y+3][x].isReal() && !b.getBoard()[y+3][x+1].isReal() ) {

            return true;
        }
        return false;
    }

    public static boolean isUpTriangleFull(int x, int y) {
        Board b = handler.getGameState().getBoard();
        if (!b.getBoard()[y][x].isReal() && !b.getBoard()[y-1][x-1].isReal() &&
                !b.getBoard()[y-1][x].isReal() && !b.getBoard()[y-2][x-1].isReal() &&
                !b.getBoard()[y-2][x].isReal() && !b.getBoard()[y-2][x+1].isReal() &&
                !b.getBoard()[y-3][x-2].isReal() && !b.getBoard()[y-3][x-1].isReal() &&
                !b.getBoard()[y-3][x].isReal() && !b.getBoard()[y-3][x+1].isReal() ) {

            return true; // here add more code for testing whether piece won or not.
        }
        return false;
    }

    //Getters and Setters
    public static boolean isSelected() {return selected;}
    public static Array<String> getPlayers() {return players;}

    public static void setSelected(boolean selected) {GameMaster.selected = selected;}

}
