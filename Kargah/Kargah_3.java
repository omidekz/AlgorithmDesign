public class Kargah_3 {
    public static void main(String[] args) {
        int[] t = {1,9,5,7,3,5,2,6,4};
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < t.length / 2; i++) {
            if(min > t[i]
                    && isBiggerFromAllChild(t,i,2*i+1,2*i+1))
                min = t[i];
        }
        System.out.println(min);
    }
    private static boolean isBiggerFromAllChild(int[] a,int nodeIndex,int leftChild,int rightChild){
        if(nodeIndex >= a.length
                || (leftChild >= a.length && rightChild >= a.length))
            return true;
        if(rightChild >= a.length){
            if(a[nodeIndex] > a[leftChild])
                return isBiggerFromAllChild(a,nodeIndex,2*leftChild+1,2*leftChild+2);
            return false;
        }else{
            if(a[nodeIndex] > a[leftChild] && a[nodeIndex] > a[rightChild])
                return isBiggerFromAllChild(a,nodeIndex,2*leftChild+1,2*leftChild+2)
                        &&isBiggerFromAllChild(a,nodeIndex,2*rightChild+1,2*rightChild+2);
            return false;
        }
    }
}
