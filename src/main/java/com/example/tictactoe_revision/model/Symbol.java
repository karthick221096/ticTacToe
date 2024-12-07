package com.example.tictactoe_revision.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Symbol {
    private char aChar;

    public Symbol (char aChar){
        this.aChar = aChar;
    }
}
