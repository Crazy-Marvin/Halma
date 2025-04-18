package app.halma.play;

import java.util.LinkedList;

public class WinnerChecker {
    public static int players = 2;
    public static boolean upperWin, lowerWin, upperLeftWin, upperRightWin, lowerLeftWin, lowerRightWin;

    public static boolean check(Board board) {
        // For both board types, our win conditions are:
        // 1. If any bot region contains all red field, the human wins.
        // 2. If the human's region (board.lower) has no red fields, the computer wins.

        // Reset win flags
        upperWin = lowerWin = upperLeftWin = upperRightWin = lowerLeftWin = lowerRightWin = false;

        // Check bot regions for red invasion
        if (containedBy(board.upper, Field.RED)) {
            upperWin = true;
            return true;
        }
        if (containedBy(board.upperLeft, Field.RED)) {
            upperLeftWin = true;
            return true;
        }
        if (containedBy(board.upperRight, Field.RED)) {
            upperRightWin = true;
            return true;
        }
        if (containedBy(board.lowerLeft, Field.RED)) {
            lowerLeftWin = true;
            return true;
        }
        if (containedBy(board.lowerRight, Field.RED)) {
            lowerRightWin = true;
            return true;
        }

        // Check if player's designated region (board.lower) is completely invaded (no red)
        if (isFull(board.lower) && !containsColor(board.lower, Field.RED)) {
            lowerWin = true;
            return true;
        }
        return false;
    }

    // Helper method: returns true if all fields in the given region has the target color.
    private static boolean containedBy(LinkedList<Field> region, char targetColor) {
        for (Field f : region) {
            if (f.getColorChar() != targetColor) {
                return false;
            }
        }
        return !region.isEmpty() && isFull(region);
    }

    // Helper method: returns true if any field in the given region has the target color.
    private static boolean containsColor(Iterable<Field> region, int targetColor) {
        for (Field f : region) {
            if (f.getColorChar() == targetColor)
                return true;
        }
        return false;
    }


    // Helper method: returns true if any field in the given region has the target color.
    private static boolean isFull(Iterable<Field> region) {
        for (Field f : region) {
            if (f.getColorChar() == Field.NONE)
                return false;
        }
        return true;
    }

    // (If needed, you can update squareCheck similarly; in this version the same logic applies.)
}
