package com.example.tictactoe_revision;

import com.example.tictactoe_revision.controller.GameController;
import com.example.tictactoe_revision.model.*;
import com.example.tictactoe_revision.strategy.WinningStrategy;
import com.example.tictactoe_revision.strategy.winningStrategy.ColWinningStrategy;
import com.example.tictactoe_revision.strategy.winningStrategy.DiagonalWinningStrategy;
import com.example.tictactoe_revision.strategy.winningStrategy.RowWinningStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TictactoeRevisionApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(TictactoeRevisionApplication.class, args);
//    }
public static void main(String[] args) {
    GameController gameController = new GameController();
    Scanner scanner = new Scanner(System.in);

    int dimensionOfGame = 3;
    List<Player> players = new ArrayList<>();
    players.add(
            new Player(1L, "Rishabh", new Symbol('X'), PlayerType.HUMAN)
    );

    players.add(
            new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.EASY)
    );

    List<WinningStrategy> winningStrategies = new ArrayList<>();
    winningStrategies.add(new RowWinningStrategy());
    winningStrategies.add(new ColWinningStrategy());
    winningStrategies.add(new DiagonalWinningStrategy());

    Game game = null;
    try {
        game = gameController.startGame(
                dimensionOfGame,
                players,
                winningStrategies
        );

        while(gameController.checkGameState(game).equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            System.out.println("Does anyone want to do an undo (y/n");
            String undoAnswer = scanner.next();
            if(undoAnswer.equalsIgnoreCase("y")) {
                gameController.undo(game);
                continue;
            }

            gameController.makeMove(game);
        }
    } catch(Exception e) {
        System.out.println("Something went wrong");
    }

    gameController.printBoard(game);
    System.out.println("Game is finished");
    GameState gameState = gameController.checkGameState(game);
    if(gameState.equals(GameState.WIN)) {
        System.out.println("Winner is: " + gameController.getWinner(game));
    } else if(gameState.equals(GameState.DRAW)) {
        System.out.println("Game Drawn");
    }

}

}
