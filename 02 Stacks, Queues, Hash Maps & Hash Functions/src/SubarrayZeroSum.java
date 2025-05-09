import java.util.*;

public class SubarrayZeroSum {
    public static void findZeroSumSubarrays(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;
        map.put(0, new ArrayList<>(List.of(-1)));

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                for (int start : map.get(sum)) {
                    System.out.println("Subarray: [" + (start + 1) + " to " + i + "]");
                }
            }
            map.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        findZeroSumSubarrays(arr);
    }
}
