package com.example.tictactoe_revision.model;

import com.example.tictactoe_revision.strategy.botPlayingStrategy.BotPlayingStrategy;
import com.example.tictactoe_revision.strategy.botPlayingStrategy.BotPlayingStrategyFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
public class Bot extends Player {

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, PlayerType.Bot);
        this.botDifficultyLevel = botDifficultyLevel;
        botPlayingStrategy =  BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board){
        Move botMove = botPlayingStrategy.makeMove(board);
        botMove.setPlayer(this);
        return botMove;
    }
}
