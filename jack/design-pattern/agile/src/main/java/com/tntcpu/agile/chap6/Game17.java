package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-03
 **/
public class Game17 {
	private int itsCurrentFrame = 0;
	private boolean firstThrowInFrame = true;
	private Scorer itsScorer = new Scorer();

	public int score() {
		return scoreForFrame(itsCurrentFrame);
	}

	public void add(int pins) {
		itsScorer.addThrow(pins);
		adjustCurrentFrame(pins);
	}

	private void adjustCurrentFrame(int pins) {
		if (strike(pins) || !firstThrowInFrame) {
			advanceFrame();
		} else {
			firstThrowInFrame = false;
		}
	}

	private boolean strike(int pins) {
		return (firstThrowInFrame && pins == 10);
	}

	private void advanceFrame() {
		itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
	}

	public int scoreForFrame(int theFrame) {
		return itsScorer.scoreForFrame(theFrame);
	}
}
