package models;

import exceptions.BotCountMoreThanOneException;
import exceptions.DuplicateSymbolException;
import exceptions.PlayerCountException;
import strategy.WinningStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> playerList;
    private GameState gameState;
    private Board board;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    private int nextPlayerIndex;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    // whatever provided by client has to be put in builder pattern
    // for validation
    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimensions;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }
        //exception throw part in builder
        //BotCountMoreThanOneException
        private void validateBotCount() throws BotCountMoreThanOneException {
            //get the list of players check whether the player is bot and increase the count of bot
            int botSize =0;
            for (Player player:players){
                if(player.getPlayerType().equals(PlayerType.Bot)){
                    botSize+=1;
                }
            }
            if(botSize>1){
                throw new BotCountMoreThanOneException();
            }

        }

        //SymbolUniquenessException
        private void validateSymbolUniqueness() throws DuplicateSymbolException {
            Map<Character,Integer> symbolCount = new HashMap<>();
            for(Player player:players){
                if(!symbolCount.containsKey(player.getSymbol().getSymbolChar())){
                    symbolCount.put(player.getSymbol().getSymbolChar(),0);
                }
                symbolCount.put(
                        player.getSymbol().getSymbolChar(),
                        symbolCount.get(player.getSymbol().getSymbolChar() +1)
                );
            }
        }

        //player size and dimension exception
        private void validateDimensionAndPlayerCount() throws PlayerCountException {
            if(players.size()!= this.dimensions-1){
                throw new PlayerCountException();
            }
        }


    }
}
