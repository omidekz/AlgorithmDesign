import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = new int[10];
        Random random = new Random();
        for (int i = 0; i <a.length ; i++) {
            a[i] = random.nextInt(100);
        }
        long s = new Date().getTime();
        System.out.println(Arrays.toString(a));
        quickSort(a,0,a.length-1);
        System.out.println(new Date().getTime() - s);
        System.out.println(Arrays.toString(a));
    }
    private static void quickSort(int [] ar, int s, int e){
        if (s >= e)
            return;
        int p = partition(ar,s,e);
        quickSort(ar,s,p-1);
        quickSort(ar,p+1,e);
    }

    private static int partition(int[] ar,int s,int e) {
        int i=s+1;
        int pivot = ar[s];
        for (int k = s; k <= e; k++)
            if(ar[k] < pivot){
                swap(ar,i,k);
                i++;
            }
        swap(ar,i-1,s);
        return i-1;
    }
    private static void swap(int[] ar,int i,int j){
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }
}
