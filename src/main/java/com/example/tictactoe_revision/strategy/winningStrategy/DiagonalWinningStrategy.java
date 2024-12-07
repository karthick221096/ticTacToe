package com.example.tictactoe_revision.strategy.winningStrategy;

import com.example.tictactoe_revision.model.Board;
import com.example.tictactoe_revision.model.Move;
import com.example.tictactoe_revision.model.Symbol;
import com.example.tictactoe_revision.strategy.WinningStrategy;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {

    private Map<Symbol,Integer> leftDiagnalCount = new HashMap<>();
    private Map<Symbol,Integer> rightDiagnalCount = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        // left diagonal
        if(row==col){
            if(!leftDiagnalCount.containsKey(symbol)){
                leftDiagnalCount.put(symbol,0);
            }
            leftDiagnalCount.put(symbol,leftDiagnalCount.get(symbol)+1);

            if(leftDiagnalCount.get(symbol)==board.getSize()){
                return true;
            }
        }
        //right diagonal
        if(row+col == board.getSize()-1){
            if(!rightDiagnalCount.containsKey(symbol)){
                rightDiagnalCount.put(symbol,0);
            }
            rightDiagnalCount.put(symbol,rightDiagnalCount.get(symbol)+1);

            if (rightDiagnalCount.get(symbol)==board.getSize()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            leftDiagnalCount.put(symbol,leftDiagnalCount.get(symbol)-1);
        }

        if(row+col == board.getSize()-1){
            rightDiagnalCount.put(symbol,rightDiagnalCount.get(symbol)-1);
        }

    }
}
