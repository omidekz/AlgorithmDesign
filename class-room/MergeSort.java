import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class MergeSort {
    public static void main(String[] args) {
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
        System.out.println(Arrays.toString(mergeSort(nums.toArray(new Integer[]{}))));
    }
    static int[] mergeSort(Integer[] nums){
        int[] num = new int[nums.length];
        for (int i = 0; i < num.length; i++) {
            num[i]=nums[i];
        }
        return mergeSort(num);
    }
    static int[] mergeSort(int[] nums){
        return   mergeSort(0,nums.length-1,nums);
    }
    static int[] mergeSort(int start,int end,int[] nums){
        int[] tmp = new int[end-start+1];
        if(end == start)
            tmp[0] = nums[end];
        else{
            int mid = (start + end) / 2;
            int[] left = mergeSort(start,mid,nums);
            int[] right = mergeSort(mid+1,end,nums);

            int pointerLeft = 0;
            int pointerRight = 0;
            for (int i = 0; i < tmp.length; i++) {
                if(pointerLeft>=left.length)
                    tmp[i] = right[pointerRight++];
                else if(pointerRight>=right.length)
                    tmp[i]=left[pointerLeft++];
                else if(left[pointerLeft] < right[pointerRight])
                    tmp[i]=left[pointerLeft++];
                else
                    tmp[i]=right[pointerRight++];
            }
        }
        return tmp;
    }
}
