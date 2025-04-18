package app.halma.play;

public class PlayColorManager {
    public static int maxPlayer = 2;

    public static void apply(Player player, int index, boolean square) {
        // Last one is always the human player; assign red.
        if (index == maxPlayer - 1) {
            player.setColor(Field.RED);
            return;
        }

        // Otherwise, assign colors to opponents.
        if (square) {
            square(player, index);
            return;
        }

        // Normal mode opponent color mapping (player is always last and red).
        if (maxPlayer == 2) {
            // Only one opponent exists.
            player.setColor(Field.LILA);
        } else if (maxPlayer == 3) {
            // Opponents at index 0 and 1.
            if (index == 0)
                player.setColor(Field.BLACK);
            else if (index == 1)
                player.setColor(Field.BLUE);  // Changed from red to blue because red is reserved.
        } else if (maxPlayer == 4) {
            // Opponents at index 0, 1, and 2.
            if (index == 0)
                player.setColor(Field.YELLOW);
            else if (index == 1)
                player.setColor(Field.GREEN);
            else if (index == 2)
                player.setColor(Field.BLACK);
        } else if (maxPlayer == 5) {
            // Opponents at index 0, 1, 2, and 3.
            if (index == 0)
                player.setColor(Field.YELLOW);
            else if (index == 1)
                player.setColor(Field.GREEN);
            else if (index == 2)
                player.setColor(Field.BLACK);
            else if (index == 3)
                player.setColor(Field.BLUE);
        } else if (maxPlayer == 6) {
            // Opponents at index 0, 1, 2, 3, and 4.
            if (index == 0)
                player.setColor(Field.YELLOW);
            else if (index == 1)
                player.setColor(Field.GREEN);
            else if (index == 2)
                player.setColor(Field.BLACK);
            else if (index == 3)
                player.setColor(Field.BLUE);
            else if (index == 4)
                player.setColor(Field.LILA);
        }
    }

    private static void square(Player player, int index) {
        // In square mode, assign colors to opponents (remember, player is always last and red).
        if (maxPlayer < 3) {
            if (index == 0)
                player.setColor(Field.BLACK);
        } else {
            // For maxPlayer >= 3, we define a mapping for opponent indices.
            if (index == 0)
                player.setColor(Field.BLACK);
            else if (index == 1)
                player.setColor(Field.BLUE);
            else if (index == 2)
                player.setColor(Field.YELLOW);
            else if (index == 3)
                player.setColor(Field.GREEN);
        }
    }
}
