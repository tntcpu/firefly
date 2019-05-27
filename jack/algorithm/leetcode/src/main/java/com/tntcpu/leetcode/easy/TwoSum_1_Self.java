package com.tntcpu.leetcode.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-05-27
 **/
public class TwoSum_1_Self {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 20};
		int[] ints = twoSum(nums, 7);
		List<Integer> result = Arrays.stream(ints).boxed().collect(Collectors.toList());
		result.stream().forEach(System.out::println);
	}

	public static int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int secondNum = target - nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if (secondNum == nums[j]) {
					result = new int[]{i, j};
					return result;
				}
			}
		}
		return result;
	}

}
