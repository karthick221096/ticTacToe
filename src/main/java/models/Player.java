package models;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

public class Player {

    @Setter
    @Getter

    private Long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long id, String name, Symbol symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Move makeMove(Board board){
        System.out.println("please enter a row where you want to make a move");
        int row = scanner.nextInt();

        System.out.println("please enter a col where you want to make a move");
        int col = scanner.nextInt();

        return new Move(new Cell(row,col),this);
    }
}
