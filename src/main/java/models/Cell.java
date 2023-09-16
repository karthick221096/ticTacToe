package models;

import lombok.Getter;
import lombok.Setter;

public class  Cell {

    private CellState cellState;
    private Player player;
    private int row;
    private int col;

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

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
