package com.example.tictactoe_revision.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Move {
    private Cell cell;
    private Player player;
    public Move(Cell cell, Player player){
        this.cell = cell;
        this.player = player;
    }
}
