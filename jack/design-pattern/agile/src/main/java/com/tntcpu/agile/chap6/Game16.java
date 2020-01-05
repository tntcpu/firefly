package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-03
 **/
public class Game16 {
    private int itsScore = 0;
    private int itsCurrentFrame = 0;
    private boolean firstThrowInFrame = true;
    private Scorer itsScorer = new Scorer();

    public int score() {
        return scoreForFrame(getCurrentFrame() - 1);
    }

    public int getCurrentFrame() {
        return itsCurrentFrame;
    }

    public void add(int pins) {
        itsScorer.addThrow(pins);
        itsScore += pins;
        adjustCurrentFrame(pins);
    }

    private void adjustCurrentFrame(int pins) {
        if (firstThrowInFrame) {
            if (pins == 10) {
                itsCurrentFrame++;
            } else {
                firstThrowInFrame = false;
            }
        } else {
            firstThrowInFrame = true;
            itsCurrentFrame++;
        }
        itsCurrentFrame = Math.min(11, itsCurrentFrame);
    }

    public int scoreForFrame(int theFrame) {
        return itsScorer.scoreForFrame(theFrame);
    }
}
