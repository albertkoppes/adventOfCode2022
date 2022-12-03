package year2022.day2;

import java.util.List;
import java.util.stream.Collectors;

import adventofcode.utils.Utils;

public class AdventOfCode2022Day2Part2
{
    // A X rock
    // B Y paper
    // C Z scissors

    private static final char YOU_NEED_TO_LOSE = 'X';
    private static final char YOU_NEED_TO_DRAW = 'Y';
    private static final char YOU_NEED_TO_WIN = 'Z';
    private static final char YOUR_DRAW_ROCK = 'X';
    private static final char YOUR_DRAW_PAPER = 'Y';
    private static final char YOUR_DRAW_SCISSORS = 'Z';
    private static final char OPPONENT_DRAW_ROCK = 'A';
    private static final char OPPONENT_DRAW_PAPER = 'B';
    private static final char OPPONENT_DRAW_SCISSORS = 'C';

    public static void main(String[] args)
    {
        List<String> lines = Utils.readLines("input2022Day2.txt");
        List<Draw> draws = readDraws(lines);
        System.out.println(draws.size());
        System.out.println(getTotalScore(draws));

    }
    public static class Draw
    {
        final char opponentsDraw;
        final char howToEnd;
        char yourDraw;

        private Draw(char opponentsDraw, char howToEnd)
        {
            this.opponentsDraw = opponentsDraw;
            this.howToEnd = howToEnd;
        }

        int getScore()
        {
            yourDraw = calcYourDraw(opponentsDraw, howToEnd);
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

        private char calcYourDraw(char opponentsDraw, char howToEnd)
        {
            switch (howToEnd)
            {
                case YOU_NEED_TO_LOSE:
                    return calcLose(opponentsDraw);
                case YOU_NEED_TO_DRAW:
                    return calcDraw(opponentsDraw);
                case YOU_NEED_TO_WIN:
                    return calcWin(opponentsDraw);
                default:
                    throw new IllegalArgumentException("Illegal how to end:" + howToEnd);
            }

        }

        private char calcWin(char opponentsDraw)
        {
            switch (opponentsDraw)
            {
                case OPPONENT_DRAW_ROCK:
                    return YOUR_DRAW_PAPER;
                case OPPONENT_DRAW_SCISSORS:
                    return YOUR_DRAW_ROCK;
                case OPPONENT_DRAW_PAPER:
                    return YOUR_DRAW_SCISSORS;
                default:
                    throw new IllegalArgumentException("Illegal how to end:" + opponentsDraw);
            }

        }

        private char calcDraw(char opponentsDraw)
        {
            switch (opponentsDraw)
            {
                case OPPONENT_DRAW_ROCK:
                    return YOUR_DRAW_ROCK;
                case OPPONENT_DRAW_SCISSORS:
                    return YOUR_DRAW_SCISSORS;
                case OPPONENT_DRAW_PAPER:
                    return YOUR_DRAW_PAPER;
                default:
                    throw new IllegalArgumentException("Illegal how to end:" + opponentsDraw);
            }
        }

        private char calcLose(char opponentsDraw)
        {
            switch (opponentsDraw)
            {
                case OPPONENT_DRAW_ROCK:
                    return YOUR_DRAW_SCISSORS;
                case OPPONENT_DRAW_SCISSORS:
                    return YOUR_DRAW_PAPER;
                case OPPONENT_DRAW_PAPER:
                    return YOUR_DRAW_ROCK;
                default:
                    throw new IllegalArgumentException("Illegal how to end:" + opponentsDraw);
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
        return lines.stream().map(AdventOfCode2022Day2Part2::readDraw).collect(Collectors.toList());
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
