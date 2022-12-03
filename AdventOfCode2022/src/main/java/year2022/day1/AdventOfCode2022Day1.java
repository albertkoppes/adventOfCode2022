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
        List<ElfCalories> elves = getElfCalories(lines);
        int maxCalories = getMaxCalories(elves);
        System.out.println(maxCalories);
        int nMaxCalories = getMaxNCalories(3, elves);
        System.out.println(nMaxCalories);
    }
    public static class ElfCalories
    {
        private final List<Integer> calories;

        private ElfCalories(List<Integer> calories)
        {
            this.calories = calories;
        }

        private int calcCalories()
        {
            return calories.stream().mapToInt(i-> i).sum();
        }
    }

    static List<ElfCalories> getElfCalories(List<String> lines)
    {
        List<ElfCalories> elfCalories = new ArrayList<>();
        List<Integer> currentCalories = new ArrayList<>();
        for (String line : lines)
        {
            if (line.isEmpty())
            {
                if (!currentCalories.isEmpty())
                {
                    elfCalories.add(new ElfCalories(currentCalories));
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
            elfCalories.add(new ElfCalories(currentCalories));
        }
        return elfCalories;
    }

    static int getMaxCalories(List<ElfCalories> elfCalories)
    {
        return elfCalories.stream().map(ElfCalories::calcCalories).mapToInt(i->i).max().orElse(0);
    }

    static int getMaxNCalories(int number, List<ElfCalories> elfCalories)
    {
        List<Integer> sorted = elfCalories.stream().map(ElfCalories::calcCalories).sorted().collect(Collectors.toList());
        return IntStream.range(0, number).map(i -> sorted.get(sorted.size() - 1 - i)).sum();
    }

}
