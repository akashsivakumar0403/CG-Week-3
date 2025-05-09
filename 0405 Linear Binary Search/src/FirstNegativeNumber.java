public class FirstNegativeNumber {
    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, -3, 6};
        int index = findFirstNegative(arr);
        System.out.println("First negative index: " + index);
    }
}
