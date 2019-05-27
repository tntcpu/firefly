package com.tntcpu.leetcode.easy;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-05-27
 **/
public class TwoSum_1_Best {
	public static void main(String[] args) {

	}

	public int[] twoSum(int[] nums,int target){
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] == target - nums[i]){
					return new int[]{i,j};
				}
			}
		}
		throw new IllegalArgumentException("no two sum solution");
	}
}
