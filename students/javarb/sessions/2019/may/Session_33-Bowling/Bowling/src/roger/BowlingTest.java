package roger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingTest {

    private Bowling bowling = new Bowling();

    @Test
    public void canScoreGutterGame() {
        rollMany(20, 0);
        assertEquals(0, bowling.score());
    }

    @Test
    public void canScoreJustOnesAndTwos() {
        rollMany(10,1);
        rollMany(10,2);
        assertEquals(30, bowling.score());
    }

    @Test
    public void canScoreSpare(){
        bowling.roll(3);
        bowling.roll(7);
        bowling.roll(5);
        rollMany(17, 0);
        assertEquals(20, bowling.score());
    }

    @Test
    public void canScoreStrike(){
        bowling.roll(10);
        bowling.roll(3);
        bowling.roll(4);
        rollMany(16, 0);
        assertEquals(24, bowling.score());
    }

    @Test
    public void canScoreTwoStrikesAndAllZeros(){
        bowling.roll(10);
        bowling.roll(10);
        bowling.roll(10);
        rollMany(14, 0);
        assertEquals(60, bowling.score());
    }

    @Test
    public void canScorePerfectGame(){
        rollMany(12, 10);
        assertEquals(300, bowling.score());
    }

    /**
     * https://bowlinggenius.com/
     * 2/ X X X 9- -- 2/ 52 11 5-"
     */
    @Test
    public void canScoreMixedGame(){
        bowling.roll(2);
        bowling.roll(8);
        rollMany(3,10);
        bowling.roll(9);
        rollMany(3,0);
        bowling.roll(2);
        bowling.roll(8);
        bowling.roll(5);
        bowling.roll(2);
        rollMany(2,1);
        bowling.roll(5);
        bowling.roll(0);
        assertEquals(136, bowling.score());
    }

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++)
            bowling.roll(pins);

    }
}

class Bowling {

    private int currentRoll;
    private int[] rolls = new int[22];

    void roll(int pins) {
        rolls[currentRoll] = pins;
        currentRoll ++;
    }

    int score() {
        int rollIndex = 0;
        int score = 0;

        for (int i = 0; i < 10; i++) {
            if (isStrike(rollIndex)) {
                score += 10 + nextTwoRolls(rollIndex);
                rollIndex += 1;

            } else if (isSpare(rollIndex)) {
                score += 10 + firstRollInNextFrame(rollIndex);
                rollIndex += 2;

            } else {
                score += currentFrameScore(rollIndex);
                rollIndex += 2;
            }
        }

        return score;
    }

    private int nextTwoRolls(int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    private boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private boolean isSpare(int rollIndex) {
        return currentFrameScore(rollIndex) == 10;
    }

    private int currentFrameScore(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }

    private int firstRollInNextFrame(int rollIndex) {
        return rolls[rollIndex + 2];
    }
}
