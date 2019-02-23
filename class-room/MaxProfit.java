//package my.AlgorithmDesign;

import java.util.Scanner;
import java.util.Vector;

public class MaxProfit {
    public static void main(String[] args) {
        int[] array = getAnArray();
        System.out.println((maxProfit(array)));
    }
    private static int maxProfit(int[] ar){
        return maxProfit(0,ar.length-1,ar);
    }
    private static int maxProfit(int start, int end, int[] ar) {
        if(start == end)
            return 0;
        else{
            int mid = (start+end)/2;
            int left = maxProfit(start,mid,ar);
            int right = maxProfit(mid+1,end,ar);
            int minLeft = min(start,mid,ar);
            int maxRight = max(mid+1,end,ar);
            int merge = maxRight - minLeft;
            return Math.max(Math.max(left,right),merge);
        }
    }
    private static int min(int start,int end,int ar[]){
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end ; i++) {
            if(ar[i]<min)
                min = ar[i];
        }
        return min;
    }
    private static int max(int start,int end,int[] ar){
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if(ar[i]>max)
                max = ar[i];
        }
        return max;
    }
    private static int[] getAnArray(){
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

}
