//package my.AlgorithmDesign;

import java.util.Scanner;
import java.util.Vector;

public class MaxSumSubarray {
    public static void main(String[] args) {
        int[] array = getAnArray();
        System.out.println(maxSumSubArray(array));
    }
    static int maxSumSubArray(int[] ar){
        return maxSumSubArray(0,ar.length-1,ar);
    }
    static int[] getAnArray(){
        System.out.println("Enter n Number \n[seprate with space, enter 'f' for sort]:");
        Vector<Integer> nums = new Vector<>();
        String nu;
        Scanner sc = new Scanner(System.in);
        while (true){
            nu = sc.next();
            if(nu.equals("f"))
                break;
            if(!nu.equals(""))
                nums.add(Integer.valueOf(nu.trim()));
        }
        int[] tmp = new int[nums.size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = nums.get(i);
        }
        return tmp;
    }
    private static int maxSumSubArray(int start, int end, int[] ar) {
        if(start == end)
            return ar[end];
        else{
            int mid=(start+end)/2;
            int left = maxSumSubArray(start,mid,ar);
            int right = maxSumSubArray(mid+1,end,ar);

            int sum = 0;
            int maxInLeft=Integer.MIN_VALUE;
            for (int i = mid; i >=start ; i--) {
                sum+=ar[i];
                if(sum > maxInLeft)
                    maxInLeft = sum;
            }
            sum = 0;
            int maxInRight = Integer.MIN_VALUE;
            for (int i = mid+1; i <=end; i++) {
                sum+=ar[i];
                if(sum > maxInRight)
                    maxInRight= sum;
            }
            return Math.max(Math.max(left,right),maxInLeft+maxInRight);
        }
    }
}
