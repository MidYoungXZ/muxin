package com.muxin.demo.arithmetic;

/**
 * @projectname: muxin
 * @filename: SlidingWindow
 * @author: yangxz
 * @data:2024/3/31 19:55
 * @description:
 */
public class SlidingWindow {


    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其总和大于等于 target 的长度最小的 连续
     * 子数组
     * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * <p>
     * 示例 2：
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     */

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 8, 0, 4, 4, 8, 6, 2};



    }


    public  static  int minSubArrayLen3(int s, int[] nums){
        if (nums .length ==0 ){
            return 0;
        }
        int begin =0;
        int end =0;
        int sum = 0;
        int sub= Integer.MAX_VALUE;
        while (end < nums.length){
            sum += nums[end];
             while (sum >= s){
                sub = Math.min(sub, end - begin + 1);
                sub -= nums[begin];
                begin++;
            }
            end++;
        }
        return sub == Integer.MAX_VALUE ? 0 :sub;
    }


    public  static  int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
