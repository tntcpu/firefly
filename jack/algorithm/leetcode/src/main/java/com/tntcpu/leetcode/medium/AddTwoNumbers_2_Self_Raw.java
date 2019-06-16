package com.tntcpu.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers_2_Self_Raw {
    public static void main(String[] args) {
        List<Integer> addOneList = new ArrayList<>();
        List<Integer> addTwoList = new ArrayList<>();

        addOneList.add(2);
        addOneList.add(4);
        addOneList.add(3);

        addTwoList.add(5);
        addTwoList.add(6);
        addTwoList.add(4);

        List<Integer> result = handleTwoListAdd(addOneList, addTwoList);

        result.forEach(System.out::println);

    }

    private static List<Integer> handleTwoListAdd(List<Integer> addOneList, List<Integer> addTwoList) {
        StringBuilder addOneStr = new StringBuilder();
        StringBuilder addTwoStr = new StringBuilder();
        for (int i = addOneList.size() - 1; i >= 0; i--) {
            addOneStr.append(addOneList.get(i));
            addTwoStr.append(addTwoList.get(i));
        }

        int addOneInt = Integer.parseInt(addOneStr.toString());
        int addTwoInt = Integer.parseInt(addTwoStr.toString());

        int addResultInt = addOneInt + addTwoInt;

        String addResultStr = String.valueOf(addResultInt);
        List<Integer> result  = new ArrayList<>();
        for (int i = addResultStr.length()-1; i >= 0; i--) {
            String item = String.valueOf(addResultStr.charAt(i));
            result.add(Integer.valueOf(item));
        }
        return result;
    }
}
