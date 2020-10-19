package com.kygi.kakaoTask3.Util;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@Slf4j
public class CommonUtils {

    // function to generate a random string of length n
    public static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static int[] randSum(int n, int min, int m) {
        Random rand = new Random();
        int[] nums = new int[n];
        int max = m - min*n;
        if(max <= 0)
            throw new IllegalArgumentException();
        for(int i=1; i<nums.length; i++) {
            nums[i] = rand.nextInt(max);
        }
        Arrays.sort(nums, 1, nums.length);
        for(int i=1; i<nums.length; i++) {
            nums[i-1] = nums[i]-nums[i-1]+min;
        }
        nums[nums.length-1] = max-nums[nums.length-1]+min;
        return nums;
    }

    public static boolean passedTimeCheck(Date currentTime, Date createdAt, long days, long hours, long minutes, long seconds)
    {
        long diff = currentTime.getTime() - createdAt.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        long duration = (seconds + minutes * 60 + hours * (60 * 60) + days * (24 * 60 * 60)) * 1000;

        log.info("diff = {}", diff);
        log.info("diffSeconds = {}", diffSeconds);
        log.info("diffMinutes = {}", diffMinutes);
        log.info("diffHours = {}", diffHours);
        log.info("diffDays = {}", diffDays);
        log.info("duration = {}", duration);
        log.info("diff > duratio = {}", diff > duration);

        return ((diff > duration) ? true:false);
    }
}
