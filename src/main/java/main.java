import controllers.GameController;
import models.Game;

public class main {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        Game game = gameController.startGame();

    }
}
