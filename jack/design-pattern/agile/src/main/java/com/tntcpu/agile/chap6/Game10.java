package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-03
 **/
public class Game10 {
	private int itsScore = 0;
	private int[] itsThrows = new int[21];
	private int itsCurrentThrow = 0;
	private int itsCurrentFrame = 1;
	private boolean firstThrow = true;

	public int score(){
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
		if (firstThrow) {
			if (pins == 10) {
				itsCurrentFrame++;
			} else {
				firstThrow = false;
			}
		} else {
			firstThrow = true;
			itsCurrentFrame++;
		}
		itsCurrentFrame = Math.min(10, itsCurrentFrame);
	}

	public int scoreForFrame(int theFrame) {
		int ball = 0;
		int score = 0;
		for (int currentFrame = 0; currentFrame < theFrame; currentFrame++) {
			int firstThrow = itsThrows[ball++];
			if (firstThrow == 10){
				score += firstThrow + itsThrows[ball] + itsThrows[ball+1];
			}else {
				int secondFrame = itsThrows[ball++];
				int frameScore = firstThrow + secondFrame;
				if (frameScore == 10) {
					score += frameScore + itsThrows[ball];
				} else {
					score += frameScore;
				}
			}
		}
		return score;
	}
}
