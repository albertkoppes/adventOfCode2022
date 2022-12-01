package year2022.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode.utils.Utils;

public abstract class AdventOfCode2022Day1
{
    public static final String INPUT_DAY_1_TXT = "input2022Day1.txt";
    public static final String INPUT_DAY_1A_TXT = "input2022Day1a.txt";

    private AdventOfCode2022Day1()
    {
    }

    public static void main(String[] args)
    {
        List<String> lines = Utils.readLines(INPUT_DAY_1_TXT);
        List<ElveCalories> elves = getElveCalories(lines);
        int maxCalories = getMaxCalories(elves);
        System.out.println(maxCalories);
        int nMaxCalories = getMaxNCalories(3, elves);
        System.out.println(nMaxCalories);
    }
    public static class ElveCalories
    {
        private final List<Integer> calories;

        private ElveCalories(List<Integer> calories)
        {
            this.calories = calories;
        }

        private int calcCalories()
        {
            return calories.stream().mapToInt(i-> i).sum();
        }
    }

    static List<ElveCalories> getElveCalories(List<String> lines)
    {
        List<ElveCalories> elveCalories = new ArrayList<>();
        List<Integer> currentCalories = new ArrayList<>();
        for (String line : lines)
        {
            if (line.isEmpty())
            {
                if (!currentCalories.isEmpty())
                {
                    elveCalories.add(new ElveCalories(currentCalories));
                    currentCalories = new ArrayList<>();
                }
            }
            else
            {
                currentCalories.add(Integer.valueOf(line));
            }
        }
        if (!currentCalories.isEmpty())
        {
            elveCalories.add(new ElveCalories(currentCalories));
        }
        return elveCalories;
    }

    static int getMaxCalories(List<ElveCalories> elveCalories)
    {
        return elveCalories.stream().map(ElveCalories::calcCalories).mapToInt(i->i).max().orElse(0);
    }

    static int getMaxNCalories(int number, List<ElveCalories> elveCalories)
    {
        List<Integer> sorted = elveCalories.stream().map(ElveCalories::calcCalories).sorted().collect(Collectors.toList());
        return IntStream.range(0, number).map(i -> sorted.get(sorted.size() - 1 - i)).sum();
    }

}
