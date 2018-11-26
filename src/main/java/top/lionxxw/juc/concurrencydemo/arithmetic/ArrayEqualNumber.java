package top.lionxxw.juc.concurrencydemo.arithmetic;

import java.util.*;

/**
 * 一个无序数组，查询其中任意两个数相等等于一个指定书的组合

 * created on 2018/5/16 11:22
 *
 * @author lionxxw
 * @version 1.0.0
 */
public class ArrayEqualNumber {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 1, 2, 1, 3, 5};
        int value = 10;
        List<Integer[]> result = sumToArray(nums, value);
        if (null != result && result.size() > 0) {
            result.forEach(item -> System.out.println(Arrays.toString(item)));
        } else {
            System.out.println("无匹配结果");
        }
    }

    public static List<Integer[]> sumToArray(int[] nums, int sum) {
        List<Integer[]> result = new ArrayList<>();
        if (null != nums && nums.length > 2) {
            // key: 数字  value: 该数字在此数组中出现次数
            Map<Integer, Integer> numMap = new HashMap<>(nums.length);
            for (int num : nums) {
                if (numMap.containsKey(num)) {
                    Integer size = numMap.get(num);
                    ++size;
                    numMap.put(num, size);
                } else {
                    numMap.put(num, 1);
                }
            }

            for (int num : nums) {
                Integer sub = sum - num;
                if (numMap.containsKey(sub)) {
                    Integer size = numMap.get(sub);
                    if (num == sub) {
                        --size;
                    }
                    if (size > 0) {
                        for (int i = 0; i < size; i++) {
                            Integer[] item = {num, sub};
                            result.add(item);
                        }
                    }
                }
            }
        }

        return result;
    }
}
