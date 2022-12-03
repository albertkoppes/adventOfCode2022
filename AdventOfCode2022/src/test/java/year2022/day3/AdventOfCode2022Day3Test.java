package year2022.day3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import adventofcode.utils.Utils;
import year2022.day3.AdventOfCode2022Day3.ElfGroup;
import year2022.day3.AdventOfCode2022Day3.RuckSack;

class AdventOfCode2022Day3Test
{
    @Test
    void testRuckSackReorganization()
    {
        List<String> lines = Utils.readLines("input2022Day3a.txt");
        List<RuckSack> ruckSacks = lines.stream().map(RuckSack::new).collect(Collectors.toList());
        assertEquals(6, ruckSacks.size());
        List<Character> commonChars = ruckSacks.stream().map(RuckSack::getCommonItem).collect(Collectors.toList());
        assertEquals(List.of('p', 'L', 'P', 'v', 't', 's'), commonChars);
        int totalValue = commonChars.stream().map(RuckSack::getValueOfChar).mapToInt(i -> i).sum();
        assertEquals(157, totalValue);
    }

    @Test
    void testRuckSackGroups()
    {
        List<String> lines = Utils.readLines("input2022Day3a.txt");
        List<ElfGroup> elfGroups = new ArrayList<>();
        List<RuckSack> ruckSacks = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++)
        {
            ruckSacks.add(new RuckSack(lines.get(i)));
            if ((i + 1) % 3 == 0)
            {
                elfGroups.add(new ElfGroup(ruckSacks));
                ruckSacks = new ArrayList<>();
            }
        }
        if (!ruckSacks.isEmpty())
        {
            throw new IllegalArgumentException("Number of rucksacks in group not equal plural of 3");
        }
        List<Character> elfGroupBadges = elfGroups.stream().map(ElfGroup::getCommonItem).collect(Collectors.toList());
        assertEquals(70, elfGroupBadges.stream().map(RuckSack::getValueOfChar).mapToInt(i -> i).sum());
    }

    @Test
    void testRealInput()
    {
        List<String> lines = Utils.readLines("input2022Day3.txt");
        List<AdventOfCode2022Day3.RuckSack> ruckSacks = lines.stream().map(AdventOfCode2022Day3.RuckSack::new).collect(Collectors.toList());
        List<Character>commonChars=ruckSacks.stream().map(AdventOfCode2022Day3.RuckSack::getCommonItem).collect(Collectors.toList());
        int totalValue = commonChars.stream().map(AdventOfCode2022Day3.RuckSack::getValueOfChar).mapToInt(i->i).sum();
        assertEquals(8401,totalValue);

        List<ElfGroup> elfGroups = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++)
        {
            ruckSacks.add(new RuckSack(lines.get(i)));
            if ((i+1)%3 == 0)
            {
                elfGroups.add(new ElfGroup(ruckSacks));
                ruckSacks = new ArrayList<>();
            }
        }
        if (!ruckSacks.isEmpty())
        {
            throw new IllegalArgumentException("Number of rucksacks in group not equal to 3");
        }
        List<Character> elfGroupBadges = elfGroups.stream().map(ElfGroup::getCommonItem).collect(Collectors.toList());
        assertEquals(2641, elfGroupBadges.stream().map(RuckSack::getValueOfChar).mapToInt(i->i).sum());

    }


}