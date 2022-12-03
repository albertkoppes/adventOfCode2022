package year2022.day3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class AdventOfCode2022Day3
{
    static class RuckSack
    {
        final char[] leftCompartment;
        final char[] rightCompartment;
        final char[] total;


        RuckSack(String line)
        {
            if (line.length() %2 != 0)
            {
                throw new IllegalArgumentException("Length of line uneven: " + line);
            }
            int half = line.length()/2;
            total = line.toCharArray();
            leftCompartment = line.substring(0, half).toCharArray();
            rightCompartment = line.substring(half).toCharArray();
        }

        char getCommonItem()
        {
            for (char c : leftCompartment)
            {
                for (char value : rightCompartment)
                {
                    if (c == value)
                    {
                        return c;
                    }
                }
            }
            throw new IllegalArgumentException("No common element found between " + String.valueOf(leftCompartment) + " and " + String.valueOf(rightCompartment));
        }

        char[] getCommonItems(char[] otherArray)
        {

            HashSet<Character> set = new HashSet<>(Arrays.asList(box(total)));

            set.retainAll(Arrays.asList(box(otherArray)));

            return unbox(set.toArray(new Character[0]));
        }

        private Character[] box(char[] chars)
        {
            return new String(chars).chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        }

        private char[] unbox(Character[] chars)
        {
            char[] unboxed = new char[chars.length];
            IntStream.range(0, chars.length).forEach(i -> unboxed[i] = chars[i]);
            return unboxed;
        }

        static int getValueOfChar(char c)
        {
            boolean upperCase = Character.isUpperCase(c);
            // converting input letter in to uniform case.
            char inputLetterToLowerCase= Character.toLowerCase(c);
            // COnverting chat in to its ascii value
            // ASCII value of lower case letters starts from 97
            int position= inputLetterToLowerCase -96;
            return position + (upperCase ? 26 : 0);
        }
    }

    static class ElfGroup
    {
        final List<RuckSack> ruckSacks;

        ElfGroup(List<RuckSack> ruckSacks)
        {
            this.ruckSacks = ruckSacks;
        }

        char getCommonItem()
        {
            char[] intersection = ruckSacks.get(0).getCommonItems(ruckSacks.get(1).total);
            char[] lastIntersection = ruckSacks.get(2).getCommonItems(intersection);
            if (lastIntersection.length != 1)
            {
                throw new IllegalArgumentException("No single char found as intersection of " + List.of(ruckSacks));
            }
            return lastIntersection[0];
        }
    }

}
