public class Kargah_2 {
    public static void main(String[] args) {
        int a[] = {1,4,7,9,10,15,20,21,30};
        System.out.println(a[nearestTo(a,0,a.length-1,13)]);
    }
    private static int nearestTo(int[] a,int start,int end,int key){
        if(start == end)
            return start;
        int mid = (start + end) / 2;
        int now = Math.abs(a[mid] - key);

        int otherIndex;
        int other;
        if(a[mid] > key) {
            otherIndex = nearestTo(a, start, mid - 1, key);
            other = Math.abs(key - a[otherIndex]);
        }else {
            otherIndex= nearestTo(a, mid + 1, end, key);
            other = Math.abs(key - a[otherIndex]);
        }
        int best = Math.min(now,other);
        return best == now?mid:otherIndex;
    }
}
