package models;

import lombok.Getter;
import lombok.Setter;

public class Symbol {


    private char symbolChar;

    public char getSymbolChar() {
        return symbolChar;
    }

    public void setSymbolChar(char symbolChar) {
        this.symbolChar = symbolChar;
    }

    public Symbol(char symbolChar) {
        this.symbolChar= symbolChar;
    }


}
