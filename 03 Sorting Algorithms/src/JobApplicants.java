public class JobApplicants {
    public static void heapSort(int[] salaries) {
        int n = salaries.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            makeHeap(salaries, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = salaries[0];
            salaries[0] = salaries[i];
            salaries[i] = temp;
            makeHeap(salaries, i, 0);
        }
    }

    public static void makeHeap(int[] salaries, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && salaries[left] > salaries[largest]) {
            largest = left;
        }

        if (right < n && salaries[right] > salaries[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = salaries[i];
            salaries[i] = salaries[largest];
            salaries[largest] = temp;
            makeHeap(salaries, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] salaries = {55000, 48000, 75000, 62000, 50000};
        heapSort(salaries);
        for (int salary : salaries) {
            System.out.println(salary);
        }
    }
}
