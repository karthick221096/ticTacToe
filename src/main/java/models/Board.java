package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Setter
    @Getter
    private int size;
    private List<List<Cell>> board;

    public Board(int size){
        this.size = size;
        this.board = new ArrayList<>();
        //board structure creation logic
        for (int i = 0;i<size;i++){
            board.add(new ArrayList<>()); //[ [], [], [] ]
            for(int j =0;j<size;j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }

    public void printBoard(){
        for(List<Cell> row:board){
            for(Cell cell: row){
                cell.display();
            }
            System.out.println();
        }
    }

}
