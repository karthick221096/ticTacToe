package models;

import lombok.Getter;
import lombok.Setter;

public class  Cell {

    @Getter
    @Setter
    private CellState cellState;
    private Player player;
    private int row;
    private int col;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.cellState= CellState.EMPTY;
    }

    //display logic needs to goes below
    public void display(){
        if(player == null){
            System.out.println("| - |");
        }
        else {
            System.out.println("| "+player.getSymbol().getSymbolChar()+" |");
        }
    }

}
