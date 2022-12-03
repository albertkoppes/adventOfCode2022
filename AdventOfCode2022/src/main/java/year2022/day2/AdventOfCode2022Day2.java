package year2022.day2;

import java.util.List;
import java.util.stream.Collectors;

import adventofcode.utils.Utils;

public class AdventOfCode2022Day2
{
    // A X rock
    // B Y paper
    // C Z scissors

    private static final char YOUR_DRAW_ROCK = 'X';
    private static final char YOUR_DRAW_PAPER = 'Y';
    private static final char YOUR_DRAW_SCISSORS = 'Z';
    private static final char OPPONENT_DRAW_ROCK = 'A';
    private static final char OPPONENT_DRAW_PAPER = 'B';
    private static final char OPPONENT_DRAW_SCISSORS = 'C';

    public static void main(String[] args)
    {
        List<String> lines = Utils.readLines("input2022Day2.txt");
        List<AdventOfCode2022Day2.Draw> draws = AdventOfCode2022Day2.readDraws(lines);
        System.out.println(draws.size());
        System.out.println(getTotalScore(draws));

    }
    public static class Draw
    {
        final char opponentsDraw;
        final char yourDraw;

        private Draw(char opponentsDraw, char yourDraw)
        {
            this.opponentsDraw = opponentsDraw;
            this.yourDraw = yourDraw;
        }

        int getScore()
        {
            switch (opponentsDraw)
            {
                case OPPONENT_DRAW_ROCK:
                    return getScoreRock(yourDraw);
                case OPPONENT_DRAW_PAPER:
                    return getScorePaper(yourDraw);
                case OPPONENT_DRAW_SCISSORS:
                    return getScoreScissors(yourDraw);
                default:
                    throw new IllegalArgumentException("Illegal opponents draw: " + opponentsDraw);
            }
        }

        private int getScoreScissors(char yourDraw)
        {
            int score = getYourDrawScore(yourDraw);
            if (yourDraw == YOUR_DRAW_ROCK)
            {
                score += 6;
            }
            else if (yourDraw == YOUR_DRAW_SCISSORS)
            {
                score += 3;
            }
            return score;
        }

        private int getYourDrawScore(char yourDraw)
        {
            switch (yourDraw)
            {
                case YOUR_DRAW_ROCK:
                    return 1;
                case YOUR_DRAW_PAPER:
                    return 2;
                case YOUR_DRAW_SCISSORS:
                    return 3;
                default:
                    throw new IllegalArgumentException("Illegal draw: " + yourDraw);

            }
        }

        private int getScorePaper(char yourDraw)
        {
            int score = getYourDrawScore(yourDraw);
            if (yourDraw == YOUR_DRAW_SCISSORS)
            {
                score += 6;
            }
            else if (yourDraw == YOUR_DRAW_PAPER)
            {
                score += 3;
            }
            return score;
        }

        private int getScoreRock(char yourDraw)
        {
            int score = getYourDrawScore(yourDraw);
            if (yourDraw == YOUR_DRAW_PAPER)
            {
                score += 6;
            }
            else if (yourDraw == YOUR_DRAW_ROCK)
            {
                score += 3;
            }
            return score;
        }
    }

    static List<Draw> readDraws(List<String> lines)
    {
        return lines.stream().map(AdventOfCode2022Day2::readDraw).collect(Collectors.toList());
    }


    private static  Draw readDraw(String l)
    {
        return new Draw(l.charAt(0), l.charAt(2));
    }

    static int getTotalScore(List<Draw> draws)
    {
        return draws.stream().map(Draw::getScore).mapToInt(i->i).sum();
    }

}
