package com.halma.game.utils;

import com.badlogic.gdx.utils.Array;
import com.halma.game.Handler;
import com.halma.game.gameobjects.Board;

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
        if (px-x == 1 && py-y == 1) return true;
        else if (px == x && py-y == 1) return true;
        else if (px-x == 1 && py == y) return true;
        else if (px-x == -1 && py == y) return true;
        else if (px == x && py-y == -1) return true;
        else if (px-x == 1 && py-y == -1) return true;
        else if (py%2 == 1 && px-x == -1 && py-y == 1) return true;
        else if (py%2 == 1 && px-x == -1 && py-y == -1) return true;
        else return false;
    }

    public static boolean isAdjacentReal(int x, int y, int px, int py) {
        if (isAdjacent(x, y, px, py)) {
            if (handler.getGameState().getBoard().getBoard()[y][x].isReal()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPieceAdjacent(int x, int y, int px, int py) {
        if (isAdjacent(x, y, px, py)) {
            boolean check = false;
            Board b = handler.getGameState().getBoard();

            if (b.getRedPieces() != null) {
                for (int i = 0; i < b.getRedPieces().length; i++) {
                    if (b.getRedPieces()[i].getX() == x && b.getRedPieces()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }

            if (!check && b.getGreenPieces() != null) {
                for (int i = 0; i < b.getGreenPieces().length; i++) {
                    if (b.getGreenPieces()[i].getX() == x && b.getGreenPieces()[i].getY() == y) {
                        check = true;
                        break;
                    }
                }
            }
            return check;
        }
        return false;
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

            return true;
        }
        return false;
    }

    //Getters and Setters
    public static boolean isSelected() {return selected;}
    public static Array<String> getPlayers() {return players;}

    public static void setSelected(boolean selected) {GameMaster.selected = selected;}

}
