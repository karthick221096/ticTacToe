package com.example.tictactoe_revision.model;

import com.example.tictactoe_revision.exception.BotCountMoreThanOneException;
import com.example.tictactoe_revision.exception.DuplicateSymbolException;
import com.example.tictactoe_revision.exception.PlayerCountException;
import com.example.tictactoe_revision.strategy.WinningStrategy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Game {

    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    private int nextPlayerIndex;
    private GameState gameState;
    private int dimension;

    public Game(int dimension,List<Player> players, List<WinningStrategy> winningStrategies){
        this.dimension = dimension;
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private List<Player> players;
        private int dimensions;
        private List<WinningStrategy> winningStrategies;

        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Builder addPlayer(Player player){
            players.add(player);
            return this;
        }

        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension (int dimensions){
            this.dimensions = dimensions;
            return this;
        }

        private void validateBotCount() throws BotCountMoreThanOneException {
            int botSize = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.Bot)){
                    botSize += 1;
                }
            }
            if(botSize>1){
                throw new BotCountMoreThanOneException();
            }

        }

        private void validateDimensionAndPlayerCount() throws PlayerCountException {
            if(players.size() != this.dimensions -1 ){
                throw new PlayerCountException();
            }
        }

        private void validateSymbolUniqueness() throws DuplicateSymbolException{
            Map<Character,Integer> symbolCount = new HashMap<>();

            for (Player player: players){
                if(!symbolCount.containsKey(player.getSymbol().getAChar())){
                    symbolCount.put(player.getSymbol().getAChar(),0);
                }

                symbolCount.put(player.getSymbol().getAChar(),symbolCount.get(player.getSymbol().getAChar())+1);

                if(symbolCount.get(player.getSymbol().getAChar()) > 1){
                    throw new DuplicateSymbolException();
                }
            }
        }

        public void validate() throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException{
            validateBotCount();
            validateDimensionAndPlayerCount();
            validateSymbolUniqueness();
        }

        public Game build() throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException{
            validate();
            return new Game(dimensions,players,winningStrategies);
        }
    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize() || col >= board.getSize()){
            return false;
        }

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            return false;
        }
        return true;
    }

    private boolean checkWinner(Board board, Move move){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public void printBoard(){
        board.printBoard();
    }

    public void makeMove(){
        Player currentMovePlayer = players.get(nextPlayerIndex);

        System.out.println("It is "+currentMovePlayer.getName()+" 's, turn please make a move");

        Move currentPlayerMove = currentMovePlayer.makeMove(board);

        if(!validateMove(currentPlayerMove)){
            System.out.println("Invalid move. please try again ");
            return;
        }

        int row = currentPlayerMove.getCell().getRow();
        int col = currentPlayerMove.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentMovePlayer);

        Move finalMoveObject = new Move(cellToChange,currentMovePlayer);
        moves.add(finalMoveObject);

        nextPlayerIndex = nextPlayerIndex+1;
        nextPlayerIndex = nextPlayerIndex % players.size();

        if(checkWinner(board,finalMoveObject)){
            gameState = GameState.WIN;
            winner = currentMovePlayer;
        }else if ( moves.size() == this.board.getSize() * this.board.getSize() ) {
            gameState = GameState.DRAW;
        }
    }

    public void undo(){
        if(moves.size()==0){
            System.out.println("No move to undo");
            return;
        }

        Move lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);

        for (WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(board,lastMove);
        }

        nextPlayerIndex -= 1;
        nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();
    }
}
