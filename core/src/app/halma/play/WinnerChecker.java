package app.halma.play;

public class WinnerChecker {
    public static int players = 2;
    public static boolean upperWin, lowerWin, upperLeftWin, upperRightWin, lowerLeftWin, lowerRightWin;

    public static boolean check(Board board) {
        if (board.isSquare()) return squareCheck(board);
        boolean winner = true;
        if (players == 2) {
            for (Field f : board.lower)
                if (f.getColorChar() != Field.LILA) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upper)
                if (f.getColorChar() != Field.RED) {
                    winner = false;
                    break;
                }

            return winner;
        }
        if (players == 3) {
            for (Field f : board.lowerLeft)
                if (f.getColorChar() != Field.BLUE) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerRight)
                if (f.getColorChar() != Field.BLACK) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upper)
                if (f.getColorChar() != Field.RED) {
                    winner = false;
                    break;
                }

            return winner;
        }
        if (players == 4) {
            for (Field f : board.lowerLeft)
                if (f.getColorChar() != Field.BLUE) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerRight)
                if (f.getColorChar() != Field.BLACK) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperLeft)
                if (f.getColorChar() != Field.YELLOW) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperRight)
                if (f.getColorChar() != Field.GREEN) {
                    winner = false;
                    break;
                }

            return winner;
        }
        if (players == 5) {
            for (Field f : board.lowerLeft)
                if (f.getColorChar() != Field.BLUE) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerRight)
                if (f.getColorChar() != Field.BLACK) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperLeft)
                if (f.getColorChar() != Field.YELLOW) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperRight)
                if (f.getColorChar() != Field.GREEN) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upper)
                if (f.getColorChar() != Field.RED) {
                    winner = false;
                    break;
                }

            return winner;
        }
        if (players == 6) {
            for (Field f : board.lowerLeft)
                if (f.getColorChar() != Field.BLUE) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerRight)
                if (f.getColorChar() != Field.BLACK) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperLeft)
                if (f.getColorChar() != Field.YELLOW) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperRight)
                if (f.getColorChar() != Field.GREEN) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upper)
                if (f.getColorChar() != Field.RED) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lower)
                if (f.getColorChar() != Field.LILA) {
                    winner = false;
                    break;
                }

            return winner;
        }
        return false;
    }

    private static boolean squareCheck(Board board) {
        boolean winner = true;
        if (players == 2) {
            for (Field f : board.upperLeft)
                if (f.getColorChar() != Field.YELLOW) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerRight)
                if (f.getColorChar() != Field.BLACK) {
                    winner = false;
                    break;
                }

            return winner;
        }
        if (players == 4) {
            for (Field f : board.upperLeft)
                if (f.getColorChar() != Field.YELLOW) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerRight)
                if (f.getColorChar() != Field.BLACK) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.upperRight)
                if (f.getColorChar() != Field.GREEN) {
                    winner = false;
                    break;
                }

            if (winner) return true;

            winner = true;
            for (Field f : board.lowerLeft)
                if (f.getColorChar() != Field.BLUE) {
                    winner = false;
                    break;
                }

            return winner;
        }
        return false;
    }
}
