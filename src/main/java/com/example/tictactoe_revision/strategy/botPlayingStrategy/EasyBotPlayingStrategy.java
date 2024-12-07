package com.example.tictactoe_revision.strategy.botPlayingStrategy;

import com.example.tictactoe_revision.model.Board;
import com.example.tictactoe_revision.model.Cell;
import com.example.tictactoe_revision.model.CellState;
import com.example.tictactoe_revision.model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> cells: board.getBoard()){
            for(Cell cell:cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell,null);
                }
            }
        }
        return null;
    }
}
