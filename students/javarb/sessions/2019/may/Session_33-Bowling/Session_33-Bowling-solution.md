# SOFTWARE ENGINEERING IN JAVA

## Session 33 (14/05/2019)

- Reviewing bowling game TDD solution and feedback

## Notes

#### Reviewing bowling game TDD solution and and feedback

In my approach to solve the given Kata, I was taking a string as argument and processing the provided stream by frame (2 rolls), which sometimes can be 1 roll (when strike). 

To show this, an excerpt of my tests code: 

```java
@Test
public void givenPerfectScoreReturnValidScore() {
    assertEquals(300, runTest("X X X X X X X X X X X X"));
}

@Test
public void givenGameOfPairsAndMissessReturnValidScore() {
    assertEquals(90, runTest("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"));
}

private int runTest(String frames) {
    return new Bowling().gameScore(frames);
}

```
In `gameScore()` method of `Bowling` class I was removing spaces in the string and casting `char`s to `int`s. After of it, I was iterating over the given frames and adding up according to game conditions. This quickly came to certain point of complexity since I was having to accomplish mixed conditions into the loop, and between them, consider the possibility of just 1 roll per frame when strike or two of them in other conditions.

During session, Roger was explaining to me another approach that was considerably easier. This was by providing scores amounts directly by roll. So if there is 20 rolls (or 10 frames) in a game, I should to provide 20 calls to the `roll()` method of `Bowling` class instead to make one single call to game with `n` (1..20) characters. Also a helper method `rollMany()` was introduced in order to roll `x` points `n` times. Provided data was stored into a in memory array called `rolls`. An excerpt of some tests and `roll()` and `rollMany()` methods:

```java
public class BowlingTest {
    //...
    @Test
    public void canScorePerfectGame(){
        rollMany(12, 10);
        assertEquals(300, bowling.score());
    }

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
    //...

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++)
            bowling.roll(pins);
    }
}

class Bowling {
    //...
    void roll(int pins) {
        rolls[currentRoll] = pins;
        currentRoll ++;
    }
    //...
}
```

Into `score()` method of `Bowling` class, several ways to improve code quality were explained to me, for example, defining methods with names that be meaningful to the application, can increase enormously the readability of code:

```java
class Bowling {
    //...   
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
```

**Note:** Complete code of both versions is on [this session's folder on GitHub](https://github.com/kinbiko/mentoring/tree/javarb/session-33-Bowling/students/javarb/sessions/2019/may/Session_33-Bowling/Bowling)

### Homework

- Try to solve the problem again by my own using TDD and using as reference the solution provided if I am stagnant.