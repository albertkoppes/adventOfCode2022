package year2022.day1;

import static org.junit.jupiter.api.Assertions.*;

import static year2022.day1.AdventOfCode2022Day1.INPUT_DAY_1A_TXT;
import static year2022.day1.AdventOfCode2022Day1.INPUT_DAY_1_TXT;

import java.util.List;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;

class AdventOfCode2022Day1Test
{
    @Test
    void getElvesTest()
    {
        final List<String> lines = Utils.readLines(INPUT_DAY_1A_TXT);
        assertEquals(14, lines.size());
        List<AdventOfCode2022Day1.ElveCalories> elfCalories = AdventOfCode2022Day1.getElveCalories(lines);
        assertEquals(5, elfCalories.size());
        assertEquals(24000, AdventOfCode2022Day1.getMaxCalories(elfCalories));
        assertEquals(45000, AdventOfCode2022Day1.getMaxNCalories(3, elfCalories));
    }
    @Test
    void getElves()
    {
        final List<String> lines = Utils.readLines(INPUT_DAY_1_TXT);
        List<AdventOfCode2022Day1.ElveCalories> elfCalories = AdventOfCode2022Day1.getElveCalories(lines);
        assertEquals(72478, AdventOfCode2022Day1.getMaxCalories(elfCalories));
    }
}