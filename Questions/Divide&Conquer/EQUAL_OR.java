package my.AlgorithmDesign;

import java.util.Scanner;
import java.util.Vector;

public class EQUAL_OR {
    private static final int E_TYPE = 1, EOB_TYPE = 2, EOS_TYPE = 3;

    public static void main(String[] args) {
        int[] ar = getAnArray();
        while (true) {
            int index = bs(ar);
            System.out.println(index != -1 ? ar[index] : index);
        }
    }


    static int bs(int[] ar) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Key = ");
        int key = in.nextInt();
        System.out.println("EQ | EOB | EOS \nEnter TYPE(lower | UPPER) :");
        in.nextLine();
        int type = getType(in.nextLine());
        return bs(ar,type,key,0,ar.length-1);
    }

    private static int getType(String nextLine) {
        nextLine = nextLine.trim();
        switch (nextLine.toUpperCase()){
            case "EQ":
                return E_TYPE;
            case "EOB":
                return EOB_TYPE;
                default:
                    return EOS_TYPE;
        }
    }

    static int bs(int[] ary, int type, int key, int start, int end) {
        if (start >= end) {
            switch (type) {
                case E_TYPE:
                    if (ary[start] == key)
                        return start;
                    else if(ary[end] == key)
                        return end;
                    return -1;
                case EOB_TYPE:
                    if (ary[start] >= key)
                        return start;
                    else if(ary[end] >= key)
                        return end;
                    return -1;
                case EOS_TYPE:
                    if (ary[start] <= key)
                        return start;
                    else if(ary[end] <= key)
                        return end;
                    return -1;
                default:
                    return -1;
            }
        }
        int mid = (start + end) / 2;
        if (ary[mid] == key)
            return mid;
        if (ary[mid] < key)
            return bs(ary, type, key, mid+1, end);
        return bs(ary, type, key, start, mid -1);
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
// This code is contributed by Aakash Hasija
