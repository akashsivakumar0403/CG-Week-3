public class PeakElement {
    public static int findPeak(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < arr[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }
        return arr[left];
    }

    public static void main(String[] args) {
        int[] arr = {1,4,69,31,90,21};
        System.out.println("Peak element: " + findPeak(arr));
    }
}
