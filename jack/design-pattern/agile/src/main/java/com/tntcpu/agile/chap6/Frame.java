package com.tntcpu.agile.chap6;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-02
 **/
public class Frame {
	public int getScore() {
		return itsScore;
	}

	public void add(int pins) {
		itsScore += pins;
	}

	private int itsScore = 0;
}
