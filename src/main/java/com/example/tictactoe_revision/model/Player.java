package com.example.tictactoe_revision.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Player {
    private Long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long id, String name, Symbol symbol, PlayerType playerType){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board){
        System.out.println("Please enter the row where you want to make the move");
        int row = scanner.nextInt();
        System.out.println("Please enter the column where you want to make the move");
        int col = scanner.nextInt();
        return new Move(new Cell(row, col),this);
    }
}
