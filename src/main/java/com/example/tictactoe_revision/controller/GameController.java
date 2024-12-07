package com.example.tictactoe_revision.controller;

import com.example.tictactoe_revision.exception.BotCountMoreThanOneException;
import com.example.tictactoe_revision.exception.DuplicateSymbolException;
import com.example.tictactoe_revision.exception.PlayerCountException;
import com.example.tictactoe_revision.model.Game;
import com.example.tictactoe_revision.model.GameState;
import com.example.tictactoe_revision.model.Player;
import com.example.tictactoe_revision.strategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies) throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException{
        return Game.builder().setDimension(dimensions).setPlayers(players).setWinningStrategy(winningStrategies).build();
    }

    public void makeMove(Game game){
        game.makeMove();
    }
    public void undo(Game game){
        game.undo();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public GameState checkGameState(Game game){
        return game.getGameState();
    }
    public String getWinner(Game game){
        return game.getWinner().getName();
    }
}
