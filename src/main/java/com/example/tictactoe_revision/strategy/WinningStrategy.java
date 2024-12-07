package com.example.tictactoe_revision.strategy;

import com.example.tictactoe_revision.model.Board;
import com.example.tictactoe_revision.model.Move;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Move move);
    public void handleUndo(Board board, Move move);
}
