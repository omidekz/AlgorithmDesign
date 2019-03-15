import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class HeapSort {
    public static void main(String[] args) {
        int[] t = getAnArray();
        System.out.println(Arrays.toString(t));
        buildMaxHeap(t);
        for (int i = 0; i < t.length - 1; i++) {
            swap(t,0,t.length-i-1);
            maxHeapifyOnChild(t,0,t.length-i-1);
        }
        System.out.println(Arrays.toString(t));
    }

    private static void buildMaxHeap(int[] t) {
        for (int i = t.length/2; i >= 0; i--) {
            maxHeapifyOnChild(t,i);
        }
    }

    private static void maxHeapifyOnChild(int[] a,int index){
        maxHeapifyOnChild(a,index,a.length);
    }
    private static void maxHeapifyOnChild(int[] a,int index,int len){

        if(index >= len || 2*index + 1 >= len)
            return;
        int l = 2*index + 1,
                r = l + 1;
        if(r >= len ){
            if(a[index] > a[l])
                return;
            swap(a,index,l);
            maxHeapifyOnChild(a,l,len);
            return;
        }
        else if(a[index] > a[l] && a[index]>a[r])
            return;
        int s_swap = a[r]>=a[l]?r:l;
        swap(a,index,s_swap);
        maxHeapifyOnChild(a,s_swap,len);
    }

    private static void swap(int[] a,int aa,int bb){
        int tmp = a[aa];
        a[aa]=a[bb];
        a[bb]=tmp;
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
