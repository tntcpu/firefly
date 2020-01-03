package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-03
 **/
public class Game8 {
	private int itsScore = 0;
	private int[] itsThrows = new int[21];
	private int itsCurrentThrow = 0;
	private int itsCurrentFrame = 1;
	private boolean firstThrow = true;

	public int getCurrentFrame() {
		return itsCurrentFrame;
	}

	public void add(int pins) {
		itsThrows[itsCurrentThrow++] = pins;
		itsScore += pins;
		adjustCurrentFrame();
	}

	private void adjustCurrentFrame() {
		if (firstThrow) {
			firstThrow = false;
		} else {
			firstThrow = true;
			itsCurrentFrame++;
		}
	}

	public int scoreForFrame(int theFrame) {
		int ball = 0;
		int score = 0;
		for (int currentFrame = 0; currentFrame < theFrame; currentFrame++) {
			int firstThrow = itsThrows[ball++];
			int secondFrame = itsThrows[ball++];

			int frameScore = firstThrow + secondFrame;
			if (frameScore == 10) {
				score += frameScore + itsThrows[ball];
			} else {
				score += frameScore;
			}
		}
		return score;
	}
}
