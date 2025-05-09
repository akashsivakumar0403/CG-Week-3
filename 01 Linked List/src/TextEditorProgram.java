import java.util.Scanner;

class TextState {
    String content;
    TextState prev, next;

    TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState head, tail, current;
    private int size = 0;
    private final int MAX_HISTORY = 10;

    void addState(String content) {
        TextState newState = new TextState(content);

        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }

        if (tail != null) {
            tail.next = newState;
            newState.prev = tail;
        } else {
            head = newState;
        }

        tail = newState;
        current = newState;
        size++;
        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo successful.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo successful.");
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    void showCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.content);
        } else {
            System.out.println("No text available.");
        }
    }
}

public class TextEditorProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        while (true) {
            System.out.println("\n1. Type Text\n2. Undo\n3. Redo\n4. Show Current State\n5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.showCurrentState();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
