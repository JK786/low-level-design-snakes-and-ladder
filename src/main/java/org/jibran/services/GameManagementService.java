package org.jibran.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jibran.models.Board;
import org.jibran.models.Player;

import java.util.List;

public class GameManagementService {

    private Dice dice;

    private Board board;

    private List<Player> players;

    private Integer currentPlayerIndex = -1;



    public void initializeGame(final Dice dice,final Board board, final List<Player> players) {
        this.dice = dice;
        this.board = board;
        this.players = players;
    }


    public void play() {
        //Illegal start
        for(int i = 0;i< players.size();i++) {
            if(players.get(i).getCurrentPosition() != 0) {
                throw new IllegalArgumentException("Player " + players.get(i).getFirstName() + " is already playing");
            }
        }

        while(Boolean.TRUE) {

            final Integer currentPlayerIndex = this.getPlayerTurn();
            this.currentPlayerIndex = currentPlayerIndex;
            System.out.println("Player " + players.get(currentPlayerIndex).getFirstName() + " turn");

            final Integer diceRollResult = this.dice.roll();
            final Integer newPosition = this.getNewPositon(diceRollResult, players.get(0).getCurrentPosition());

            System.out.println("Player " + players.get(currentPlayerIndex)  + " is making the move to position : " + newPosition);
            players.get(currentPlayerIndex).setCurrentPosition(newPosition);

            if(this.isGameOver(newPosition)) {
                System.out.println("Player " + players.get(currentPlayerIndex).getFirstName() + " wins the game");
                break;
            }

        }

    }


    private Boolean isGameOver(final Integer position) {
        return position == 100;
    }

    private Integer getNewPositon(final Integer diceResult , final Integer currentPositon) {

        Integer nextPosition = currentPositon + diceResult;


        //Check if any snake there then adjust position.
        for(int i = 0; i<this.board.getSnakes().size();i++) {
           if(nextPosition == this.board.getSnakes().get(i).getHead()) {
               System.out.println("Snake encountered : start | " + this.board.getSnakes().get(i).getHead()  + " end | "  + this.board.getSnakes().get(i).getTail());
               nextPosition = this.board.getSnakes().get(i).getTail();
           }
        }

        //Check if any ladder there then adjust position.
        for(int i = 0; i<this.board.getLadders().size();i++) {
            if(nextPosition == this.board.getLadders().get(i).getBase()) {
                System.out.println("Ladder encountered : start | " + this.board.getLadders().get(i).getBase()  + " end | "  + this.board.getLadders().get(i).getTop());
                nextPosition = this.board.getLadders().get(i).getTop();
            }
        }

        if(nextPosition > 100) {
            return currentPositon;
        }

        return nextPosition;

    }

    private Integer getPlayerTurn() {
       return  (currentPlayerIndex + 1)%(this.players.size());
    }
}
