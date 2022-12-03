package year2022.day2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;

class AdventOfCode2022Day2Part2Test
{

    @Test
    void readDraws()
    {
        List<String> lines = Utils.readLines("input2022Day2a.txt");
        List<AdventOfCode2022Day2Part2.Draw> draws = AdventOfCode2022Day2Part2.readDraws(lines);
        assertEquals(3, draws.size());
        assertEquals(12, AdventOfCode2022Day2Part2.getTotalScore(draws));
    }

    @Test
    void getTotalScore()
    {
    }
}