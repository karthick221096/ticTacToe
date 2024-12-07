package com.example.tictactoe_revision.strategy.botPlayingStrategy;

import com.example.tictactoe_revision.model.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();
    }
}
