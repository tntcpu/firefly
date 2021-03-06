package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-03
 **/
public class Game2 {
	private int itsScore = 0;
	private int[] itsThrows = new int[21];
	private int itsCurrentThrow = 0;

	public int score(){
		return itsScore;
	}
	public void add(int pins){
		itsThrows[itsCurrentThrow++] = pins;
		itsScore += pins;
	}

	public int scoreForFrame(int frame){
		int score = 0;
		for (int ball = 0;
		      frame > 0 && (ball < itsCurrentThrow);
		      ball += 2, frame--){
			score += itsThrows[ball] + itsThrows[ball+1];
		}
		return score;
	}
}
