public class StudentAges {
    public static void countingSort(int[] ages) {
        int max = 18;
        int min = 10;
        int range = max - min + 1;

        int[] count = new int[range];
        for (int i = 0; i < ages.length; i++) {
            count[ages[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[ages.length];
        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i] - min] - 1] = ages[i];
            count[ages[i] - min]--;
        }

        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void main(String[] args) {
        int[] ages = {12, 15, 10, 14, 18, 16, 13};
        countingSort(ages);
        for (int age : ages) {
            System.out.println(age);
        }
    }
}
