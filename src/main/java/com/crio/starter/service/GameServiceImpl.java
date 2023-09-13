package com.crio.starter.service;

import com.crio.starter.data.Ladder;
import com.crio.starter.data.Player;
import com.crio.starter.data.Snake;
import com.crio.starter.data.SnakesAndLadders;
import com.crio.starter.utility.DiceUtility;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public void playGame(SnakesAndLadders snakesAndLadders) {
        if (nonNull(snakesAndLadders)) {
            int numberOfPlayers = snakesAndLadders.getPlayers().size();
            int currentPlayer = 0;
            while (true) {
                if (currentPlayer == numberOfPlayers) {
                    currentPlayer = currentPlayer % numberOfPlayers;
                }
                Player player = snakesAndLadders.getPlayers().get(currentPlayer);
                int diceNumber = DiceUtility.rollDice();
                int originalPosition = player.getPosition();
                int newPosition = getPosition(player.getPosition(), diceNumber, snakesAndLadders);
                player.setPosition(newPosition);
                System.out.println(player.getName() + " rolled a " + diceNumber + " and moved from " + originalPosition + " to " + newPosition);
                if (player.getPosition() == 100) {
                    System.out.println(player.getName() + " wins the game");
                    System.exit(1);
                }
                currentPlayer++;
            }
        }
    }

    private int getPosition(int currentPosition, int diceNumber, SnakesAndLadders snakesAndLadders) {
        if (nonNull(snakesAndLadders)) {
            if (currentPosition + diceNumber <= 100) {
                int newPosition = currentPosition + diceNumber;
                newPosition = newPositionIfBitten(snakesAndLadders.getSnakes(), newPosition);
                newPosition = newPositionIfClimbed(snakesAndLadders.getLadders(), newPosition);
                return newPosition;
            }
            return currentPosition;
        }
        return -1;
    }

    private int newPositionIfBitten(List<Snake> snakes, int newPosition) {
        if (nonNull(snakes)) {
            while (true) {
                boolean hasSnake = false;
                for (Snake snake : snakes) {
                    if (nonNull(snake) && snake.getHead() == newPosition) {
                        hasSnake = true;
                        newPosition = snake.getTail();
                        break;
                    }
                }
                if (!hasSnake) break;
            }
        }

        return newPosition;
    }

    private int newPositionIfClimbed(List<Ladder> ladders, int newPosition) {
        if (nonNull(ladders)) {
            while (true) {
                boolean hasLadder = false;
                for (Ladder ladder : ladders) {
                    if (nonNull(ladder) && ladder.getStart() == newPosition) {
                        hasLadder = true;
                        newPosition = ladder.getEnd();
                        break;
                    }
                }
                if (!hasLadder) break;
            }
        }
        return newPosition;
    }
}
