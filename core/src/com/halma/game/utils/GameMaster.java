package com.halma.game.utils;

import com.badlogic.gdx.utils.Array;
import com.halma.game.Handler;

public class GameMaster {

    public static Handler handler;
    public static Array<String> players;

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
        else if (px-x == -1 && py-y == -1) return true;
        else return false;
    }

    public static boolean isAdjacentReal(int x, int y) {
        return false;
    }

    public static boolean isPieceAdjacent() {
        return false;
    }


}
