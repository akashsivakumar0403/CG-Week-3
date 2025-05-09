import java.util.*;

public class GivenSumPair {
    public static boolean hasPair(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (seen.contains(target - num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 6};
        int target = 10;
        System.out.println(hasPair(arr, target));
    }
}
