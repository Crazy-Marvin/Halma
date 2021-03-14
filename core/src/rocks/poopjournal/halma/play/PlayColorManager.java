package rocks.poopjournal.halma.play;

public class PlayColorManager {
    public static int maxPlayer = 2;
    public static void apply(Player player,int index, boolean square){
        if(square){square(player, index); return;}
        if(maxPlayer == 2){
            if(index == 0)
                player.setColor(Field.LILA);
            if(index == 1)
                player.setColor(Field.RED);
        }
        if(maxPlayer == 3){
            if(index == 0)
                player.setColor(Field.BLACK);
            if(index == 1)
                player.setColor(Field.RED);
            if(index == 2)
                player.setColor(Field.BLUE);
        }
        if(maxPlayer == 4){
            if(index == 0)
                player.setColor(Field.YELLOW);
            if(index == 1)
                player.setColor(Field.GREEN);
            if(index == 2)
                player.setColor(Field.BLACK);
            if(index == 3)
                player.setColor(Field.BLUE);
        }
        if(maxPlayer == 5){
            if(index == 0)
                player.setColor(Field.YELLOW);
            if(index == 1)
                player.setColor(Field.GREEN);
            if(index == 2)
                player.setColor(Field.BLACK);
            if(index == 3)
                player.setColor(Field.BLUE);
            if(index == 4)
                player.setColor(Field.RED);
        }
        if(maxPlayer == 6){
            if(index == 0)
                player.setColor(Field.YELLOW);
            if(index == 1)
                player.setColor(Field.GREEN);
            if(index == 2)
                player.setColor(Field.BLACK);
            if(index == 3)
                player.setColor(Field.BLUE);
            if(index == 4)
                player.setColor(Field.RED);
            if(index == 5)
                player.setColor(Field.LILA);
        }
    }

    private static void square(Player player, int index) {
        if(maxPlayer < 3){
            if(index == 0)
                player.setColor(Field.BLACK);
            if(index == 1)
                player.setColor(Field.YELLOW);
        }
        else{
            if(index == 0)
                player.setColor(Field.BLACK);
            if(index == 1)
                player.setColor(Field.BLUE);
            if(index == 2)
                player.setColor(Field.YELLOW);
            if(index == 3)
                player.setColor(Field.GREEN);
        }
    }
}
