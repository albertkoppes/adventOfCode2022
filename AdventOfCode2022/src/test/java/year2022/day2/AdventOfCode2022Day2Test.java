package year2022.day2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;

class AdventOfCode2022Day2Test
{

    @Test
    void readDrawsExample()
    {
        List<String> lines = Utils.readLines("input2022Day2a.txt");
        List<AdventOfCode2022Day2.Draw> draws = AdventOfCode2022Day2.readDraws(lines);
        assertEquals(3, draws.size());
        assertEquals(15, AdventOfCode2022Day2.getTotalScore(draws));
    }
    @Test
    void readDraws()
    {
        List<String> lines = Utils.readLines("input2022Day2.txt");
        List<AdventOfCode2022Day2.Draw> draws = AdventOfCode2022Day2.readDraws(lines);
        assertEquals(2500, draws.size());
        assertEquals(12156, AdventOfCode2022Day2.getTotalScore(draws));
    }
}