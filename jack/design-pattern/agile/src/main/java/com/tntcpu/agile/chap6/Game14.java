package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-03
 **/
public class Game14 {
    private int ball;
    private int firstThrow;
    private int secondThrow;

    private int itsScore = 0;
    private int[] itsThrows = new int[21];
    private int itsCurrentThrow = 0;
    private int itsCurrentFrame = 1;
    private boolean firstThrowInFrame = true;

    public int score() {
        return scoreForFrame(itsCurrentFrame);
    }

    public int getCurrentFrame() {
        return itsCurrentFrame;
    }

    public void add(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
        itsScore += pins;
        adjustCurrentFrame(pins);
    }

    private void adjustCurrentFrame(int pins) {

    }

    public int scoreForFrame(int theFrame) {
        ball = 0;
        int score = 0;
        for (int currentFrame = 0; currentFrame < theFrame; currentFrame++) {
            firstThrow = itsThrows[ball];
            if (strike()) {
                ball++;
                score += 10 + nextTwoBalls();
            } else {
                score += handleSecondThrow();
            }
        }
        return score;
    }

    private int nextTwoBalls() {
        return itsThrows[ball] + itsThrows[ball + 1];
    }

    private boolean strike() {
        return itsThrows[ball] == 10;
    }

    private int handleSecondThrow() {
        int score = 0;
        secondThrow = itsThrows[ball + 1];
        int frameScore = firstThrow + secondThrow;
        if (spare()) {
            ball += 2;
            score += 10 + nextBall();
        } else {
            ball += 2;
            score += frameScore;
        }
        return score;
    }

    private int nextBall() {
        return itsThrows[ball];
    }

    private boolean spare() {
        return (itsThrows[ball] + itsThrows[ball + 1]) == 10;
    }
}
