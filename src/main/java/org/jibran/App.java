package org.jibran;

import org.jibran.models.Board;
import org.jibran.models.Ladder;
import org.jibran.models.Player;
import org.jibran.models.Snake;
import org.jibran.services.Dice;
import org.jibran.services.GameManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Scanner scanner = new Scanner(System.in);

        Integer numberOfSnakes = 0;
        Integer numberOfLadders = 0;
        Integer numberOfPlayers = 0;

        final List<Snake> snakes = new ArrayList<>();
        final List<Ladder> ladders = new ArrayList<>();
        final List<Player> players = new ArrayList<>();

        //Reading number of snakes
        if(scanner.hasNext()) {
           numberOfSnakes = Integer.parseInt(scanner.nextLine());
        }




        //Reading snakes
        while(numberOfSnakes > 0) {
            String snake = scanner.nextLine();

            Integer start = Integer.parseInt(snake.split(" ")[0]);
            Integer end = Integer.parseInt(snake.split(" ")[1]);

            snakes.add(new Snake(start, end));

            numberOfSnakes = numberOfSnakes - 1;
        }


        //Reading number of ladders
        if(scanner.hasNext()) {
            numberOfLadders = Integer.parseInt(scanner.nextLine());
        }

        //Reading ladders
        while(numberOfLadders > 0) {
            String ladder = scanner.nextLine();
            Integer start = Integer.parseInt(ladder.split(" ")[0]);
            Integer end = Integer.parseInt(ladder.split(" ")[1]);

            ladders.add(new Ladder(start, end));

            numberOfLadders = numberOfLadders - 1;
        }

        //Reading number of players
        if(scanner.hasNext()) {
           numberOfPlayers =  Integer.parseInt(scanner.nextLine());
        }


        //Reading players
        while(numberOfPlayers > 0) {
             String firstName  = scanner.nextLine();
             players.add(new Player(UUID.randomUUID().toString(),firstName, 0));

             numberOfPlayers = numberOfPlayers - 1;
        }


        //Initialize the board
        Board board = new Board(snakes, ladders);
        Dice dice = new Dice();
        GameManagementService gameManagementService = new GameManagementService();

        gameManagementService.initializeGame(dice,board,players);

        gameManagementService.play();

    }
}
