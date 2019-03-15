import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class CountingSort {
    public static void main(String[] args) {
        int [] t = getAnArray();
        System.out.print("Enter k = ");
        int k = new Scanner(System.in).nextInt();
        int [] c = new int[k+1];
        for (int aT : t) c[aT]++;
        for (int i = 1; i < k + 1; i++) c[i]+=c[i-1];
        int [] b = new int[t.length];
        for (int aT : t) {
            b[c[aT] - 1] = aT;
            c[aT]--;
        }
        System.out.println(Arrays.toString(b));
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
