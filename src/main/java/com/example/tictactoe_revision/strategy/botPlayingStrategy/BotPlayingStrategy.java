package com.example.tictactoe_revision.strategy.botPlayingStrategy;

import com.example.tictactoe_revision.model.Board;
import com.example.tictactoe_revision.model.Move;

public interface BotPlayingStrategy {
    public Move makeMove(Board board);
}
