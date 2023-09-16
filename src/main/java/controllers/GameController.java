package controllers;

import exceptions.BotCountMoreThanOneException;
import exceptions.DuplicateSymbolException;
import exceptions.PlayerCountException;
import models.Game;
import models.Player;
import strategy.WinningStrategy;

import java.util.List;

public class GameController {

    //start the game
    //get the game state
    //make next move
    //undo
    //check winner

    //start the game
    public Game startGame(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies)
    throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException {
        return Game.builder()
                .setDimensions(dimensions)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    //make move
    public void makeMove(Game game){
        game.makeMove();
    }

    public void checkGameState(Game game){

    }
}
