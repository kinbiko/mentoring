import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Kata Bowling - http://codingdojo.org/kata/Bowling/
 * A string based solution. This code can be improved, however,
 * not additional changes will be made to this code,
 * since there is a better approach passing points directly per roll.
 */
public class BowlingTest {

    @Test
    public void givenNullReturnZero() {
        assertEquals(0, runTest(null));
    }

    @Test
    public void givenMissReturnZero() {
        assertEquals(0, runTest("-"));
    }

    @Test
    public void givenStrikeReturnTen() {
        assertEquals(10, runTest("X"));
    }

    @Test
    public void givenNumberReturnNumber() {
        assertEquals(9, runTest("9"));
    }

    @Test
    public void givenFrameWithNumberAndMissReturnNumber() {
        assertEquals(9, runTest("9-"));
    }

    @Test
    public void givenSpareReturnValidScore() {
        assertEquals(15, runTest("9/ 5"));
    }

    @Test
    public void givenPerfectScoreReturnValidScore() {
        assertEquals(300, runTest("X X X X X X X X X X X X"));
    }

    @Test
    public void givenGameOfPairsAndMissessReturnValidScore() {
        assertEquals(90, runTest("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"));
    }

    @Test
    public void givenGameOfSparesReturnValidScore() {
        assertEquals(150, runTest("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"));
    }

    @Test
    public void givenMixedGameReturnValidScore() {
        assertEquals(138,runTest("2/ X X X 9- -- 2/ 52 11 5-"));
    }

    @Test
    public void givenOtherMixedGameReturnValidScore() {
        assertEquals(138,runTest("2/ X X X 81 -- 2/ 52 11 5-"));
    }

    private int runTest(String frames) {
        return new Bowling().gameScore(frames);
    }

}

class Bowling {

    /**
     *
     * @param frames
     * @return total score of game
     * We're not testing valid rolls here,
     * For spare '/' we assume this comes after a number that represents the first not strike roll
     * We don't allow '/' to be first character (this not have sense and should break our code).
     */
    int gameScore(String frames) {
        int totalScore = 0;

        if (frames == null)
            return 0;

        frames = frames.replaceAll("\\s","");
        int gameLength = frames.length();

        if (gameLength >= 2) {
            for (int i = 0; i < gameLength; i++) {
                if (frames.charAt(i)  == '/') {
                    if (i > 0)
                        totalScore -= rollScore(frames.charAt(i - 1));

                    totalScore += rollScore(frames.charAt(i));
                    totalScore += rollScore(frames.charAt(i + 1));

                    if (frames.charAt(i+1) != 'X' && i < gameLength - 2)
                        if (frames.charAt(i+2) != '/')
                            totalScore += rollScore(frames.charAt(i + 2));

                    if ( i == gameLength - 2 )
                        return totalScore;

                } else if (frames.charAt(i)  == 'X') {
                        totalScore += rollScore(frames.charAt(i));
                        totalScore += rollScore(frames.charAt(i+1));
                        totalScore += rollScore(frames.charAt(i+2));

                        if ( i == gameLength - 3 )
                            return totalScore;

                } else {
                    totalScore += rollScore(frames.charAt(i));
                }
            }

        } else {
            totalScore = rollScore(frames.charAt(0));
        }

        return totalScore;
    }

    private int rollScore(Character roll) {
        switch (roll) {
            case ' ':
                return 0;
            case '-':
                return 0;
            case 'X':
                return 10;
            case '/':
                return 10;
            default:
                return Character.getNumericValue(roll);
        }
    }

}
