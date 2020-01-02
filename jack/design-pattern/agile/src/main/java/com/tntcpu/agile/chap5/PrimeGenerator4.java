package com.tntcpu.agile.chap5;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-02
 **/
public class PrimeGenerator4 {
	private static boolean[] crossedOut;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if (maxValue < 2) {
			return new int[0];
		} else {
			uncrossIntegersUpTo(maxValue);
			crossOutMultiples();
			putUncrossedIntegersIntoResult();
			return result;
		}
	}

	private static void putUncrossedIntegersIntoResult() {
		result = new int[numberOfUncrossedIntegers()];
	}

	private static int numberOfUncrossedIntegers() {
		int count = 0;
		for (int i = 2; i < crossedOut.length; i++) {
			if (notCrossed(i)) {
				count++;
			}
		}
		return count;
	}

	private static void crossOutMultiples() {
		int limit = datermineIterationLimit();
		for (int i = 2; i <= limit; i++) {
			if (notCrossed(i)) {
				crossOutMultiplesOf(i);
			}
		}
	}

	private static boolean notCrossed(int i) {
		return !crossedOut[i];
	}

	private static void crossOutMultiplesOf(int i) {
		for (int multiple = 2 * i; multiple < crossedOut.length; multiple += i) {
			crossedOut[multiple] = true;
		}
	}

	private static int datermineIterationLimit() {
		double iterationLimit = Math.sqrt(crossedOut.length);
		return (int) iterationLimit;
	}

	private static void uncrossIntegersUpTo(int maxValue) {
		crossedOut = new boolean[maxValue + 1];
		for (int i = 2; i < crossedOut.length; i++) {
			crossedOut[i] = false;
		}
	}
}
