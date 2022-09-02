package app.halma.play;

public class WinnerChecker {
    public static int players = 2;
    public static boolean upperWin, lowerWin, upperLeftWin, upperRightWin, lowerLeftWin, lowerRightWin;
    public static boolean check(Board board){
        if(board.isSquare()) return squareCheck(board);
        boolean winner = true;
        if(players == 2){
            for(Field f : board.lower)
                if(f.getColorChar() != Field.LILA)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upper)
                if(f.getColorChar() != Field.RED)
                    winner = false;

            if(winner) return true;
        }
        if(players == 3){
            for(Field f : board.lowerLeft)
                if(f.getColorChar() != Field.BLUE)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerRight)
                if(f.getColorChar() != Field.BLACK)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upper)
                if(f.getColorChar() != Field.RED)
                    winner = false;

            if(winner) return true;
        }
        if(players == 4){
            for(Field f : board.lowerLeft)
                if(f.getColorChar() != Field.BLUE)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerRight)
                if(f.getColorChar() != Field.BLACK)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperLeft)
                if(f.getColorChar() != Field.YELLOW)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperRight)
                if(f.getColorChar() != Field.GREEN)
                    winner = false;

            if(winner) return true;
        }
        if(players == 5){
            for(Field f : board.lowerLeft)
                if(f.getColorChar() != Field.BLUE)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerRight)
                if(f.getColorChar() != Field.BLACK)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperLeft)
                if(f.getColorChar() != Field.YELLOW)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperRight)
                if(f.getColorChar() != Field.GREEN)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upper)
                if(f.getColorChar() != Field.RED)
                    winner = false;

            if(winner) return true;
        }
        if(players == 6){
            for(Field f : board.lowerLeft)
                if(f.getColorChar() != Field.BLUE)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerRight)
                if(f.getColorChar() != Field.BLACK)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperLeft)
                if(f.getColorChar() != Field.YELLOW)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperRight)
                if(f.getColorChar() != Field.GREEN)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upper)
                if(f.getColorChar() != Field.RED)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lower)
                if(f.getColorChar() != Field.LILA)
                    winner = false;

            if(winner) return true;
        }
        return false;
    }

    private static boolean squareCheck(Board board) {
        boolean winner = true;
        if(players == 2){
            for(Field f : board.upperLeft)
                if(f.getColorChar() != Field.YELLOW)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerRight)
                if(f.getColorChar() != Field.BLACK)
                    winner = false;

            if(winner) return true;
        }
        if(players == 4){
            for(Field f : board.upperLeft)
                if(f.getColorChar() != Field.YELLOW)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerRight)
                if(f.getColorChar() != Field.BLACK)
                    winner = false;

            if(winner) return true;

            for(Field f : board.upperRight)
                if(f.getColorChar() != Field.GREEN)
                    winner = false;

            if(winner) return true;

            for(Field f : board.lowerLeft)
                if(f.getColorChar() != Field.BLUE)
                    winner = false;

            if(winner) return true;
        }
        return false;
    }
}
