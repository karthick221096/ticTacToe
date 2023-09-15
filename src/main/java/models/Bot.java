package models;

import strategy.BotPlayingStrategy;

public class Bot extends Player{

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Long id, String name, Symbol symbol, PlayerType playerType,BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, playerType.Bot);
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
