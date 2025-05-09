public class RotationPoint {
    public static int findRotationIndex(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[right])
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {23,9,10,2,934,31};
        int index = findRotationIndex(arr);
        System.out.println("Rotation point index: " + index);
    }
}
