import java.util.Stack;

class SortStack {
    public static void sort(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int temp = stack.pop();
            sort(stack);
            insertSorted(stack, temp);
        }
    }

    public static void insertSorted(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
        } else {
            int temp = stack.pop();
            insertSorted(stack, element);
            stack.push(temp);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(10);
        stack.push(20);
        stack.push(50);
        stack.push(40);

        sort(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
