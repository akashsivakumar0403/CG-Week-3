public class EmployeeID {
    public static void insertionSort(int[] ids) {
        int n = ids.length;
        for (int i = 1; i < n; i++) {
            int key = ids[i];
            int j = i - 1;
            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j--;
            }
            ids[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] ids = {1023, 1005, 1012, 1001, 1020};
        insertionSort(ids);
        for (int id : ids) {
            System.out.println(id);
        }
    }
}
