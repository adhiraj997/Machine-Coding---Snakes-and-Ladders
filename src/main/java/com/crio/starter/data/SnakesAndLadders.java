package com.crio.starter.data;

import com.crio.starter.enums.Dice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnakesAndLadders {
    private final int[] grid = new int[100];
    private List<Player> players = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();
    private List<Ladder> ladders = new ArrayList<>();
    private Dice dice;
}
